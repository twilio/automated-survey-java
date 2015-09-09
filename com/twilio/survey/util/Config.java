package com.twilio.survey.util;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import com.mongodb.MongoClientURI;
import com.google.gson.*; // TODO: Resolve wildcard import


public class Config {
  private int port;
  private MongoClientURI mongoURI;
  // Holds the name of the collection (helpful in the event that authentication is enforced)
  private String mongoDBName;
  private Question[] questions;

  public Config() {
    Map<String, String> env = System.getenv();

    // Set defaults, and override with environment variables, if present.

    // default to port 4567, the idiomatic Spark port.
    port = 4567;
    // default to localhost, the idiomatic MongoDB host.
    String envMongoURL = "mongodb://localhost:27017/Test";

    // Check the environment for the presence of configured variables.
    if (env.containsKey("PORT")) {
      port = Integer.valueOf(env.get("PORT"));
    }
    if (env.containsKey("MONGOLAB_URI")) {
      envMongoURL = env.get("MONGOLAB_URI");
    } else if (env.containsKey("MONGO_URL")) {
      envMongoURL = env.get("MONGO_URL");
    }

    mongoURI = new MongoClientURI(envMongoURL);
    mongoDBName = mongoURI.getDatabase();
    questions = parseQuestionFile();
  }

  private Question[] parseQuestionFile() {
    String questionFileAsJson = importQuestionFile();
    Gson gson = new Gson();
    Question[] q = gson.fromJson(questionFileAsJson, Question[].class);
    return q;
  }

  private String importQuestionFile() {
    String questionFileAsString = "";
    try {
      Scanner questionFile = new Scanner(new File("questions.json"));
      while (questionFile.hasNextLine()) {
        questionFileAsString += questionFile.nextLine();
      }
      questionFile.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return questionFileAsString;
  }

  public int getPort() {
    return port;
  }

  public MongoClientURI getMongoURI() {
    return mongoURI;
  }

  public String getMongoDBName() {
    return mongoDBName;
  }

  public Question[] getQuestions() {
    return questions;
  }

}
