# Use a lightweight Maven image with Java 21
FROM maven:3.9.12-amazoncorretto-21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to leverage Docker's build cache
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the project, creating the executable JAR
RUN mvn clean install -DskipTests

# Define the command to run the application
# The JAR file name is Java-battleship-oop-1.0-SNAPSHOT.jar as defined in pom.xml
CMD ["java", "-jar", "target/Java-battleship-oop-1.0-SNAPSHOT.jar"]
