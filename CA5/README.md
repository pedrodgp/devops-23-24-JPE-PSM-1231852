# Technical Report - Class Assignment 5

### DevOps 23-24 JPE-PSM

#### Pedro Pires, 1231852

## Index

1. [Introduction](#introduction)


2. [What is Jenkins](#what-is-jenkins)


3. [Getting Started with Jenkins](#getting-started-with-jenkins)


4. [First Goal - Jenkins Pipeline for 'gradle basic demo' project](#first-goal---jenkins-pipeline-for-gradle-basic-demo-project)


5. [Second Goal - Jenkins Pipeline for 'react-and-spring-data-rest-basic' project with Docker](#second-goal---jenkins-pipeline-for-react-and-spring-data-rest-basic-project-with-docker)


6. [Conclusion](#conclusion)


# Introduction

In this assignment, we will be using Jenkins to automate the build and deployment of two projects. The first project is
a simple Java project that uses Gradle as a build tool. The second project is a more complex project that uses React and
Spring Boot. We will be using Jenkins to automate the build and deployment of these projects.


# What is Jenkins

Jenkins is an open-source automation server widely used for continuous integration and continuous delivery (CI/CD)
processes. It helps automate the parts of software development related to building, testing, and deploying, facilitating
DevOps practices. Here are some key features and components of Jenkins:

### Key Features of Jenkins:

1. **Extensibility**:
    - Jenkins has a vast library of plugins that extend its capabilities. These plugins allow integration with various
      tools and technologies used in software development.

2. **Continuous Integration**:
    - Jenkins automates the process of integrating code changes from multiple contributors into a shared repository,
      reducing integration issues and improving code quality.

3. **Continuous Delivery and Deployment**:
    - Jenkins can automate the delivery pipeline, ensuring that software can be reliably released at any time. It
      supports continuous deployment, where code changes are automatically deployed to production.

4. **Pipeline as Code**:
    - Jenkins uses a domain-specific language (DSL) to define build pipelines in code, allowing version control and easy
      sharing of pipeline configurations.

5. **Distributed Builds**:
    - Jenkins supports the distribution of build jobs across multiple machines, improving the efficiency and speed of
      the build process.

6. **Integration with Various Tools**:
    - Jenkins integrates with numerous tools such as version control systems (Git, SVN), build tools (Maven, Gradle),
      testing frameworks, and deployment tools.

### Components of Jenkins:

1. **Jenkins Master**:
    - The central control unit that manages build processes, schedules jobs, dispatches builds to agents, and monitors
      the overall health of the build system.

2. **Jenkins Agent**:
    - A separate machine or process that runs build jobs dispatched by the master. Agents help distribute the load and
      run jobs in parallel.

3. **Jobs**:
    - Configurable units in Jenkins representing the work to be performed. Jobs can be simple tasks like running a
      script or complex workflows involving multiple steps.

4. **Build Pipelines**:
    - A series of interconnected jobs that define the stages of the software delivery process. Pipelines can include
      stages like code compilation, testing, and deployment.

5. **Plugins**:
    - Extensions that add specific features or integrations to Jenkins. Popular plugins include those for Git, Docker,
      Slack notifications, and many testing tools.

### Use Cases of Jenkins:

- **Automating Code Builds**:
    - Compiling code, running unit tests, and generating artifacts automatically upon code changes.

- **Continuous Testing**:
    - Running automated tests to validate code quality and functionality after each change.

- **Automated Deployment**:
    - Deploying applications to various environments such as development, staging, and production.

- **Monitoring and Notifications**:
    - Sending notifications about build status, failures, and other important events to team members through email,
      Slack, or other communication tools.

Overall, Jenkins is a powerful tool that helps streamline and automate the software development lifecycle, enabling
faster delivery of high-quality software.



# Getting Started with Jenkins


### Installation and Setup:

To get started, we need to install Jenkins on our machine, we will be using Docker container to run Jenkins. To install
Jenkins, we need to run the following command:

```bash
docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home --name=jenkins jenkins/jenkins:lts-jdk17
```

This command will download the Jenkins Docker image and run it on our machine. Save the Jenkins password that is
displayed in the terminal, we will need it to unlock Jenkins. To access Jenkins, go to
`http://localhost:8080` in your browser. Execute the necessary steps (installations, create an account, etc) to unlock
Jenkins.

You should be able to see the Jenkins dashboard after unlocking Jenkins and the Docker container running. Now we can
start creating pipelines for our projects.

![Jenkins Dashboard](https://i.ibb.co/bFdcZ2M/jenkins1.png)


### Jenkins Credentials:

We are using a private repository to store the code for the projects. To access the repository, we need to set up the
credentials in Jenkins. We are using GitHub as the repository provider, so we need to add GitHub new personal access token
to Jenkins. After creating the token, go to `Manage Jenkins -> Manage Credentials -> (Global) -> Add Credentials` and add the necessary
credentials to access the repository.

![Jenkins Credentials](https://i.ibb.co/KD0ftqz/jenkins2.png)


# First Goal - Jenkins Pipeline for 'gradle basic demo' project

The first goal is to create a Jenkins pipeline for a simple Java project in the CA2 part 1, which uses Gradle as the
build tool. The objective is to automate the build process and run the tests for the project using Jenkins.


Resume of the steps to create the pipeline:
- **Checkout** - To checkout the code form the repository.
- **Assemble** - Compiles and Produces the archive files with the application. Do not use the build task of gradle (because it also executes the tests)!
- **Test** - Executes the Unit Tests and publish in Jenkins the Test results. See the junit step for further information on how to archive/publish test results.
- **Archive** - Archives in Jenkins the archive files (generated during Assemble).

Add this Jenkinsfile to the root of the project `CA2/part1/gradle_basic_demo`:

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852.git'
            }
        }
        stage('Assemble') {
            steps {
                dir('CA2/Part1/gradle_basic_demo') {
                    echo 'Assembling...'
                    sh 'chmod +x gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }
        stage('Test') {
            steps {
                dir('CA2/Part1/gradle_basic_demo') {
                    echo 'Testing...'
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Archiving') {
            steps {
                dir('CA2/Part1/gradle_basic_demo') {
                    echo 'Archiving...'
                    archiveArtifacts 'build/distributions/*'
                }
            }
        }
    }
}
```

### Explanation:

* The first stage is the `Checkout` stage, where we checkout the code from the repository using the `git` command. We
specify the branch, credentials, and URL of the repository.


* The second stage is the `Assemble` stage, where we compile the code and produce the archive files with the application.
We use the `./gradlew clean assemble` command to clean the project and assemble the application.


* The third stage is the `Test` stage, where we execute the unit tests using the `./gradlew test` command. We also use the
`junit` step to publish the test results in Jenkins.


* The fourth stage is the `Archiving` stage, where we archive the archive files generated during the `Assemble` stage using
the `archiveArtifacts` step.


### Create and Run the Pipeline:

After adding the Jenkinsfile to the project, we can create a new pipeline in Jenkins by selecting `New Item -> Pipeline`
and configuring the pipeline to use the Jenkinsfile in the project repository.
Then select `Definition -> Pipeline script from SCM`, `SCM -> Git`, and provide the repository URL and credentials.


![Jenkins Pipeline](https://i.ibb.co/ZGrt9M0/jenkins3.png)

![Jenkins Pipeline Running](https://i.ibb.co/wg2PrHG/jenkins4.png)


Now we can run the pipeline and see the build process in Jenkins. The pipeline will checkout the code, compile the
application, run the tests, and archive the artifacts. We can view the test results and the archived artifacts in the
Jenkins dashboard as the next pictures show.

![Jenkins Stages Results](https://i.ibb.co/nkM2KsK/jenkins5.png)

![Jenkins Artifacts Result](https://i.ibb.co/3vfS0jQ/jenkins6.png)

![Jenkins Build Result](https://i.ibb.co/0h5F6Y7/jenkins7.png)


# Second Goal - Jenkins Pipeline for 'react-and-spring-data-rest-basic' project with Docker


The second goal is to create a Jenkins pipeline for a more complex project that uses React and Spring Boot. The objective
is to checkout the code from the repository, build the project, run the tests, create javadoc, create a Docker image, and
push the image to Docker Hub.

### Configure the Jenkins for Docker:

First you need to change the Jenkins configuration to allow Jenkins to run Docker commands.
You can do it by **removing the current Jenkins** container and **running a new one** with the following dockerfile configuration:

```dockerfile
FROM jenkins/jenkins:lts-jdk17
USER root
RUN apt-get update -qq \
    && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
RUN apt-get update  -qq \
    && apt-get -y install docker-ce
RUN usermod -aG docker jenkins
```

In the folder where the Dockerfile is located, run the following command to build the image:

```bash
docker image build -t custom-jenkins-docker .
```

After building the image, run the following command to start the Jenkins container:

```bash
docker run --name custom-jenkins-docker-ca5 -it -p 8080:8080 -p 50000:50000 -v //var/run/docker.sock:/var/run/docker.sock -v jenkins_home2:/var/jenkins_home custom-jenkins-docker
```


Configure the Jenkins credentials to access the GitHub repository (as explained in the first goal) and add the Docker Hub
credentials to Jenkins (is the same process as adding the GitHub credentials).
You should be able to see the two available credentials in Jenkins like the following image:

![Jenkins Credentials](https://i.ibb.co/KhhGQYF/ca5p2-6.png)

And install this **plugins** in Jenkins:
- **HTML Publisher** - This plugin allows you to publish HTML reports in Jenkins (used to publish Javadoc).
- **Docker Pipeline** - This plugin provides a build step to build Docker images and publish them to Docker Hub.
- **Docker Commons** - This plugin provides common Docker operations for other plugins.
- **Docker** - This plugin integrates Jenkins with Docker (used to run Docker commands in Jenkins).
- **Docker API** - This plugin provides a REST API for managing Docker containers and images.


### Create the Jenkins Pipeline:

Resume of the steps to create the pipeline:
- **Checkout** - To checkout the code form the repository.
- **Assemble** - Builds the project without running the tests.
- **Test** - Executes the Unit Tests and publish in Jenkins the Test results.
- **Javadoc** - Generates the Javadoc for the project.
- **Docker Build** - Builds the Docker image.
- **Docker Push** - Pushes the Docker image to Docker Hub.
- **Archive** - Archives in Jenkins the archive files (generated during Build).

One important thing is that you need to comment the database configuration in the `application.properties` file of the
Spring Boot project, if you want to test the result docker image. The database configuration is not necessary for this
project.

After that, add this **Jenkinsfile** and **Dockerfile** to the root of the project `CA2/Part2/react-and-spring-data-rest-basic`:

### Jenkinsfile

```groovy
pipeline {
    agent any

    environment {
        DOCKERHUB_TOKEN = credentials('dockerhub')
        IMAGE_NAME = "${env.DOCKERHUB_TOKEN_USR}/jenkins-ca5-devops"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852'
            }
        }

        stage('Assemble') {
            steps {
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    echo 'Assembling...'
                    sh 'chmod +x gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }

        stage('Test') {
            steps {
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    echo 'Testing...'
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Javadoc') {
            steps {
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    echo 'Generating Javadocs...'
                    sh './gradlew javadoc'
                    publishHTML(target: [
                            reportDir  : 'build/docs/javadoc',
                            reportFiles: 'index.html',
                            reportName : 'Javadoc'])
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    echo 'Building Docker Image...'
                    script {
                        dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}", "-f Dockerfile .")
                    }
                }
            }
        }

        stage('Docker Login') {
            steps {
                echo 'Logging in to Docker Hub...'
                sh 'echo $DOCKERHUB_TOKEN_PSW | docker login -u $DOCKERHUB_TOKEN_USR --password-stdin'
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker Image...'
                sh 'docker push $IMAGE_NAME:$BUILD_NUMBER'
            }
        }

        stage('Archiving') {
            steps {
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    echo 'Archiving...'
                    archiveArtifacts 'build/libs/*'
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            sh 'docker rmi $IMAGE_NAME:$BUILD_NUMBER'
            sh 'docker logout'
        }
    }
}
```

#### Explanation:

The `environment` block is used to define environment variables that can be used across stages in the pipeline. We define
the `DOCKERHUB_TOKEN` variable to store the Docker Hub credentials (it should be the same name as the Docker Hub credentials
added to Jenkins) and the `IMAGE_NAME` variable to store the name of the Docker image.

1. The first stage is the `Checkout` stage, where we checkout the code from the repository using the `git` command. We
   specify the branch, credentials, and URL of the repository.
2. The second stage is the `Assemble` stage, where we compile the code and produce the archive files with the application.
3. The third stage is the `Test` stage, where we execute the unit tests using the `./gradlew test` command. We also use the
  `junit` step to publish the test results in Jenkins.
4. The fourth stage is the `Javadoc` stage, where we generate the Javadoc for the project using the `./gradlew javadoc`
command. We use the `publishHTML` step to publish the Javadoc in Jenkins.
5. The fifth stage is the `Build Docker Image` stage, where we build the Docker image using the `docker.build` command. The
`${BUILD_NUMBER}` variable is used to tag the Docker image with the build number generated by Jenkins (example, if the
build number is 10, the image will be tagged as `jenkins-ca5-devops:10`).
6. The sixth stage is the `Docker Login` stage, where we log in to Docker Hub using the Docker Hub credentials.
7. The seventh stage is the `Push Docker Image` stage, where we push the Docker image to Docker Hub using the `docker push`
command.
8. The eighth stage is the `Archiving` stage, where we archive the archive files generated during the `Assemble` stage using
   the `archiveArtifacts` step.
9. The `post` block contains the cleanup steps that run after the pipeline finishes. We remove the Docker image and log out
from Docker Hub.

### Dockerfile

```dockerfile
FROM tomcat:10-jdk17

COPY build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080
```

#### Explanation:

The Dockerfile is used to build the Docker image for the Spring Boot application. We use the `tomcat:10-jdk17` base image
and copy the WAR file generated during the build process to the Tomcat webapps directory. We expose port 8080 to allow
access to the application.


### Create and Run the Pipeline:

After adding the Jenkinsfile and Dockerfile to the project, we can create a new pipeline in Jenkins by selecting `New Item
-> Pipeline` and configuring the pipeline in the same way as the first goal, the only difference is that we need to add the
new path to the Jenkinsfile in the repository `CA2/Part2/react-and-spring-data-rest-basic/Jenkinsfile`. Then we can run the
pipeline and see the build process in Jenkins.

The results should be similar to the following images:


![Stages Results](https://i.ibb.co/hBrw11L/ca5p2-2.png)
![Test Results](https://i.ibb.co/q1Lrn7s/ca5p2-8.png)
![Javadoc Results](https://i.ibb.co/6NXvd0w/ca5p2-4.png)
![Artifacts Results](https://i.ibb.co/vH1zzs4/ca5p2-7.png)

The Docker image should be pushed to Docker Hub after the pipeline finishes. You can check the Docker Hub repository to
see the pushed image. **The image should be tagged with the build number generated by Jenkins.**

![Docker Hub Results](https://i.ibb.co/pP0KS18/ca5p2-5.png)


### Running the result Docker Image:

If you want you can then pull the image from Docker Hub and run it using the following command:

```bash
docker pull pdgpires/jenkins-ca5-devops:1
```

```bash
docker run -p 8181:8080 pdgpires/jenkins-ca5-devops:1
```

You should be able to access the application at `http://localhost:8181/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT` in your browser.

![Docker Pull-RUN Results](https://i.ibb.co/svgPMSD/ca5p2-3.png)


# Conclusion

In this assignment, we used Jenkins to automate the build and deployment of two projects. We created Jenkins pipelines for
a simple Java project that uses Gradle and a more complex project that uses React and Spring Boot. We learned how to
configure Jenkins, create pipelines, and integrate Jenkins with Docker to build and push Docker images. Jenkins is a
powerful tool that helps streamline the software development lifecycle and enables faster delivery of high-quality
software. By automating the build and deployment processes, we can improve the efficiency and reliability of software
development and delivery.






