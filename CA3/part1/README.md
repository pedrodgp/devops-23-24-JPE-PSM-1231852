# Technical Report - Class Assignment 3 - Part 1

### DevOps 23-24 JPE-PSM

#### Pedro Pires, 1231852

## Introduction

This report outlines my work on Class Assignment 3, Part 1 for the DevOps course.

The repository can be found [here](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852).

## Index

1. [Getting Started](#getting-started)
    - [Step 1: Download and Install VirtualBox](#step-1-download-and-install-virtualbox)
    - [Step 2: Download Ubuntu](#step-2-download-ubuntu)
    - [Step 3: Initiate and configure a new VirtualBox](#step-3-initiate-and-configure-a-new-virtualbox)
2. [Build and Execute the Spring Boot Tutorial Basic Project, CA1](#build-and-execute-the-spring-boot-tutorial-basic-project-ca1)
3. [Build and Execute Gradle Basic Demo Project, CA2 p2](#build-and-execute-gradle-basic-demo-project-ca2-p2)
4. [Conclusion](#conclusion)

# Getting Started - Create VirtualBox/UTM VM with Ubuntu.

### Step 1: Download and Install VirtualBox

Download the latest version of VirtualBox from the official website [here](https://www.virtualbox.org/).

### Step 2: Download Ubuntu

Download the latest version of Ubuntu from the official website [here](https://ubuntu.com/download/server).

### Step 3: Initiate and configure a new VirtualBox

The VM needs 2048 MB RAM minimum, 1 CPU, 25 GB disk space (default), and 2 network adapters.
Enabled Network Adapter 2 as Host-only Adapter (vboxnet0) and check the IP address range of this network
(in this case it is 192.168.56.1/24).
We should select an IP in this range for the second adapter of our VM.

After starting the VM, log on into the VM and continue the setup:

1. Update the packages repositories

```bash
sudo apt update
```

2. Install the network tools:

```bash
sudo apt install net-tools
```

3. Edit the network configuration file to setup the IP

```bash
sudo nano /etc/netplan/01-netcfg.yaml
```

Make sure the contents of the file are similar to the following (in this case we are
setting the IP of the second adapter as 192.168.56.5):

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

4. Apply the new changes:

```bash
sudo netplan apply
```

5. Install openssh-server so that we can use ssh to open secure terminal sessions to the VM (from other hosts)

```bash
sudo apt install openssh-server
```

5. Enable password authentication for ssh

```bash
sudo nano /etc/ssh/sshd_config
```

Uncomment the line `PasswordAuthentication yes`

6. Restart the ssh service

```bash
sudo service ssh restart
```

7. Install an ftp server so that we can use the FTP protocol to transfers files to/from the VM (from other hosts)

```bash
sudo apt install vsftpd
```

8. Enable write access for vsftpd

```bash
sudo nano /etc/vsftpd.conf
```

Uncomment the line `write_enable=YES`.

9. Restart the vsftpd service

```bash
sudo service vsftpd restart
```

10. Install git

```bash
sudo apt install git
```

11. Install Java 17

```bash
sudo apt install openjdk-17-jdk-headless
```

Since the SSH server is enabled in the VM we can now use ssh to connect to the VM.
In the host (Windows or Linux or OSX), in a terminal/console, type:

```bash
ssh pedro@192.168.56.5
```

* Where pedro should be replaced by the user name of the VM
* Where 192.168.56.5 should be replaced by the IP of the VM

# Make a clone of the repository

```bash
git clone https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852.git
```

Note: You may need to login to your github account to be able to perform the clone operation.

Or you can use the **ssh protocol** to clone the repository.

1. Generate a new ssh key pair

```bash
ssh-keygen -C
```

2. Copy the content of the public key

```bash
cat /home/pedro/.ssh/id_rsa.pub
```

3. Add the public key to your github account

Now you can clone the repository using the ssh protocol:

```bash
git clone git@github.com:pedrodgp/devops-23-24-JPE-PSM-1231852.git
```

# Build and Execute the spring boot tutorial basic project, CA1




# Conclusion

