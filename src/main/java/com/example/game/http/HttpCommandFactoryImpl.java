package com.example.game.http;

import com.example.game.application.ApplicationContext;
import com.sun.net.httpserver.HttpExchange;

public class HttpCommandFactoryImpl implements HttpCommandFactory {

  ApplicationContext applicationContext;

  public HttpCommandFactoryImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public HttpCommand create(HttpExchange exchange) {
    return LoginHttpCommand.of(applicationContext.sessionService(), "123", HttpMethod.GET);
  }
}
