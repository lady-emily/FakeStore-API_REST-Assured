# Use an official OpenJDK runtime as a parent image
FROM openjdk:23-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Verify Maven installation
RUN mvn --version

# Copy the pom.xml and source code
# Assuming your project files are in the same directory as Dockerfile
COPY pom.xml .
COPY src/ ./src

# Command to run when the container starts
CMD ["mvn", "test"]