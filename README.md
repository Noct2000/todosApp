# Todos App

## Description

The Todos app is a simple example project that utilizes Thymeleaf for showing web pages and MySQL DB for data storage. It can be run in two ways: as a Java (JAR file) application or as a native executable file in an Oracle Linux environment.

**Note**: The following instructions assume that you have GNU Make installed, but you can also execute the commands directly from the Makefile. The app has been tested on Linux and macOS environments.

### Run as a JAR

#### Estimated time to complete: 5 minutes

1. Create the .env file by copying the content from .env_sample:

   ```shell
   make setup
   ```

2. Build the JAR file and Docker image:

   ```shell
   make build
   ```

3. Run the application:

   ```shell
   make up
   ```

4. Check the application health by visiting [http://localhost:8081/list-todos](http://localhost:8081/list-todos) with the following credentials:

   ```
   Username: user
   Password: password
   ```

### Run as a Native Image

#### Estimated time to complete: 35 minutes

1. Create the .env file by copying the content from .env_sample:

   ```shell
   make setup
   ```

2. Build the native executable file and Docker image for the native executable:

   ```shell
   make native-build
   ```

3. Run the native application:

   ```shell
   make native-up
   ```

4. Check the application health by visiting [http://localhost:8081/list-todos](http://localhost:8081/list-todos) with the following credentials:

   ```
   Username: user
   Password: password
   ```

## Useful Commands

Here are some useful commands for different scenarios:

- Build the JAR directly on your machine (requires Java 17):

  ```shell
  ./mvnw clean package
  ```

- Run the Java app directly on your machine (requires Java 17):

  ```shell
  ./mvnw spring-boot:run
  ```

- Compile Java to a binary executable file (requires GraalVM CE 17.0.7+7.1 and native-image util):

  ```shell
  ./mvnw native:compile -Pnative
  ```

- Build a native Docker image with Cloud Native Buildpacks (requires GraalVM CE 17.0.7+7.1 and native-image util):

  ```shell
  ./mvnw spring-boot:build-image
  ```

- Run the native Docker image (Cloud Native Buildpacks):

  ```shell
  docker run --rm -p 8081:8081 docker.io/library/todos-app:0.0.1-SNAPSHOT
  ```

- Stop the web app with Docker container removal:

  ```shell
  make down # or make native-down
  ```

- Stop the web app without Docker container removal:

  ```shell
  make stop # or make native-stop
  ```

- Remove Docker containers, images, and volumes:

  ```shell
  make clear
  ```

