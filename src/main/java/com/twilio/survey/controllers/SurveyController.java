package com.twilio.survey.controllers;

import spark.Route;
import com.twilio.survey.models.*;

public class SurveyController {
	private static SurveyService survey = new SurveyService();
	
	public static Route index = (request, response) -> {
		return "hello, world!";
	};
	
	public static Route respond = (request, response) -> {
		return survey.saveResponse(new Response(request.params(":phone")));
	};
	
	public static Route getResponse = (request, response) -> { 
		return survey.getResponse(request.params(":id")).getRespondent();
	};
}
