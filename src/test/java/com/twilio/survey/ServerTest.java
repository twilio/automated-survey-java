package com.twilio.survey;

import static org.junit.Assert.*;

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
