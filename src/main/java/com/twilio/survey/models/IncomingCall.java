package com.twilio.survey.models;

import com.google.gson.annotations.SerializedName;

public class IncomingCall {
  // Annotate each field, so Gson knows to map the appropriate JSON field name to the correct
  // instance variable.
  @SerializedName("From")
  private String from;
  @SerializedName("RecordingUrl")
  private String recordingUrl;
  @SerializedName("Digits")
  private String digits;
  @SerializedName("TranscriptionText")
  private String transcriptionText;

  // Constructor
  public IncomingCall(String from, String recordingUrl, String digits, String transcriptionText) {
    this.from = from;
    this.recordingUrl = recordingUrl;
    this.digits = digits;
    this.transcriptionText = transcriptionText;
  }

  // Accessors
  public String getFrom() {
    return from;
  }

  public String getRecordingUrl() {
    return recordingUrl;
  }

  public String getDigits() {
    return digits;
  }

  public String getTranscriptionText() {
    return transcriptionText;
  }
}
