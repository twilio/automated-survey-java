package com.twilio.survey;

import com.twilio.survey.util.Config;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ServerTest {
  public static Config config;

  @Test
  public void questionsImportedSuccessfully() {
    config = new Config();
    assertTrue(config.getQuestions().length > 0);
    assertNotNull(config.getQuestions());
  }



}
