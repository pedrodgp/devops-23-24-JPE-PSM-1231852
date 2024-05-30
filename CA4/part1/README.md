# Technical Report - Class Assignment 4 - Part 1

### DevOps 23-24 JPE-PSM

#### Pedro Pires, 1231852

## Index

1. [Introduction](#introduction)

2. [Getting Started](#getting-started)

3. [What is Docker](#what-is-vagrant)

4. [Version 1 - Build the Chat Server "Inside" the Dockerfile](#version-1---build-the-chat-server-inside-the-dockerfile)

5. [Version 2 - Build the Chat Server in Host and Copy the jar "Into" the Dockerfile](#version-2---build-the-chat-server-in-host-and-copy-the-jar-into-the-dockerfile)

6. [Conclusion](#conclusion)



# # Introduction

In this assignment, I explore how to use Docker to containerize a chat server application and demonstrate two methods to
build and deploy the chat server application using Docker.

The first method involves building the application inside the Docker container, while the second method builds the
application on the host machine
and then copies the built JAR file into the Docker container.

# What is Docker

Docker is an open-source platform that automates the deployment of applications inside lightweight, portable containers.
Containers package the application code along with its dependencies, ensuring that it runs seamlessly across different
computing environments. Docker uses images as the blueprint for containers, and these images can be stored and shared
through Docker Hub or other container registries.

Common Docker commands include:

- `docker build`: Builds an image from a Dockerfile.
- `docker run`: Runs a container from an image.
- `docker ps`: Lists running containers.
- `docker stop`: Stops a running container.
- `docker rm`: Removes a stopped container.
- `docker images`: Lists all images.
- `docker rmi`: Removes an image.
- `docker push`: Pushes an image to a container registry.
- `docker pull`: Pulls an image from a container registry.

# Getting Started

To follow the steps in this assignment, you need to have Docker installed on your machine. You can download Docker from
the official website: [Docker](https://www.docker.com/).
And you must clone the repository to your machine:
    
```bash
git clone https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852.git
```

All the commands in this assignment will be made in the **terminal in the root directory of the project**.


# Version 1 - Build the Chat Server "Inside" the Dockerfile

### Dockerfile:

This Dockerfile builds the chat server application inside the Docker container using the Gradle wrapper. The application
is built in the first stage of the Dockerfile, and the built JAR file is copied to the final image in the second stage.

```Dockerfile
# Stage 1: Build the application
# Use a larger image for the builder stage
FROM gradle:jdk21 as builder
LABEL author="Pedro Pires, 1231852"

# Set the working directory
WORKDIR /ca4-part1/devops-23-24-JPE-PSM-1231852/CA2/Part1/gradle_basic_demo

# Copy the repository into the container
COPY CA2/part1/gradle_basic_demo .

# Ensure Gradle wrapper has execution permissions
RUN chmod +x gradlew

# Build the application
CMD ["./gradlew", "build"]

# Stage 2: Create the final image with the built JAR
# Use a smaller image for the final image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /ca4-part1

# Copy the built JAR from the builder stage
COPY --from=builder /ca4-part1/devops-23-24-JPE-PSM-1231852/CA2/Part1/gradle_basic_demo/build/libs/*.jar ca4-part1.jar

# Specify the entry point for the application
ENTRYPOINT ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]
```

### Explanation of Dockerfile

1. **First Stage:**
   ```Dockerfile
   FROM gradle:jdk17 as builder
   LABEL author="Pedro Pires, 1231852"

   WORKDIR /CA4-part1
   ```
    - `FROM gradle:jdk21 as builder`: This line sets the base image to `gradle:jdk21` and names this stage `builder`.
    - `WORKDIR /ca4-part1(...)`: This line sets the working directory inside the container to `/ca4-part1(...)`. If this directory
      doesn't exist, Docker will create it.

   ```Dockerfile
   COPY CA2/part1/gradle_basic_demo .
   ```
    - This command copies the contents of the `CA2/part1/gradle_basic_demo` directory on the host machine to the current
      directory in the container.

   ```Dockerfile
   RUN chmod +x gradlew
   CMD ["./gradlew", "build"]
   ```
     - `RUN chmod +x gradlew`: This command gives execution permissions to the Gradle wrapper script.
     - `CMD ["./gradlew", "build"]`: This command runs the Gradle wrapper script to build the application.
   

2. **Second Stage:**
   ```Dockerfile
   FROM openjdk:21-jdk-slim

   WORKDIR /ca4-part1
   ```
    - `FROM openjdk:21-jdk-slim`: This line sets the base image to `openjdk:21-jdk-slim` for the final stage.
    - `WORKDIR /ca4-part1`: This sets the working directory inside the container to `/ca4-part1`.

   ```Dockerfile
   COPY --from=builder /ca4-part1/devops-23-24-JPE-PSM-1231852/CA2/Part1/gradle_basic_demo/build/libs/*.jar ca4-part1.jar
   ```
    - `COPY --from=builder /ca4-part1/devops-23-24-JPE-PSM-1231852/CA2/Part1/gradle_basic_demo/build/libs/*.jar ca4-part1.jar`: This copies the built JAR file
      from the `builder` stage to the `/ca4-part1` directory inside the final stage container.

    ```Dockerfile
    CMD ["java", "-jar", "ca4-part1.jar"]
    ```
    - `CMD ["java", "-jar", "ca4-part1.jar"]`: This command runs the JAR file in the container when the container is
      started.

### Build the Docker Image

In this section we will make the commands in the **terminal in the root directory of the project**.

1. **Build the Docker Image:**
   ```bash
   docker build -f CA4/part1/Dockerfile_v1 -t ca4-part1:v1 .
   ```
    - The `-f` flag is used to specify the Dockerfile to use and `CA4/part1/Dockerfile_v1` is the path to the Dockerfile.
    - The `-t` flag is used to tag the image.
    - The `ca4-part1:v1` tag is used to name the image.
    - The `.` at the end of the command specifies the build context as the current directory.

### Run the Docker Container and Test the Chat Server

1. **Run the Docker Container:**

   ```bash
   docker run -it --rm -p 59001:59001 ca4-part1:v1
   ```
    - The `-it` flag is used to run the container interactively.
    - The `--rm` flag is used to remove the container when it exits, which is useful for temporary containers.
    - The `-p 59001:59001` flag is used to map port 59001 on the host machine to port 59001 in the container.
    - This command runs the Docker container interactively with the image `ca4-part1:v1`.


2. **Run the clients**:

You must build the application in the host machine to run the clients. And after that, you can run the clients using different terminals for each client.
Use the following command to run the client:

   ```bash
   ./gradlew runClient
   ```

You should now have similar results as in the next image:

![Chat Server V1](https://i.ibb.co/DDQPsdm/docke-img1.png)

### Push the Docker Image to Docker Hub:

   ```bash
   docker login
   docker tag ca4-part1:v1 pdgpires/gradle_basic_demo:v1
   docker push pdgpires/gradle_basic_demo:v1
   ```
   - The `docker login` command is used to log in to Docker Hub.
   - The `docker tag` command is used to tag the image with the Docker Hub username and repository name.
   - The `docker push` command is used to push the image to Docker Hub.

Now it is possible to run the image from Docker Hub using the following command:

```bash
docker run -it --rm -p 59001:59001 pdgpires/gradle_basic_demo:v1
```

# Version 2 - Build the Chat Server in Host and Copy the jar "Into" the Dockerfile

In this version, the chat server application is built on the host machine using the Gradle wrapper.**The built JAR file
is then copied into the Docker container using the Dockerfile.**

### Build the Chat Server in Host Machine

Clone and build the Chat Server Application to generate the JAR file on the host machine:

   ```bash
   cd devops-23-24-JPE-PSM-1231852/CA2/Part1/gradle_basic_demo
   ./gradlew build
  ```
   - `cd gradle_basic_demo`:
       - This command changes the current directory to `gradle_basic_demo`.
   - `./gradlew build`:
       - This command builds the project using Gradle.
   - After, go back to the root directory of the project using the command `cd ../../..`.

### Explanation of Dockerfile

#### Dockerfile:
```Dockerfile
# Use a base image with JDK installed
FROM openjdk:21-jdk-slim
LABEL author="Pedro Pires, 1231852"

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the host machine to the container
COPY CA2/part1/gradle_basic_demo/build/libs/basic_demo-0.1.0.jar app.jar

# Expose the port that the application will use
EXPOSE 59001

# Specify the entry point for the application
ENTRYPOINT ["java", "-cp", "app.jar", "basic_demo.ChatServerApp", "59001"]

```

1. **`FROM openjdk:21-jdk-slim`:**
    - This line sets the base image to `openjdk:21-jdk-slim`.
2. **`WORKDIR /app`:**
    - This line sets the working directory inside the container to `/app`.
3. **`COPY CA2/part1/gradle_basic_demo/build/libs/basic_demo-0.1.0.jar app.jar`:**
    - This line copies the JAR file from the host machine to the `/app` directory inside the container.
    - The path `CA2/part1/gradle_basic_demo/build/libs/basic_demo-0.1.0.jar` should be updated based on the location of
      the built JAR file on the host machine.
4. **`EXPOSE 59001`:**
    - This line exposes port 59001 in the container.
    - This is the port that the chat server listens on.
5. **`ENTRYPOINT ["java", "-cp", "app.jar", "basic_demo.ChatServerApp", "59001"]`:**
    - This line specifies the entry point for the application.
        - When the container starts, it runs the JAR file `app.jar` with the main class `basic_demo.ChatServerApp` and
          port `59001`.

### Build the Docker Image

In this section we will make the commands in the **terminal in the root directory of the project**.

1. **Build the Docker Image:**
   ```bash
   docker build -f CA4/part1/Dockerfile_v2 -t ca4-part1:v2 .
   ```
   - The `-f` flag is used to specify the Dockerfile to use and `CA4/part1/Dockerfile_v2` is the path to the Dockerfile.
   - The `-t` flag is used to tag the image.
   - The `ca4-part1:v2` tag is used to name the image.
   - The `.` at the end of the command specifies the build context as the current directory.

### Run the Docker Container and Test the Chat Server

1. **Run the Docker Container:**

   ```bash
   docker run -it --rm -p 59001:59001 ca4-part1:v2
   ```
   - The `-it` flag is used to run the container interactively.
   - The `--rm` flag is used to remove the container when it exits, which is useful for temporary containers.
   - The `-p 59001:59001` flag is used to map port 59001 on the host machine to port 59001 in the container.
   - This command runs the Docker container interactively with the image `ca4-part1:v2`.


2. **Run the clients**:

You must build the application in the host machine to run the clients. And after that, you can run the clients using different terminals for each client.
Use the following command to run the client:

   ```bash
   ./gradlew runClient
   ```

You should now have similar results as in the next image:

![Chat Server V2](https://i.ibb.co/yp9YJfW/docke-img2.png)

### Push the Docker Image to Docker Hub:

   ```bash
   docker login
   docker tag ca4-part1:v2 pdgpires/gradle_basic_demo:v2
   docker push pdgpires/gradle_basic_demo:v2
   ```
- The `docker login` command is used to log in to Docker Hub.
- The `docker tag` command is used to tag the image with the Docker Hub username and repository name.
- The `docker push` command is used to push the image to Docker Hub.

Now it is possible to run the image from Docker Hub using the following command:

```bash
docker run -it --rm -p 59001:59001 pdgpires/gradle_basic_demo:v2
```

# Conclusion

In this assignment, I demonstrated two approaches to containerizing a chat server application using Docker. The first
approach builds the application inside the Docker container, ensuring that the build process is encapsulated within the
container environment. The second approach builds the application on the host machine and copies the built JAR file into
the container, which can be more efficient for development workflows.

By following these methods, I have leveraged Docker's capabilities to create portable and consistent environments for
the applications, making it easier to develop, test, and deploy software across different systems. This exercise
highlights the flexibility and power of Docker in modern DevOps practices.
