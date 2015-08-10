package com.twilio.survey.controllers;

import spark.Route;

import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.*; // TODO: limit this to the verbs used

public class MessageController {
  public static Route receiveMessage = (request, response) -> {
	String phone = request.params("From");
	String input = request.params("Body");
	respond(input, phone);
    return "hello functional stuff";
  };
  public static void respond (String message, String phone) throws TwiMLException {
		TwiMLResponse response = new TwiMLResponse();
		Message smsBody = new Message(message);
		smsBody.setTo(phone);
		response.append(smsBody);
		
  }
}
