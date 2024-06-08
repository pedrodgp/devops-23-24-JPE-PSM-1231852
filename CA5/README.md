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
                git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/pedrodgp/devops-jen.git'
            }
        }
        stage('Assemble') {
            steps {
                dir('CA2/part1/gradle_basic_demo') {
                    echo 'Assembling...'
                    sh 'chmod +x gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }
        stage('Test') {
            steps {
                dir('CA2/part1/gradle_basic_demo') {
                    echo 'Testing...'
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
       stage('Archiving') {
                   steps {
                        dir('CA2/part1/gradle_basic_demo') {
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





