package com.twilio.survey.controllers;

import com.twilio.survey.Server;
import com.twilio.survey.models.IncomingCall;
import com.twilio.survey.models.Question;
import com.twilio.survey.models.Response;
import com.twilio.survey.models.SurveyService;
import com.twilio.survey.models.Survey;

import com.twilio.sdk.verbs.Gather;
import com.twilio.sdk.verbs.Hangup;
import com.twilio.sdk.verbs.Record;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

import spark.ModelAndView;
import spark.Route;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    IncomingCall call = new IncomingCall(parseBody(request.body()));
    TwiMLResponse twiml = new TwiMLResponse();
    Survey existingSurvey = surveys.getSurvey(call.getFrom());
    if (existingSurvey == null) {
      Survey survey = surveys.createSurvey(call.getFrom());
      twiml.append(new Say("Thanks for taking our survey."));
      continueSurvey(survey, twiml);
    } else if (!existingSurvey.isDone()) {
      existingSurvey.appendResponse(new Response(call.getInput()));
      surveys.updateSurvey(existingSurvey);
      if (!existingSurvey.isDone()){
        continueSurvey(existingSurvey, twiml);
      }
    }
    twiml.append(new Say("Your responses have been recorded. Thank you for your time!"));
    return twiml.toXML();
  };

  // Helper methods
  protected static String continueSurvey(Survey survey, TwiMLResponse twiml) throws TwiMLException {
    Question question = Server.config.getQuestions()[survey.getIndex()];
    Say say = new Say(question.getText());
    twiml.append(say);
    switch (question.getType()) {
      case "text":
        Say textInstructions =
            new Say("Your response will be recorded after the tone. "
                + "Once you have finished recording, press the #.");
        twiml.append(textInstructions);
        Record text = new Record();
        text.setFinishOnKey("#");
        twiml.append(text);
        break;
      case "boolean":
        Say boolInstructions =
        new Say("Press 1 to respond 'true,' and press 2 to respond 'false.'");
        twiml.append(boolInstructions);
        Gather booleanGather = new Gather();
        booleanGather.setNumDigits(1);
        twiml.append(booleanGather);
        break;
      case "number":
        Say numInstructions = new Say("Enter the number on your keypad, followed by the #.");
        twiml.append(numInstructions);
        Gather numberGather = new Gather();
        numberGather.setNumDigits(3);
        twiml.append(numberGather);
        break;
    }
    return twiml.toXML();
  }

  public static Map<String, String> parseBody(String body) throws UnsupportedEncodingException {
    String[] unparsedParams = body.split("&");
    Map<String, String> parsedParams = new HashMap<String, String>();
    for (int i = 0; i < unparsedParams.length; i++) {
      String[] param = unparsedParams[i].split("=");
      if (param.length == 2) {
        parsedParams
            .put(URLDecoder.decode(param[0], "utf-8"), URLDecoder.decode(param[1], "utf-8"));
      } else if (param.length == 1) {
        parsedParams.put(URLDecoder.decode(param[0], "utf-8"), "");
      }
    }
    return parsedParams;
  }
}
