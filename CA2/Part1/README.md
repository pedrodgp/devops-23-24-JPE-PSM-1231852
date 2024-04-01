# Technical Report - Class Assignment 2 - Part 1
### DevOps 23-24 JPE-PSM
#### Pedro Pires, 1231852

## Introduction

This report outlines my work on Class Assignment 2, Part 1 for the DevOps course, focusing on using Gradle for build automation. The assignment involved cloning an example application, creating Gradle tasks to manage server execution and source file manipulation and the introduction of unit testing.

The challenge was to apply Gradle's capabilities to automate routine tasks effectively, a crucial skill in DevOps for ensuring efficient and consistent build processes. The inclusion of unit testing further emphasizes the importance of maintaining code quality alongside automation. The report details the steps taken, from issue creation and task implementation to the final results, showcasing the practical application of Gradle in a real-world scenario.

The repository can be found [here](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852).

## Index
1. [About Gradle](#about-gradle)
2. [Getting Started](#getting-started)
3. [Add a new task to execute the server](#add-a-new-task-to-execute-the-server)
4. [Add a simple Unit Test](#add-a-simple-unit-test)
5. [Add a new task to copy the src folder to a backup folder](#add-a-new-task-to-copy-the-src-folder-to-a-backup-folder)
6. [Add a new task to zip the src folder to a backup folder](#add-a-new-task-to-zip-the-src-folder-to-a-backup-folder)
7. [Conclusion](#conclusion)


## About Gradle

Gradle is a powerful build automation tool that simplifies the process of managing software projects. It allows for the
efficient scripting and execution of tasks such as compiling code, packaging binaries, and running tests. Unlike traditional
build tools, Gradle uses a Groovy-based DSL (Domain-Specific Language) for its configuration scripts, making them more
expressive and easier to understand, it also runs on the JVM (Java Virtual Machine), providing a familiar environment for
Java developers.

In the context of the _Class Assignment 2 - Part 1_, Gradle was instrumental in setting up the project structure,
defining tasks for executing the application, and handling dependencies. A typical Gradle project consists of a `build.gradle` file,
which outlines the build configuration, including plugins to use, tasks to run, and dependencies required by the project.

One of the standout features of Gradle is its incremental build capabilities, which avoid re-running tasks if the inputs
have not changed, significantly speeding up the build process. Additionally, its dependency management system automatically
resolves and fetches libraries needed for the project, ensuring that the development process is smooth and efficient.

By leveraging Gradle, we could automate repetitive tasks, streamline the build process, and focus more on the development
work, showcasing Gradle's importance and utility in modern software development practices.

## Getting Started

1. **Create the first issue** in the repository to track the changes that will be made:

- #### Issue #1
     - **Tittle:** ```CA2 p1 - create a new folder and clone gradle_basic_demo```
     - **Description:**
``Create a folder for each part of the class assignment, CA2/Part1 and CA2/part2.
For the Part 1, clone the example application available at`` [Click Here](..%2F..%2F..%2F..%2F..%2F..%2FAppData%2FLocal%2FTemp%2Fpssmatos%20-%20gradle_basic_demo%20%97%20Bitbucket.url)

2. After creating the issue, **create a new folder** for the assignment, create the folder `CA2/Part1` and `CA2/Part2`.

3. At `CA2/Part1`,  **clone** [this repository](..%2F..%2F..%2F..%2F..%2F..%2FAppData%2FLocal%2FTemp%2Fpssmatos%20-%20gradle_basic_demo%20%97%20Bitbucket.url)
   to your local machine, it will be used as the base for the assignment.

4. **Open a terminal** and execute the following command from the project's root directory to build the .jar file:
```
./gradlew build 
```
5. **Run the server**, but substitute `<server port>` by a valid por number, e.g. 59001
```
java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp <server port>
```
6. **Run a client**, open another terminal and execute the following gradle task from the project's root directory. You can run two or more clients by opening more terminals and repeating the invocation of the runClient gradle task.
```
./gradlew runClient
```

After opening the server and the clients, it should look like this:

![loginPedro](https://i.ibb.co/t8Yzsy9/1.png)

![loginAna](https://i.ibb.co/Q9K90sm/2.png)

![chatExample](https://i.ibb.co/YjDkz52/3.png)


## Add a new task to execute the server.

This segment of the assignment is conducted on the **main branch**, with the objective of adding a new task to execute the server. The task will be named `runServer` and will execute the server with the default port 59001.

1**Create a new issue** in the repository to track the changes that will be made.
- #### Issue #2
  - **Tittle:** ```CA2 p1 - Add a new task to execute the server```

2. Open the `build.gradle` file in the project's root directory and add the following task to the file:
```gradle
task runServer(type:JavaExec, dependsOn: classes){
    group = "DevOps"
    description = "Launches a chat server that listens on port 59001"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}
```

3. Commit the changes and close the issue.


## Add a simple Unit Test

This segment of the assignment is conducted on the **main branch**, with the objective of 

1**Create a new issue** in the repository to track the changes that will be made.
- #### Issue #2
    - **Tittle:** ```CA2 p1 - Add a simple unit test and update the gradle script```
    - **Description:** ```Update the gradle script, the unit tests require junit 4.12 to execute.
Add a simple unit test for testAppHasAGreeting()```

2. Open the `build.gradle` file in the project's root directory and add inside the `dependencies` block the following line to include JUnit 4.12 for testing:
```gradle
    // Use JUnit 4.12 for testing
    testImplementation 'junit:junit:4.12'
```
3. Now, create a new file `src/test/java/basic_demo/AppTest.java` and add the following code:
```java
package basic_demo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for App class.
 */
public class AppTest {

    /**
     * Test of getGreeting method, of class App.
     * If the app has a greeting, the test will pass.
     * If the app does not have a greeting, the test will fail.
     */
    @Test
    public void testAppHasAGreeting(){
        App classUnderTest= new App();
        assertNotNull("appshould have agreeting",classUnderTest.getGreeting());
    }
}
```

4. Commit the changes and close the issue.

## Add a new task to copy the src folder to a backup folder

This segment of the assignment is conducted on the **main branch**, with the objective of adding a new task to copy the contents of the src folder to a new backup folder `backup/backupSrc`. The task will be named `copy` and will be of type `Copy`.
1. **Create a new issue** in the repository to track the changes that will be made.
- #### Issue #2
    - **Tittle:** ```CA2 p1 - Add a new task of type Copy```
    - **Description:** ```Add a new task of type Copy to be used to make a backup of the sources of the application.
      It should copy the contents of the src folder to a new backup folder.```

2. Open the `build.gradle` file in the project's root directory and add the following task to the file:
```gradle
task copy(type: Copy) {
    group = "DevOps"
    description = "Copies the contents of the src folder to a new backup folder"

    from 'src'
    into 'backup/backupSrc'
}
```
3. If you run the task `copy` with the command `./gradlew copy`, the contents of the `src` folder will be copied to the `backup/backupSrc` folder. Make sure to add the `backup` folder to the `.gitignore` file to avoid committing the backup files to the repository.

![backupSRC](https://i.ibb.co/nc57KXr/backupsrc.png)

4. Commit the changes and close the issue.

## Add a new task to zip the src folder to a backup folder

This segment of the assignment is conducted on the **main branch**, with the objective of adding a new task to zip the contents of the src folder to a new backup folder `backup/backupZip`. The task will be named `createZip` and will be of type `Zip`.
- #### Issue #2
    - **Tittle:** ```CA2 p1 - Add a new task of type Zip```
    - **Description:** ```Add a new task of type Zip to be used to make an archive (i.e., zip file) of the sources of the application. It should copy the contents of the src folder to a new zip file```

2. Open the `build.gradle` file in the project's root directory and add the following task to the file:
```gradle
task createZip(type: Zip) {
    group = 'DevOps'
    description = 'Copies the contents of the src folder to a new zip file.'

    from 'src'
    archiveFileName = 'src.zip'
    destinationDirectory = file('backup/backupZip')
}
```
3. If you run the task `createZip` with the command `./gradlew createZip`, a zip file named `src.zip` will be created in the `backup/backupZip` folder containing the contents of the `src` folder. Make sure to add the `backup` folder to the `.gitignore` file to avoid committing the backup files to the repository.
   
![backupZIP](https://i.ibb.co/SvS1CMC/backupzip.png)
4. Commit the changes and close the issue.

# Conclusion

This technical report has documented the completion of Class Assignment 2, Part 1. The assignment focused on enhancing with Gradle and implementing new tasks to automate the build process, including the new addition of implementing unit testing. The tasks were executed on the main branch, each with its dedicated issue for effective tracking. Key accomplishments include cloning the example application, creating tasks for server execution, copying and zipping source files, and ensuring code quality through unit testing. The report provided a detailed account of the steps taken to fulfill the requirements, including the creation of issues, the implementation and the final results. The tasks were successfully implemented, and the project was updated accordingly. The next steps will involve completing Part 2 of the assignment, which will be documented in a separate report.

