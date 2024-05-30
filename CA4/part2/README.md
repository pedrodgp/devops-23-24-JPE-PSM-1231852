# Technical Report - Class Assignment 4 - Part 1

### DevOps 23-24 JPE-PSM

#### Pedro Pires, 1231852

## Index

1. [Introduction](#introduction)
2. [What is Docker Compose](#what-is-docker-compose)
3. [Create a Docker Web Application](#create-a-docker-web-application)
   4. [Create the Dockerfile Web](#create-the-dockerfile-web)
   5. [Create the Dockerfile Database](#create-the-dockerfile-database)
   6. [Create the docker-compose.yml File](#create-the-docker-compose.yml-file)
   7. [Build, Run, Tag, and Push the Docker Image](#build-run-tag-and-push-the-docker-image)
8. [Comparison with Kubernetes](#comparison-with-kubernetes)
9. [Conclusion](#conclusion)

# # Introduction

In this technical report, we will explore how to use Docker Compose to define and run multi-container Docker applications.
We will create a Docker Compose file that defines two services: a database service and a web service. The database service
will use the H2 database server, and the web service will use the Tomcat server to deploy a Spring Boot application. We
will also explain how to build, run, tag, and push the Docker images to Docker Hub.
Additionally, we will compare Docker Compose with Kubernetes, highlighting the differences in features and use cases.

# What is Docker Compose

Docker Compose is a tool for defining and running multi-container Docker applications. It allows you to define a
multi-container application in a single file, called `docker-compose.yml`, and then use that file to create and manage
the application with a single command.

Common Docker compose commands:

- `docker-compose build`: This command builds the images defined in the `docker-compose.yml` file.
- `docker-compose up`: This command creates and starts the containers defined in the `docker-compose.yml` file.
- `docker-compose down`: This command stops and removes the containers defined in the `docker-compose.yml` file.
- `docker-compose ps`: This command lists the containers defined in the `docker-compose.yml` file.
- `docker-compose logs`: This command shows the logs of the containers defined in the `docker-compose.yml` file.

# Create a Docker Web Application

## Create the Dockerfile Web

 ```Dockerfile
## Use the official Tomcat base image with JDK 21
FROM tomcat:10.1-jdk21

# Copy the Spring Boot WAR file to the Tomcat webapps directory
COPY CA2/part2/react-and-spring-data-rest-basic/build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
 ```

### Explanation

- `FROM tomcat:10.1-jdk21`: This line specifies the base image for the Docker container. In this case, we are using the
  official Tomcat base image with JDK 21.
- `COPY CA2/part2/react-and-spring-data-rest-basic/build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war
  /usr/local/tomcat/webapps/`: This line copies the Spring Boot WAR file to the Tomcat webapps directory.
- `EXPOSE 8080`: This line exposes the default Tomcat port.
- `CMD ["catalina.sh", "run"]`: This line starts Tomcat.

**Note:** In this example, we are using the Spring Boot WAR file generated in Class Assignment 2 - Part 2, you should
build
the WAR file before running the Docker Compose commands.

## Create the Dockerfile Database

```Dockerfile
# Dockerfile for H2 Database Server
# Use the official Ubuntu base image
FROM ubuntu

# Install Java 8, unzip and wget
RUN apt-get update && \
  apt-get install -y openjdk-21-jdk-headless && \
  apt-get install unzip -y && \
  apt-get install wget -y

# Set the working directory
WORKDIR /usr/src/app/

# Download the H2 database server
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar

# Expose the ports
EXPOSE 8082
EXPOSE 9092

# Run the H2 server
CMD ["java", "-cp", "h2-1.4.200.jar", "org.h2.tools.Server", "-web", "-webAllowOthers", "-tcp", "-tcpAllowOthers", "-ifNotExists"]
```  

### Explanation

- `FROM ubuntu`: This line specifies the base image for the Docker container. In this case, we are using the official
  Ubuntu base image.
- `RUN apt-get update && \ apt-get install -y openjdk-21-jdk-headless && \ apt-get install unzip -y && \ apt-get install
  wget -y`: This line installs Java 8, unzip, and wget.
- `WORKDIR /usr/src/app/`: This line sets the working directory.
- `RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar`: This line downloads the H2
  database server.
- `EXPOSE 8082 EXPOSE 9092`: This line exposes the ports.
- `CMD ["java", "-cp", "h2-1.4.200.jar", "org.h2.tools.Server", "-web", "-webAllowOthers", "-tcp", "-tcpAllowOthers",
  "-ifNotExists"]`: This line runs the H2 server.

## Create the docker-compose.yml File

```yaml
version: '3.7'
services:
  db:
    build:
      context: .
      dockerfile: Dockerfile-db
    container_name: CA4_Part2_db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - h2-data:/opt/h2-data
    networks:
      CA4_network:
        ipv4_address: 192.168.56.11

  web:
    build:
      context: ../..
      dockerfile: CA4/part2/Dockerfile-web
    container_name: CA4_Part2_web
    ports:
      - "8080:8080"
    networks:
      CA4_network:
        ipv4_address: 192.168.56.10
    depends_on:
      - "db"

volumes:
  h2-data:
    driver: local

networks:
  CA4_network:
    ipam:
      driver: default
      config:
        - subnet: 192.168.56.0/24
```

### Explanation

- `version: '3.7'`: This line specifies the version of the Docker Compose file.

### Services: This section defines the services that make up the application.

In Docker Compose, a service defines a container that will be managed by Docker Compose. Each service can specify
details such as the Docker image to use, environment variables, volumes, networks, and other configuration options.
In this example, we have two services: `db` and `web`.

- `services`: This section defines the services that make up the application.

### Database (db): This section defines the database service.

Here we are using the official Ubuntu base image to create the database service.

- `build`: This section specifies the build context and Dockerfile for the database service.
    - `context: .`: This line specifies the build context.
    - `dockerfile: Dockerfile-db`: This line specifies the Dockerfile.
- `container_name: CA4_Part2_db`: This line specifies the name of the container.
- `ports`: This line specifies the ports to expose on the host machine.
    - `"8082:8082"`: This line maps port 8082 on the host machine to port 8082 on the container.
    - `"9092:9092"`: This line maps port 9092 on the host machine to port 9092 on the container.
- `volumes`: This line specifies the volumes to mount.
    - `h2-data:/opt/h2-data`: This line mounts the `h2-data` volume to the `/opt/h2-data` directory in the container.
    - `networks`: This line specifies the networks to connect to.
        - `CA4_network`: This line specifies the network in this case we are using the `CA4_network`.

### Web: This section defines the web service.

Here we are using the official Tomcat base image to create the web service.

- `build`: This section specifies the build context and Dockerfile for the web service.
    - `context: ../..`: This line specifies the build context.
    - `dockerfile: CA4/part2/Dockerfile-web`: This line specifies the Dockerfile.
- `container_name: CA4_Part2_web`: This line specifies the name of the container.
- `ports`: This line specifies the ports to expose on the host machine.
    - `"8080:8080"`: This line maps port 8080 on the host machine to port 8080 on the container.
- `networks`: This line specifies the networks to connect to.
    - `CA4_network`: This line specifies the network in this case we are using the `CA4_network`.
- `depends_on`: This line specifies the services that this service depends on.
    - `"db"`: This line specifies that the web service depends on the db service.
- `volumes`: This line specifies the volumes to mount.
    - `h2-data:/opt/h2-data`: This line mounts the `h2-data` volume to the `/opt/h2-data` directory in the container.
    - `networks`: This line specifies the networks to connect to.
        - `CA4_network`: This line specifies the network in this case we are using the `CA4_network`.

### Volumes: This section defines the volumes to create.

To ensure that data is persisted between container restarts when using Docker Compose, you need to define and use
volumes. Volumes allow data to be stored outside of the container's filesystem, making it persistent even if the
container is stopped or removed. In this example, we are using a local volume driver to create the `h2-data` volume.

- `h2-data`: This line specifies the volume to create.
- `driver: local`: This line specifies the local volume driver.

### Networks: This section defines the networks to create.

Using networks in Docker Compose allows you to define how your services communicate with each other. By default, Docker
Compose creates a default network for all the services specified in a docker-compose.yml file, but you can also define
custom networks for more control over the network configuration. In this example, we are using a custom network called
`CA4_network` and specifying the subnet for the network.

- `CA4_network`: This line specifies the network in this case we are using the `CA4_network`.
    - `ipam`: This line specifies the IP address management driver.
        - `driver: default`: This line specifies the default IP address management driver.
        - `config`: This line specifies the network configuration.
            - `subnet: 192.168.56.0/24`: This line specifies the subnet for the network.

## Build, Run, Tag, and Push the Docker Image

### Build the Docker Image

In this section we will make the commands in the **terminal in the root directory of the project**.
 
```bash
   docker-compose -f CA4/part2/docker-compose.yaml build
   ```

- The `-f` flag is used to specify the location of the Docker Compose file.
- This command builds the Docker images defined in the `docker-compose.yml` file.
- `build` is the command to build the images.~~

### Run the Docker Containers

```bash
docker-compose -f CA4/part2/docker-compose.yaml up
```
- The `-f` flag is used to specify the location of the Docker Compose file.
- This command creates and starts the Docker containers defined in the `docker-compose.yml` file.
- `up` is the command to create and start the containers.

You should now have similar results as in the next image:

![app](https://i.ibb.co/QdTX1Vy/devops-p2.png)

### Push the Docker Image to Docker Hub:

   ```bash
    docker login
    docker tag ca4_part2_web:latest pedrospires96/ca4_part2_web:latest
    docker push pedrospires96/ca4_part2_web:latest
   ```

### Push the Docker Image to Docker Hub:

After building and verifying your Docker images locally, the next step is to push these images to Docker Hub. This
allows you to share your images with others or use them in different environments. Follow these steps to tag and push
your Docker images to Docker Hub:

1. **Log in to Docker Hub:**
   ```bash
   docker login
   ```
    - This command prompts you to enter your Docker Hub username and password. If you don't have an account, you need to
      create one at [Docker Hub](https://hub.docker.com/).


2. **Tag the Docker images:**

    ```bash
    docker tag part2-web:latest pdgpires/ca4_p2_web:v1.0
    docker tag part2-db:latest pdgpires/ca4_p2_db:v1.0
    ```
    - The first command tags the web service image with your Docker Hub username and the desired version number.
    - The second command tags the database service image with your Docker Hub username and the desired version number.


3. **Push the Docker images to Docker Hub:**

      ```bash
      docker push pdgpires/ca4_p2_web:v1.0
      docker push pdgpires/ca4_p2_db:v1.0
      ```
    - The first command pushes the web service image to Docker Hub.
    - The second command pushes the database service image to Docker Hub.


4. **Pull the Docker images from Docker Hub:**

    ```bash
    docker pull pdgpires/ca4_p2_web
    docker pull pdgpires/ca4_p2_db
    ``` 

    - After running these commands, your images will be available on Docker Hub, and you can pull them from any
      environment by
      using the `docker pull` command.

This completes the process of pushing your Docker images to Docker Hub, making them accessible for deployment in various
environments.


# Comparison with Kubernetes

## Introduction to Kubernetes

Kubernetes (often abbreviated as K8s) is an open-source container orchestration platform that automates many of the
manual processes involved in deploying, managing, and scaling containerized applications. While Docker Compose is
designed for simpler, single-host setups, Kubernetes is designed for complex, multi-host environments and provides a
more robust and flexible approach to container orchestration.

## Comparison of Features

### Docker Compose

- **Ease of Use**: Simple to set up and use for small to medium-sized applications.
- **Configuration**: Uses a single `docker-compose.yml` file to define services, networks, and volumes.
- **Deployment**: Suitable for development, testing, and small-scale production deployments.
- **Networking**: Basic networking capabilities.
- **Scaling**: Limited scaling capabilities compared to Kubernetes.
- **Monitoring and Logging**: Basic logging and monitoring capabilities.

### Kubernetes

- **Ease of Use**: More complex to set up and use, but offers more powerful features.
- **Configuration**: Uses multiple configuration files (`Deployment`, `Service`, `Config
Map`, etc.) to define resources.

- **Deployment**: Suitable for large-scale production deployments with complex requirements.
- **Networking**: Advanced networking capabilities, including service discovery and load balancing.
- **Scaling**: Robust and automated scaling capabilities (horizontal and vertical scaling).
- **Monitoring and Logging**: Advanced monitoring and logging capabilities through integration with tools like
  Prometheus, Grafana, and ELK stack.

### Using Kubernetes to Achieve Similar Goals

To achieve the same goals presented in this assignment using Kubernetes, follow these steps:

1. **Create Kubernetes Deployment and Service for the Web Application named `web-deployment.yaml`**

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: web-deployment
    spec:
      replicas: 3
      selector:
        matchLabels:
          app: web
      template:
        metadata:
          labels:
            app: web
        spec:
          containers:
            - name: web
              image: pdgpires/ca4_p2_web:v1.0
              ports:
                - containerPort: 8080
    ---
    apiVersion: v1
    kind: Service
    metadata:
      name: web-service
    spec:
      selector:
        app: web
      ports:
        - protocol: TCP
          port: 80
          targetPort: 8080
      type: LoadBalancer
    ```

2. **Create Kubernetes Deployment and Service for the Database named `db-deployment.yaml`**

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: db-deployment
    spec:
      replicas: 1
      selector:
        matchLabels:
          app: db
      template:
        metadata:
          labels:
            app: db
        spec:
          containers:
            - name: db
              image: pdgpires/ca4_p2_db:v1.0
              ports:
                - containerPort: 8082
                - containerPort: 9092
    ---
    apiVersion: v1
    kind: Service
    metadata:
      name: db-service
    spec:
      selector:
        app: db
      ports:
        - protocol: TCP
          port: 8082
          targetPort: 8082
        - protocol: TCP
          port: 9092
          targetPort: 9092
      type: ClusterIP
    ```

3. **Apply the Kubernetes Configurations**

    ```bash
    kubectl apply -f web-deployment.yaml
    kubectl apply -f db-deployment.yaml
    ```

By using Kubernetes, you can leverage its advanced features to manage and scale your applications more efficiently.
Kubernetes provides a robust and flexible platform for deploying, managing, and scaling containerized applications in
production environments.


# Conclusion

In this technical report, we have explored how to use Docker Compose to define and run multi-container Docker
applications.
We have created a Docker Compose file that defines two services: a database service and a web service. We have also
explained how to build and run the Docker containers using Docker Compose commands. Finally, we have demonstrated how to
push the Docker images to Docker Hub, making them accessible for deployment in different environments.

We have also compared Docker Compose with Kubernetes, highlighting the differences in features and use cases. While
Docker Compose is suitable for simpler, single-host setups, Kubernetes is designed for complex, multi-host environments
and provides more advanced container orchestration capabilities.

In conclusion, Docker Compose is a valuable tool for defining and running multi-container applications in a simple and
efficient way. However, for more complex and scalable deployments, Kubernetes can provide a more robust and flexible
solution. By understanding the strengths and limitations of each tool, you can choose the right tool for your specific
use case and requirements.

