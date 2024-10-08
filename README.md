# repository-details-service
REST service to fetch GitHub repository details using owner and repository name. This demo application can fetch details of the repositories that are public.

Application uses REDIS to cache repository details to improve performance.

# Building

Prerequisites:

* JDK 21+
* Maven 3.8.6+

Run the build: 

mvn install

# Test and Start the application
Test cases will run as part of build process, please use below command to run test cases in ad hoc basis

mvn test

To start the application, please use below maven command

mvn spring-boot:run

(NOTE: This application runs on port number 8083. You can modify the port number in configuration file. Please use credentials - username: DemoUser and password: Test12345 to login and use the application)

Endpoint Details 

GET /repositories/{owner}/{repository­name}

Please use below maven command to activate local profile that connects application with local instance of redis server

mvn spring-boot:run -Dspring-boot.run.profiles=local