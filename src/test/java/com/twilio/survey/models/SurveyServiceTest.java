package com.twilio.survey.models;

import static org.junit.Assert.*;

import com.twilio.survey.Server;
import com.twilio.survey.util.Config;

import org.junit.BeforeClass;
import org.junit.Test;

public class SurveyServiceTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    Config config = new Config();
    Server.config = config;
  }

  @Test
  public void testGetSurveyString() {
    SurveyService service = new SurveyService();
    String phone = "5555555556";
    Survey expected = service.createSurvey(phone);
    Survey actual = service.getSurvey(phone);
    assertEquals(expected.getId(), actual.getId());
  }

  @Test
  public void testCreateSurvey() {
    SurveyService service = new SurveyService();
    String phone = "5555555558";
    Survey expected = service.createSurvey(phone);
    assertNotNull(expected.getId());
    assertEquals(expected.getPhone(), phone);
  }

  @Test
  public void testUpdateSurvey() {
    SurveyService service = new SurveyService();
    String phone = "5555555555";
    service.createSurvey(phone);
    Survey expected = service.getSurvey(phone);
    assertNotNull(expected);
    Response newResponse = new Response("This is a string!");
    expected.appendResponse(newResponse);
    service.updateSurvey(expected);

    Survey actual = service.getSurvey(phone);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getResponses()[0].getAnswer(), actual.getResponses()[0].getAnswer());

  }

}
