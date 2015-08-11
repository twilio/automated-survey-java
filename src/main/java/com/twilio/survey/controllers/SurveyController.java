package com.twilio.survey.controllers;

import com.twilio.survey.models.Response;
import com.twilio.survey.models.SurveyService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;
import java.util.HashMap;
import java.util.Map;

public class SurveyController {
	private static SurveyService survey = new SurveyService();
	private static FreeMarkerEngine templateEngine;
	
	// Configure and attach FreeMarker template engine to the class variable for easy access in route definitions.
	public static void initializeTemplateEngine() {
		templateEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfig = new Configuration();
		freeMarkerConfig.setTemplateLoader(new ClassTemplateLoader(SurveyService.class, "/"));
		templateEngine.setConfiguration(freeMarkerConfig);
	}
	
	
	public static Route index = (req, res) -> {
		initializeTemplateEngine();
		
		// Create template keys/values
		Map<String, Object> templateValues = new HashMap<>();
		templateValues.put("greeting", "Ahoy hoy!");
		
		return templateEngine.render(new ModelAndView(templateValues, "index.ftl"));
	};
	
	public static Route favoriteNumber = (req, res) -> {
		initializeTemplateEngine();
		
		// Create template keys/values
		Map<String, Object> templateValues = new HashMap<>();
		templateValues.put("favoriteNumber", req.params(":number"));
		
		return templateEngine.render(new ModelAndView(templateValues, "template.ftl"));
	};
	
	public static Route respond = (request, response) -> {
		// Create Response object and commit it to the database, returning an ObjectId for retrieval
		return survey.saveResponse(new Response(request.params(":phone")));
	};
	
	public static Route getResponse = (request, response) -> { 
		// Retrieve a Response object based on the ObjectId provided in the URL parameter
		return survey.getResponse(request.params(":id")).getRespondent();
	};
}
