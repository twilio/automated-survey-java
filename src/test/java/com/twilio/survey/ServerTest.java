package com.twilio.survey;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTest {
  public static Config config;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    
  }

  @Test
  public void questionsImportedSuccessfully() {
    config = new Config();
    assertTrue(config.questions.length > 0);
    assertNotNull(config.questions);
  }
  
  

}
