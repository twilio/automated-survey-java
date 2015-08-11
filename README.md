# automated-survey-java

Use this example application to create a telephone survey.

## Quick Start
1. Clone this repository
2. Run ```mvn install``` to build the project
3. Start the server with ```java -jar target/server.jar```
4. Open ```http://localhost:4567``` in your web browser to explore

Note: If you have a $PORT environment variable configured on your computer, this application will use that port instead of the default :4567.

## Dependencies
In order to build and run this project, a Java 1.8 JDK and [Maven](http://maven.apache.org) must be installed. A [Tomcat](http://tomcat.apache.org) server is recommended, but not required.

This Maven project requires the following packages.
 - the Spark Framework
 - Spark's FreeMarker Template Engine Helpers
 - MongoDB's Java Driver
 - MongoDB's Morphia Object-Document Mapper
 - Twilio's Java SDK

To run the accompanying test suite, JUnit is required. For additional detail about the dependencies of this application, see ```pom.xml``` in the root of this repository.
