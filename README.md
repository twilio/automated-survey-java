# Automated Surveys with Twilio Voice

Use this example application to create a voice survey using Twilio Voice.

[Read the full tutorial here](https://www.twilio.com/docs/tutorials/walkthrough/automated-survey/java/spark)!

## Quick Start
### Heroku
[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)  
Heroku will automatically configure the environment variables necessary to launch this application. Heroku automatically uses a ["Free" dyno](https://www.heroku.com/pricing), and provisions a ["Sandbox" MongoLab](https://mongolab.com/plans/pricing/) instance, so you can play around with this application at no charge.

### Local
1. Clone this repository.
2. Run ```mvn install``` to run the accompanying test suite, and build the application.
3. Use ```java -jar target/server.jar``` to run the application.

The repository includes a Procfile, which enables easy deployment to a service like Heroku. Instead of executing the command step 3, you can start the server with [Foreman](https://ddollar.github.io/foreman/), using the ```foreman start``` command.

## Environment Variables
Use the following variables to configure this application when running it locally. Heroku will automatically configure these variables for you.
- ```PORT```: the application's port number (defaults to port 4567).
- ```MONGO_URI```: the address of a MongoDB instance to use (defaults to a MongoDB instance listening on the localhost).

## Dependencies
In order to build and run this project locally, a Java 1.8 JDK and
[Maven](http://maven.apache.org) must be installed. A
[Tomcat](http://tomcat.apache.org) server is recommended, but not required.

This Maven project requires the following packages, which will be installed with the command ```mvn install```.
 - the [Spark Framework](http://sparkjava.com)
 - Spark's [FreeMarker Template Engine Helpers](https://github.com/perwendel/spark-template-engines/tree/master/spark-template-freemarker)
 - MongoDB's [Java Driver](http://mongodb.github.io/mongo-java-driver/)
 - MongoDB's [Morphia](http://mongodb.github.io/morphia/) Object-Document Mapper
 - Twilio's [Java Helper Library](https://www.twilio.com/docs/java/install)
 - Google's [Gson](https://github.com/google/gson) JSON parser

To run the accompanying test suite, clone this repository and run ```mvn test```. For additional detail
about the dependencies of this application, see ```pom.xml``` in the root of
this repository.
