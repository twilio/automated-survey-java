package com.twilio.survey.controllers;

import spark.Route;

public class SurveyController {
	
	public static Route index = (request, response) -> {
		return "hello, world!";
	};
}
