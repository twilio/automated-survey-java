package com.twilio.survey.util;

import java.util.Map;

public class IncomingCall {

  private String from;
  private String recordingUrl;
  private String digits;
  private String transcriptionText;

  // Constructor
  public IncomingCall(String from, String recordingUrl, String digits, String transcriptionText) {
    this.from = from;
    this.recordingUrl = recordingUrl;
    this.digits = digits;
    this.transcriptionText = transcriptionText;
  }

  public IncomingCall(Map<String, String> parsedBody) {
    this(parsedBody.get("From"), parsedBody.get("RecordingUrl"), parsedBody.get("Digits"), parsedBody.get("TranscriptionText"));
  }
  
  public IncomingCall() {
    this("+0000000000", null, null, null);
  }

  // Accessors
  public String getFrom() {
    return from;
  }

  public String getInput() {
    if (recordingUrl != null) {
      return recordingUrl;
    } else {
      return digits;
    }
  }

  public String getTranscriptionText() {
    return transcriptionText;
  }
}
