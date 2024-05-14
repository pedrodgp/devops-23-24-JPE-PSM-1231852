# Technical Report - Class Assignment 3 - Part 2

### DevOps 23-24 JPE-PSM

#### Pedro Pires, 1231852

# Introduction

In this technical report, I developed the second part of the Class Assignment 3 (CA3) for the DevOps course. The
assignment consists of deploying a Spring Boot application and an H2 database server within virtualized environments
using Vagrant and Oracle VM VirtualBox.
With a focus on automation, we explore the steps required to configure the Vagrantfile, update software versions, and
enable local access to web applications and the H2 console through port forwarding.

Furthermore, I present an alternative solution leveraging Hyper-V as the hypervisor, highlighting its
comparative advantages and demonstrate how Hyper-V seamlessly integrates with Vagrant to achieve the same
deployment goals outlined in the assignment using Oracle VM VirtualBox.

## Index

1. [Introduction](#introduction)


2. [What is Vagrant](#what-is-vagrant)
    - [Vagrantfile Configuration](#vagrantfile-configuration)
    - [Common Vagrant Commands](#common-vagrant-commands)
   

3. [Create a Vagrantfile to Set Up Two Virtual Machines](#create-a-vagrantfile-to-set-up-two-virtual-machines)
    - [Code Update - CA2: Part 2, Spring Boot App](#code-update---ca2-part-2-spring-boot-app)
    - [Vagrantfile Configuration](#vagrantfile-configuration-1)
    - [H2 Database Server](#h2-database-server)
    - [Tomcat Server](#tomcat-server)
    - [The Vagrantfile - Final Result](#the-vagrantfile---final-result)
   

4. [Alternative Solution: Using Hyper-V as a Hypervisor](#alternative-solution-using-hyper-v-as-a-hypervisor)
    - [What is Hyper-V?](#what-is-hyper-v)
    - [Comparing Hyper-V with VirtualBox](#comparing-hyper-v-with-virtualbox)
    - [Configuring Vagrant with Hyper-V for this Assignment](#configuring-vagrant-with-hyper-v-for-this-assignment)
    - [The Vagrantfile - Final Result for Hyper-V](#the-vagrantfile---final-result-for-hyper-v)
    - [Running the Vagrantfile with Hyper-V](#running-the-vagrantfile-with-hyper-v)
   

5. [Conclusion](#conclusion)

# What is Vagrant

Vagrant is an open-source tool for building and managing virtual machine environments in a single workflow. It provides
easy-to-use commands to create, configure, and manage VMs, allowing developers to automate the setup of development
environments.

### Vagrantfile Configuration:

The Vagrantfile is a Ruby script that defines the configuration for the VMs. It specifies the base box, network
settings,
provisioning scripts, and other configurations required to set up the VMs. The Vagrantfile is used to define the
infrastructure as code, enabling consistent and reproducible environments.

### Common Vagrant Commands:

Vagrant provides a set of commands to manage VMs defined in the Vagrantfile. Some common commands include:

- `vagrant up`: Starts the VMs defined in the Vagrantfile.
- `vagrant halt`: Stops the VMs.
- `vagrant reload`: Restarts the VMs.
- `vagrant destroy`: Stops and deletes the VMs.
- `vagrant provision`: Re-runs the provisioning scripts defined in the Vagrantfile.
- `vagrant status`: Displays the status of the VMs.
- `vagrant global-status`: Displays the status of all Vagrant environments on the system.

# Create a Vagrantfile to Set Up Two Virtual Machines

The objective is to set up two virtual machines (VMs) with Ubuntu Bionic 64-bit OS. The first VM is for the H2 database
server, and the second VM is for the Tomcat server. Spring Boot application (at CA2, part 2) are deployed on the Tomcat
server, and the H2 database is used to store data.

## Code Update - CA2: Part 2, Spring Boot App

### App.js - Update

Updated the API request path within the `componentDidMount()` method to reflect the new context path specified in
the `application.properties`. This change ensures the client-side React application correctly fetches data from the
updated API endpoint.

- **Before**:

```Javascript
componentDidMount()
{ // <2>
    client({method: 'GET', path: '/api/employees'}).done(response => {
        this.setState({employees: response.entity._embedded.employees});
    });
}
```

- **After**:

```Javascript
componentDidMount()
{ // <2>
    client({method: 'GET', path: '/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT/api/employees'}).done(response => {
        this.setState({employees: response.entity._embedded.employees});
    });
}
```

### Application Properties - Update

Introduced additional configuration settings to `application.properties` to accommodate the changes in the application
deployment environment and database connectivity:

- Set a new context path for the server.
- Configured database connection details for the H2 database.
- Enabled and configured the H2 console for web access.

Should look like this:

```properties
server.servlet.context-path=/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT
spring.data.rest.base-path=/api
spring.datasource.url=jdbc:h2:tcp://192.168.56.11:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true
```

### Build.gradle - Update

- **Plugin Addition**: Added the `war` plugin to facilitate the generation of a WAR file, enabling deployment to a
  Tomcat server environment.

```gradle
plugins {
	// (...) ** REST OF THE PLUGINS **
	
	id 'war'
}
```

- **Dependency Changes**: Included `providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'` to ensure the
  embedded Tomcat server is not included in the WAR file, suitable for deployment in a standalone server setup.

```gradle
dependencies {
        // (...) ** REST OF THE DEPENDENCIES **

	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
}
```

### Index.html - Update

Removed the leading slash from the stylesheet link to ensure correct relative path resolution:

- **Before**: `<link rel="stylesheet" href="/main.css" />`
- **After**: `<link rel="stylesheet" href="main.css" />`

### ServletInitializer Class - Addition

Created `ServletInitializer.java` under `java/com/greglturnquist/payroll`, extending `SpringBootServletInitializer`.
This class is crucial for deploying Spring Boot applications as WAR files. It adapts the Spring Boot application to
the Servlet environment, enabling it to run in a conventional web server by configuring the application's sources.

- The `ServletInitializer` class is essential for deploying the Spring Boot application on a standalone Tomcat server
  in this setup.
- The `configure()` method specifies the application sources to be used for deployment, ensuring the Spring Boot
  application is correctly initialized and executed in the Servlet container.

The class should look like this:

```java
package com.greglturnquist.payroll;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReactAndSpringDataRestApplication.class);
    }
}
```

## Vagrantfile Configuration

Use this [Vagrantfile](https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/) as a base to create the
final Vagrantfile for this assignment.

### Update OpenJDK and H2 Database

To ensure our virtual environments are with the same software versions as the current ones,
I updated the OpenJDK version from 11 to 17 and the H2 database version from 1.4.200 to 2.2.224.

### H2 Database Server

The configuration line `db.vm.network "forwarded_port", guest: 8082, host: 8082` is used to set up
port forwarding between the virtual machine (VM) and the host machine.
This setting maps port 8082 on the guest VM (where your H2 database is running) to port
8082 on your host machine. By doing this, any traffic that comes to port 8082 on the host machine is automatically
forwarded to port 8082 on the guest VM.

#### Purpose and Use:

The main purpose of this configuration is to allow the access of the services running on the VM
directly from your host machine using the same port number. For this specific, this means it can be accessed by the H2
console running on the H2 database server inside the VM.

#### Accessing the Application:

Since the guest’s port 8082 (where H2 database server runs your application) is forwarded to the
host’s port 8082, you can access your application by visiting http://localhost:8082 in a web browser on the host
machine.
This URL points to the H2 console, allowing you to interact with your database as if it were running locally on your
host machine.

It should look like this after running `Vagrant up`:

![H2 Console](https://i.ibb.co/4g7VQPC/BD-vagrant-ca3-p2.png)

## Tomcat Server

The configuration line `web.vm.network "forwarded_port", guest: 8080, host: 8080` is used to set up
port forwarding between the virtual machine (VM) and the host machine.
This setting maps port 8080 on the guest VM (where your webserver is running) to port
8080 on your host machine. By doing this, any traffic that comes to port 8080 on the host machine is automatically
forwarded to port 8080 on the guest VM.

#### Purpose and Use:

The main purpose of this configuration is to allow the access of the services running on the VM
directly from your host machine using the same port number. For this specific, this means it can be accessed by the web
application running on the Tomcat server inside the VM.

#### Tomcat Server Execution:

   ```ruby
   web.vm.provision "shell", :run => 'always', inline: <<-SHELL
     ./apache-tomcat-10*/bin/startup.sh
   SHELL
   ```

This provision ensures that the Tomcat server is automatically initiated using the `startup.sh` script from the
Tomcat installation directory every time the virtual machine is started. This approach keeps the server running
continuously, enhancing availability and reliability.

#### Application Deployment:

   ```ruby
   web.vm.provision "shell", inline: <<-SHELL, privileged: false
     git clone https://github.com/pedrodgp/vagrant-test.git
     cd vagrant-test/CA3/react-and-spring-data-rest-basic
     chmod u+x gradlew
     ./gradlew clean build

     sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war ~/apache-tomcat-10*/webapps/
   SHELL
   ```

1. **Repository Management**: The script first clones a specific Git repository containing our application code.
2. **Building the Project**: It then navigates to the project directory, sets the necessary permissions on the Gradle
   wrapper, and executes a clean build. This step compiles the application and packages it into a WAR file.
3. **Deployment**: Finally, the WAR file is strategically copied to the Tomcat webapps directory to deploy the
4. application on the server.

This setup not only ensures that the Tomcat server is always ready to serve the application but also automates the
deployment process, making it efficient and reliable for continuous development and testing.

#### Accessing the Application:

Since the guest’s port 8080 (where Tomcat server runs your application) is forwarded to the
host’s port 8080, you can access your application by
visiting http://localhost:8080/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT (my example) in a web browser on the host
machine.
This URL points to the deployed WAR file on Tomcat, allowing you to interact with your web application as if it were
running locally on your host machine.

It should look like this after running `Vagrant up`:

![Web Application](https://i.ibb.co/d6sKr1t/spring-vagrant-ca3-p2.png)

## The Vagrantfile - Final Result

After all the configurations and provisions, the Vagrantfile is ready to be executed.
Using the `vagrant up` command, the two VMs will be created, and the H2 database server and Tomcat server will be
automatically set up and running.

In the Oracle VM VirtualBox Manager, you will see two VMs running: `db_h2_database` and `web_tomcat10_server`.

![Oracle VM VirtualBox Manager](https://i.ibb.co/tYK1hpb/VM-vagrant-ca3-p2.png)

Visiting the URLs http://localhost:8082 and http://localhost:8080/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT
will allow you to access the H2 console and the deployed Spring Boot application, respectively.

#### The final result of the Vagrantfile:

```ruby
Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/bionic64"
  config.ssh.insert_key = false

  # This provision is common for both VMs
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
        openjdk-17-jdk-headless
  SHELL

  # Configurations specific to the database VM
  config.vm.define "db" do |db|
    db.vm.box = "ubuntu/bionic64"
    db.vm.hostname = "db"
    db.vm.network "private_network", ip: "192.168.56.11"

    # We want to access H2 console from the host using port 8082
    # We want to connet to the H2 server using port 9092
    db.vm.network "forwarded_port", guest: 8082, host: 8082
    db.vm.network "forwarded_port", guest: 9092, host: 9092

    # We need to download H2
    db.vm.provision "shell", inline: <<-SHELL
      wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar 
    SHELL

    # The following provision shell will run ALWAYS so that we can execute the H2 server process
    # This could be done in a different way, for instance, setting H2 as as service
    #
    # To connect to H2 use: jdbc:h2:tcp://192.168.33.11:9092/./jpadb
    db.vm.provision "shell", :run => 'always', inline: <<-SHELL
      java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
    SHELL

    # Rename the VirtualBox VM
    db.vm.provider "virtualbox" do |v|
      v.name = "db_h2_database"
    end
  end

  #============
  # Configurations specific to the webserver VM
  config.vm.define "web" do |web|
    web.vm.box = "ubuntu/bionic64"
    web.vm.hostname = "web"
    web.vm.network "private_network", ip: "192.168.56.10"

    # We set more ram memmory for this VM
    web.vm.provider "virtualbox" do |v|
      v.memory = 1024
    end

    # We want to access tomcat from the host using port 8080
    web.vm.network "forwarded_port", guest: 8080, host: 8080

    # We need to download Tomcat10
    web.vm.provision "shell", inline: <<-SHELL
      wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.24/bin/apache-tomcat-10.1.24.tar.gz
      sudo tar xzvf apache-tomcat-10*tar.gz -C .
      sudo chown -R vagrant:vagrant apache-tomcat-10*
      sudo chmod -R u+x apache-tomcat-10* 
    SHELL

    # The following provision shell will run ALWAYS so that we can execute the Tomcat10 server process
    # This could be done in a different way, for instance, setting Tomcat10 as as service
    #
    web.vm.provision "shell", :run => 'always', inline: <<-SHELL
      ./apache-tomcat-10*/bin/startup.sh
    SHELL
    
    # Clone the repository and build the project
    web.vm.provision "shell", inline: <<-SHELL, privileged: false
      git clone https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852.git
      cd devops-23-24-JPE-PSM-1231852/CA2/Part2/react-and-spring-data-rest-basic
      chmod u+x gradlew
      ./gradlew clean build

      # To deploy the war file to tomcat10 do the following command:
      sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war ~/apache-tomcat-10*/webapps/
    SHELL

    # Rename the VirtualBox VM
    web.vm.provider "virtualbox" do |v|
      v.name = "web_tomcat10_server"
    end
  end
end
```

# Alternative Solution: Using Hyper-V as a Hypervisor

### What is Hyper-V?

Hyper-V is a native hypervisor developed by Microsoft, which allows users to create and run virtual machines on Windows
systems. As a type of platform virtualization, Hyper-V enables the host machine to support multiple, isolated virtual
environments, known as virtual machines (VMs). This technology is integrated into Windows as an optional feature in
versions such as Windows 10 Pro, Enterprise, and Education, as well as in Windows Server.

#### Key Features of Hyper-V:

* Hardware Virtualization: Hyper-V takes advantage of hardware virtualization capabilities provided by modern CPUs to
  deliver enhanced performance and security features.

* Multiple OS Support: It supports various operating systems on the guest VMs, allowing Windows, Linux, and others to
  run
  simultaneously on the same physical hardware.

* Live Migration: Users can move running VMs from one host to another without any downtime, enhancing flexibility in
  load
  balancing and maintenance without impacting system availability.

* Virtual Network Switches: It provides a rich set of networking capabilities, enabling virtual network switches,
  different levels of isolation, and connectivity configurations to emulate complex network topologies.

* Snapshots: Hyper-V allows taking snapshots of a running VM, capturing the complete state of the VM at a specific point
  in time. This is beneficial for backup, restore, and disaster recovery scenarios.

* Scalability and Resource Management:Hyper-V supports a robust set of features for managing virtual hardware resources,
  including CPU cores, memory, and storage adaptations, allowing for efficient scaling as demands grow.

### Comparing Hyper-V with VirtualBox:

When considering Hyper-V as an alternative to VirtualBox for deploying virtual environments, several factors should be
evaluated to determine the best fit for the specific use case:

1. **Performance**: Hyper-V generally provides better performance and utilizes hardware virtualization support more
   efficiently compared to VirtualBox, especially on Windows systems where it is integrated into the OS.

2. **Networking Features**: Hyper-V offers advanced networking features such as virtual network adapters and external
   network switches, which may provide more robust networking capabilities compared to VirtualBox.

3. **Management Tools**: Hyper-V comes with a strong set of management tools integrated with Windows, like the Hyper-V
   Manager for managing virtual machines. VirtualBox has a simpler, user-friendly interface but may lack some deeper
   configuration options available in Hyper-V.

4. **Snapshot and Save States**: Both VirtualBox and Hyper-V support snapshots; however, Hyper-V's snapshot management
   is integrated with Windows and might be more robust in handling complex snapshot operations.

5. **Linux Support**: VirtualBox generally has better support and performance with a wide range of Linux distributions
   compared to Hyper-V, which is more Windows-centric in its optimization.

### Configuring Vagrant with Hyper-V for this Assignment:

To use Hyper-V as the hypervisor with Vagrant, first ensure the Hyper-V is enabled on your Windows machine.

**Adjustments to the Vagrantfile**:

- **Provider Specific Options**: Configured `hyperv` as the provider with specific options like CPU and memory
  allocations.
- **Network Settings**: Defined private network IPs and port forwarding rules similar to those set in the VirtualBox
  configuration.
- **Provisioning Scripts**: Updated to ensure all necessary software and settings are properly configured for both the
  database and web server VMs.

### The Vagrantfile - Final Result for Hyper-V:

Here’s how it be adjusted the Vagrantfile for using Hyper-V and ensuring that all the previous setup requirements are
met:

```ruby
Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/bionic64"
  config.vm.provider "hyperv" do |h|
    h.cpus = 1
    h.memory = 1024
    h.linked_clone = true
  end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip openjdk-17-jdk-headless
  SHELL

  config.vm.define "db" do |db|
    db.vm.hostname = "h2-database"
    db.vm.network "private_network", ip: "192.168.56.11"
    db.vm.network "forwarded_port", guest: 8082, host: 8082
    db.vm.network "forwarded_port", guest: 9092, host: 9092

    db.vm.provision "shell", inline: <<-SHELL
      wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar
    SHELL

    db.vm.provision "shell", :run => 'always', inline: <<-SHELL
      java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
    SHELL
  end

  config.vm.define "web" do |web|
    web.vm.hostname = "tomcat-server"
    web.vm.network "private_network", ip: "192.168.56.10"
    web.vm.network "forwarded_port", guest: 8080, host: 8080

    web.vm.provision "shell", inline: <<-SHELL
      wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.24/bin/apache-tomcat-10.1.24.tar.gz
      tar xzvf apache-tomcat-10*.tar.gz -C .
      chmod u+x apache-tomcat-10*/bin/startup.sh
      ./apache-tomcat-10*/bin/startup.sh
    SHELL

    web.vm.provision "shell", inline: <<-SHELL, privileged: false
      git clone https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852.git
      cd devops-23-24-JPE-PSM-1231852/CA2/Part2/react-and-spring-data-rest-basic
      chmod u+x gradlew
      ./gradlew clean build

      sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war ~/apache-tomcat-10*/webapps/
    SHELL
  end
end
```

### Running the Vagrantfile with Hyper-V:

Open the terminal in **administrator mode** and navigate to the directory containing the Vagrantfile. Run the following
command to start the VMs using Hyper-V as the provider, note that the `--provider=hyperv` flag is used to specify the
hypervisor because the default is VirtualBox:

```
vagrant up --provider=hyperv
```

If everything is configured correctly, the VMs should be created, and the Spring Boot application and H2 database should
be deployed successfully in the Hyper-V environment.

Open the Hyper-V Manager to view the running VMs and access the services, you will see running: `db_h2_database`
and `web_tomcat10_server`.

![Hyper-V Manager](https://i.ibb.co/9bZny8p/hyper-v.png)

# Conclusion

In this report, we successfully implemented a virtualized environment using Vagrant, demonstrating the deployment of a
Spring Boot application alongside an H2 database. This exercise illustrated the value of automation and consistency in
development environments through the use of Vagrant with both Oracle VM VirtualBox and Hyper-V. By automating the setup
process, we enhanced reproducibility and efficiency, allowing developers to focus more on development tasks rather than
environment configuration. This project highlights the practical application of DevOps principles, ensuring that
environments are easily replicable and manageable, which is crucial for scalable and reliable software development.

