package com.twilio.survey.controllers;

import com.twilio.survey.Server;
import com.twilio.survey.models.Question;
import com.twilio.survey.models.Response;
import com.twilio.survey.models.SurveyService;
import com.twilio.survey.models.Survey;
import com.twilio.survey.models.IncomingCall;
import com.twilio.sdk.verbs.Gather;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Record;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.Route;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

public class SurveyController {
  private static SurveyService surveys = new SurveyService(Server.config.getMongoURI());
  private static FreeMarkerEngine templateEngine;

  // Configure and attach FreeMarker template engine to the class variable for easy access in route
  // definitions.
  public static void initializeTemplateEngine() {
    templateEngine = new FreeMarkerEngine();
    Configuration freeMarkerConfig = new Configuration();
    freeMarkerConfig.setTemplateLoader(new ClassTemplateLoader(SurveyService.class, "/"));
    templateEngine.setConfiguration(freeMarkerConfig);
  };

  // Render landing page.
  public static Route index = (req, res) -> {
    initializeTemplateEngine();

    // Create template keys/values
      Map<String, Object> templateValues = new HashMap<>();
      templateValues.put("greeting", "Ahoy hoy!");

      return templateEngine.render(new ModelAndView(templateValues, "index.ftl"));
    };

  // Main interview loop.
  public static Route interview = (request, response) -> {
    Gson gson = new Gson();
    IncomingCall call = gson.fromJson(request.body(), IncomingCall.class);
    TwiMLResponse twiml = new TwiMLResponse();
    Survey existingSurvey = surveys.getSurvey(call.getFrom());
    if (existingSurvey == null) {
      Survey survey = surveys.createSurvey(call.getFrom());
      twiml.append(new Say("Thanks for taking our survey."));
      continueSurvey(survey, twiml);
    } else {
      existingSurvey.appendResponse(new Response(call.getInput()));
      continueSurvey(existingSurvey, twiml);
    }
    return twiml.toXML();
  };

  // Helper methods
  private static String continueSurvey(Survey survey, TwiMLResponse twiml)
      throws TwiMLException {
    Question q = Server.config.getQuestions()[survey.getIndex()];
    Say say = new Say(q.getText());
    twiml.append(say);
    switch (q.getType()) {
      case "text":
        Record text = new Record();
        text.setFinishOnKey("#");
        twiml.append(text);
        break;
      case "boolean":
        Gather booleanGather = new Gather();
        booleanGather.setNumDigits(1);
        twiml.append(booleanGather);
        break;
      case "number":
        Gather numberGather = new Gather();
        numberGather.setNumDigits(3);
        twiml.append(numberGather);
        break;
    }
    return twiml.toXML();
  }
}
