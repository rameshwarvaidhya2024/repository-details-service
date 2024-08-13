# repository-details-service
REST service to fetch GitHub repository details using owner and repository name. This application can fetch details of the repositories that are public.

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

(NOTE: Please use credentials - username: DemoUser and password: Test12345 to login and use the application)
