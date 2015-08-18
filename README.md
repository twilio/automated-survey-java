# automated-survey-java

Use this example application to create a telephone survey.

## Quick Start
### Heroku
[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Environment Variables
- ```PORT```: the application's port number.
- ```MONGO_URI```: the address of a MongoDB

## Dependencies
In order to build and run this project, a Java 1.8 JDK and
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
