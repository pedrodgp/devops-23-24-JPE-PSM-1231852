# Technical Report - Class Assignment 3 - Part 1

### DevOps 23-24 JPE-PSM

#### Pedro Pires, 1231852

## Introduction

In this technical report, I detail my experiences and outcomes from completing Class Assignment 3, Part 1 of the DevOps
course. The focus of this assignment was to set up a development environment using VirtualBox and Ubuntu, and to execute
two different projects: a Spring Boot application and a basic Gradle demo project.
The process involved configuring a virtual machine, installing necessary software and dependencies, and utilizing tools
like Git. This report not only reflects on the technical steps taken to achieve the objectives but
also serves as a practical guide for similar future endeavors.

The repository can be found [here](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852).

## Index

1. [Introduction](#introduction)
2. [Getting Started](#getting-started---create-virtualboxutm-vm-with-ubuntu)
   - [Step 1: Download and Install VirtualBox](#step-1-download-and-install-virtualbox)
   - [Step 2: Download Ubuntu](#step-2-download-ubuntu)
   - [Step 3: Initiate and configure a new VirtualBox](#step-3-initiate-and-configure-a-new-virtualbox)
3. [Repository Cloning](#make-a-clone-of-the-repository-https-or-ssh)
4. [Project Execution](#build-and-execute-the-spring-boot-tutorial-basic-project-with-maven-ca1)
   - [Spring Boot Tutorial Basic Project (with Maven), CA1](#build-and-execute-the-spring-boot-tutorial-basic-project-with-maven-ca1)
   - [Gradle Basic Demo Project, CA2 p1](#build-and-execute-gradle_basic_demo-project-ca2-p1)
   - [Spring Boot Tutorial Basic Project (with Gradle), CA2 p2](#build-and-execute-the-spring-boot-tutorial-basic-project-with-gradle-ca2-p2)
5. [Conclusion](#conclusion)

# Getting Started - Create VirtualBox/UTM VM with Ubuntu.

### Step 1: Download and Install VirtualBox

Download the latest version of VirtualBox from the official website [here](https://www.virtualbox.org/).

### Step 2: Download Ubuntu

Download the latest version of Ubuntu from the official website [here](https://ubuntu.com/download/server).

### Step 3: Initiate and configure a new VirtualBox

Minimum recommended: VM with 2048 MB RAM, 1 CPU, 25 GB disk space (maybe the default), and 2 network adapters.
Enabled the **Network Adapter 2 as Host-only Adapter** (vboxnet0) and check the IP address range of this network
(in this case it's **192.168.56.1/24**).
Select an IP in this range for the second adapter of our VM.

After starting the VM, log on into the VM and continue the setup:

1. Update the packages repositories `sudo apt update`

2. Install the network tools `sudo apt install net-tools`

3. Edit the network configuration file to setup the IP `sudo nano /etc/netplan/01-netcfg.yaml`

Make sure the contents of the file are similar to the following (in this case we are
setting the IP of the second adapter as **192.168.56.5**):

```bash
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      dhcp4: yes
    enp0s8:
      addresses:
        - 192.168.56.5/24
```

4. Apply the new changes `sudo netplan apply`

5. Install openssh-server so that we can use ssh to open secure terminal sessions to the VM (from other
   hosts)`sudo apt install openssh-server`

5. Enable password authentication for ssh ` sudo nano /etc/ssh/sshd_config` and **uncomment** the
   line `PasswordAuthentication yes`

6. Restart the ssh service `sudo service ssh restart`

7. Install an ftp server so that we can use the FTP protocol to transfers files to/from the VM (from other
   hosts) `sudo apt install vsftpd`

8. Enable write access for vsftpd `sudo nano /etc/vsftpd.conf` and **uncomment** the line `write_enable=YES`.

9. Restart the vsftpd service `sudo service vsftpd restart`

10. Install git `sudo apt install git`

11. Install Java 17 `sudo apt install openjdk-17-jdk-headless`

Since the SSH server is enabled in the VM we can now use ssh to connect to the VM.
In the host (Windows or Linux or OSX), in a terminal/console, type:

```bash
ssh pedro@192.168.56.5
```

* Where pedro should be replaced by the user name of the VM
* Where 192.168.56.5 should be replaced by the IP of the VM

# Make a clone of the repository (HTTPS or SSH)

To clone the repository using the **https protocol**:

```bash
git clone https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852.git
```

**You may need to login to your github account to be able to perform the clone operation.**

Or, you can use the **ssh protocol** to clone the repository.

1. Generate a new ssh key pair `ssh-keygen -C`

2. Copy the content of the public key `cat /home/pedro/.ssh/id_rsa.pub` (example path)

3. Add the public key to your github account

Now you can clone the repository using the ssh protocol:

```bash
git clone git@github.com:pedrodgp/devops-23-24-JPE-PSM-1231852.git
```

# Build and Execute the spring boot tutorial basic project (with maven), CA1

**1. Change to the directory of the project:**

This command changes the current directory to the project directory.

```bash
cd devops-23-24-JPE-PSM-1231852/CA1/tut-react-and-spring-data-rest/basic
```

**2. Change permissions of the mvnw file:**

This command changes the permissions of the mvnw file to make it executable.

```bash
chmod +x mvnw
```

**3. Build the project:**

This command uses Maven's wrapper script (mvnw) to clean any previous build artifacts and then build the project,
packaging it into an executable jar file.

```bash
./mvnw clean package
```

**4. Run the project:**

This command uses Maven's wrapper script to run the Spring Boot application.
**As defined in the project, the server will be started on port `8080`.**

```bash
./mvnw spring-boot:run
```

**But we can define the port we want to use as an argument (e.g. `8181`) like this:**

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8181
```

**5. Access the project:**

Open a browser and access the URL [http://192.168.56.5:8080/](http://192.168.56.5:8080/)
**change the port if you used a different one.**

# Build and Execute gradle_basic_demo project, CA2 p1

For this project, a chat server and client were implemented using Java and Gradle.

**1. Change to the directory of the project from the root of the repository:**

This command changes the current directory to the project directory.

```bash
cd devops-23-24-JPE-PSM-1231852/CA2/p1/gradle_basic_demo
```

**2. Change permissions of the gradlew file:**

This command changes the permissions of the gradlew file to make it executable.

```bash
chmod +x gradlew
```

**3. Build the project:**

This command uses Gradle's wrapper script (gradlew) to clean any previous build artifacts and then build the project.

```bash
./gradlew clean build
```

**4. Run the Server:**

This command created from the previous assignment uses Gradle's wrapper script to run the server application.
As defined in the project, the server will be started on port `59001`.

```bash  
./gradlew runServer
```

But we can define the port we want to use as an argument (e.g. 8181) like this:

```bash
./gradlew runServer --args="8181"
```

**5. Run the Client:**

In your host machine (example), open a terminal and execute the following gradle task from the project's root directory,
but **remember to change the IP** (in the previous assignment we used localhost) and the port (in the previous
assignment we used 59001),
so the `./gradlew runClient` command cannot be used directly, we need to give the IP of the server and the port we have
chosen.

```bash
./gradlew runClient --args="192.313.313.2 8181"
```

**6. Run several clients:**

To run several clients, you just need to open more terminals and repeat the invocation of the runClient gradle task.


# Build and Execute the spring boot tutorial basic project (with gradle), CA2 p2

This project is the same as the first project but using Gradle instead of Maven.

**1. Change to the directory of the project from the root of the repository:**

This command changes the current directory to the project directory.

```bash
cd devops-23-24-JPE-PSM-1231852/CA2/Part2/react-and-spring-data-rest-basic
```

**2. Change permissions of the gradlew file:**

This command changes the permissions of the gradlew file to make it executable.

```bash
chmod +x gradlew
```

**3. Build the project:**

This command uses Gradle's wrapper script (gradlew) to clean any previous build artifacts and then build the project.

```bash
./gradlew clean build
```

**5. Run the project:**

This command uses Gradle's wrapper script to run the Spring Boot application.
**As defined in the project, it will be started on port `8080`**.

```bash
./gradlew bootRun
```

But we can define the port we want to use as an argument (e.g. `8181`) like this:

```bash
./gradlew bootRun --args="--server.port=8181"
```

**6. Access the project:**

Open a browser and access the URL [http://192.168.56.5:8080/](http://192.168.56.5:8080/)
**change the port if you used a different one.**


# Conclusion

In conclusion, completing Class Assignment 3, Part 1 provided a comprehensive understanding of setting up and managing a
virtual development environment, which is essential for any DevOps workflow. The tasks involved in this assignment, from
configuring a VirtualBox VM with Ubuntu to building and running complex Java applications using Maven and Gradle, have
enhanced my practical skills in software development and deployment. This experience has not only solidified my
understanding of DevOps principles but also prepared me for more advanced challenges in the field. The successful
execution of the projects within the newly configured environment demonstrates the effectiveness of integrating
development and operations, which is at the heart of DevOps practices.
