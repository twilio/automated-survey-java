# automated-survey-java

Use this example application to create a telephone survey.

## Quick Start
1. Clone this repository
2. Run ```mvn install``` to build the project
3. Create a MongoDB database
  - You can run the MongoDB server, ```mongod```, on your own computer. Follow
  the [MongoDB setup guide](https://docs.mongodb.org/getting-started/shell/installation/) for additional information.
  - You can choose hosted MongoDB provider, and use their MongoDB URL in step 4.
4. Set environment variables
  - ```PORT``` : The port on which the application will listen (default: 4567).
  - ```MONGO_URL``` : The MongoDB URL that points to your MongoDB database (e.g. ```mongodb://user@password:myawesomemongohost.com:27017```)

  If you chose to run ```mongod``` locally, you do not need to specify a
  ```MONGO_URL```.
5. Start the server with ```java -jar target/server.jar```
6. Open ```http://localhost:PORT``` in your web browser to explore (where
  ```PORT``` is the port number you defined in step 3)

## Dependencies
In order to build and run this project, a Java 1.8 JDK and
[Maven](http://maven.apache.org) must be installed. A
[Tomcat](http://tomcat.apache.org) server is recommended, but not required.

This Maven project requires the following packages.
 - the Spark Framework
 - Spark's FreeMarker Template Engine Helpers
 - MongoDB's Java Driver
 - MongoDB's Morphia Object-Document Mapper
 - Twilio's Java SDK
 - Google's Gson JSON parser

To run the accompanying test suite, JUnit is required. For additional detail
about the dependencies of this application, see ```pom.xml``` in the root of
this repository.
