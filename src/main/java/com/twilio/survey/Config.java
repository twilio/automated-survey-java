package com.twilio.survey;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import com.mongodb.MongoClientURI;
import com.google.gson.*; // TODO: Resolve wildcard import
import com.twilio.survey.models.Question;


public class Config {
  public int port;
  public MongoClientURI mongoURI;
  public Question[] questions;

  public Config() {
    Map<String, String> env = System.getenv();

    // Set defaults, and override with environment variables, if present.
    port = 4567; // default to port 4567, the idiomatic Spark port.
    String envMongoURL = "mongodb://localhost:27017"; // default to localhost, the idiomatic MongoDB
                                                      // host. The driver will
    // use the idiomatic MongoDB port by default.

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
}
