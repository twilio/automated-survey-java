package com.twilio.kitchensink.controllers;

import spark.Route;

public class PersonController {
  public static Route sayHello = (request, response) -> {
    return "hello functional stuff";
  };
}
