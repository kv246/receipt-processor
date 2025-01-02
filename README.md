# Spring Boot Dockerized Receipt WebApp

This is a Spring Boot application that can be run using Docker. This project is for [Fetch Processor Challenge](https://github.com/fetch-rewards/receipt-processor-challenge).

## Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) installed

## Getting Started with Running the App

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/kv246/receipt-processor
    cd receipt-processor
    ```

2. Build the Docker image:

    ```bash
    docker build -t receipt-spring-boot-app .
    ```

    This will create a Docker image named `receipt-spring-boot-app`.

3. Run the Docker container:

    ```bash
    docker run -p 8080:8080 receipt-spring-boot-app
    ```

    This will run the container, mapping port 8080 in the container to port 8080 on your local machine.

4. Access the application:

    Open a browser and go to [http://localhost:8080](http://localhost:8080) to see the app in action.


## Project Completion Notes

This is a Spring Boot application that exposes a REST API based on an OpenAPI specification defined in the api.yml file. The Java classes and necessary dependencies were generated using the OpenAPI Generator from the provided OpenAPI definition. The project is Dockerized to build, run, and deploy the application in any environment.

Here’s how the project was completed:

- OpenAPI Generator: I used the OpenAPI Generator tool to generate the Java source files based on the API definition in api.yml.
You can install OpenAPI Generator via Homebrew (macOS), download the JAR directly, or use a Docker container. Here's the Homebrew method:
    ```
    brew install openapi-generator
    ```
- Code Generation: The generated files (controllers, models, and API logic) were incorporated into the project, and additional custom logic was added as needed.
    Using the api.yml file (which contains the OpenAPI specification), I generated the Spring Boot project by running:

    ```
    openapi-generator-cli generate -i api.yml -g spring -o receipt-spring-boot-app
    ```

        -i api.yml: Specifies the input OpenAPI specification file.
        -g spring: Tells OpenAPI Generator to generate a Spring Boot server application.
        -o receipt-spring-boot-app: Specifies the output directory for the generated project.

- Modify the output as needed:
After generating the project, I made some changes to the default generated code to suit the specific business logic and to Dockerize the application.

These steps are already completed and the corresponding source code is available in the repository. The generation process is documented here for transparency and to explain how the project was originally built. You do not need to follow these steps to run the Dockerized app—you can simply follow the instructions mentioned in the first section [Getting Started with Running the App](##getting-started-with-running-the-app).

## Docker Notes
This Dockerfile defines a multi-stage build process:
- Build Stage: It uses a Maven image to compile the Spring Boot application and generate the .jar file.
- Final Image: It copies the generated .jar file from the build stage and uses a smaller OpenJDK runtime image to run the application.

If you encounter any issues, here are some common troubleshooting steps:
- Build Issues: If the build fails, ensure that you have all the necessary dependencies and that your pom.xml file is correctly configured.
- Port Conflicts: If port 8080 is already in use, modify the docker run command to use a different port (e.g., docker run -p 9090:8080 receipt-spring-boot-app).

Additional Notes
- Swagger UI: Once the application is running, you can access the Swagger UI at http://localhost:8080/swagger-ui.html to interact with and test the API.