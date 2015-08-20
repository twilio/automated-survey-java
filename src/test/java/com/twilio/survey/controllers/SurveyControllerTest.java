package com.twilio.survey.controllers;

import static org.junit.Assert.*;

import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.survey.Server;
import com.twilio.survey.models.Survey;
import com.twilio.survey.util.Config;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class SurveyControllerTest {
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    Config config = new Config();
    Server.config = config;
  }

  @Test
  public void testContinueSurvey() {
    // Confirms that, if a TwiML response is returned, the XML includes a "Say" verb, and the user
    // will receive some feedback.
    Survey survey = new Survey();
    TwiMLResponse twiml = new TwiMLResponse();
    try {
      assertTrue(SurveyController.continueSurvey(survey, twiml).contains("Say"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @Test
  public void testParseBody() {
    // Confirms that the body parser returns some valid key/value pair.
    String testMaterial =
        "FromState=MN&FromCountry=US&CallerCountry=US&Direction=inbound&FromCity=MINNEAPOLIS&CalledCountry=US&CallerState=MNCallStatus=ringing&ApiVersion=2010-04-01";
    try {
      assertEquals(SurveyController.parseBody(testMaterial).get("Direction"), "inbound");
    } catch (UnsupportedEncodingException e) {
      System.out.println(e.getMessage());
    }
  }

}
