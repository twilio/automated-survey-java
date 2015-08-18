package com.twilio.survey.models;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

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
    this.from = parsedBody.get("From");
    this.recordingUrl = parsedBody.get("RecordingURL");
    this.digits = parsedBody.get("Digits");
    this.transcriptionText = parsedBody.get("TranscriptionText");
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
