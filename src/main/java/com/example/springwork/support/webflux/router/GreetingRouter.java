package com.example.springwork.support.webflux.router;

import com.example.springwork.support.webflux.handler.GreetingHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

  @Bean
  public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

    return RouterFunctions.route(RequestPredicates.GET("/webflux").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
        greetingHandler::webflux);
  }
}