package com.example.game.http;

import com.example.game.application.ApplicationContext;
import com.sun.net.httpserver.HttpExchange;

public class HttpCommandFactoryImpl implements HttpCommandFactory {

  private final ApplicationContext applicationContext;

  public HttpCommandFactoryImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public HttpCommand create(HttpExchange exchange) {
    URIWrapper uriWrapper = URIWrapper.of(exchange.getRequestURI());

    if (!uriWrapper.getId().isPresent()) {
      return InvalidHttpCommand.of("Id is invalid in request: " + exchange.getRequestURI());
    }
    if (!uriWrapper.getCommand().isPresent()) {
      return InvalidHttpCommand.of("Command is missing in request: " + exchange.getRequestURI());
    }

    HttpMethod method = HttpMethod.parse(exchange.getRequestMethod());

    switch (uriWrapper.getCommand().get()) {
      case "login":
        return LoginHttpCommand.of(applicationContext.sessionService(), uriWrapper.getId().get(), method);
      case "quit":
        return QuitHttpCommand.of(applicationContext.application());
      case "score":
        return createPostCommand(uriWrapper, exchange, method);
      case "highscorelist":
        return createHighScoreListCommand(uriWrapper, exchange);
      default:
        return InvalidHttpCommand.of("Invalid request: " + exchange.getRequestURI());
    }

  }

  private HttpCommand createHighScoreListCommand(URIWrapper uriWrapper, HttpExchange exchange) {
    if (!uriWrapper.getSessionId().isPresent()) {
      return InvalidHttpCommand.of("Session id is invalid in request: " + exchange.getRequestURI());
    }
    return HighScoreHttpCommand.of(applicationContext, uriWrapper.getSessionId().get(), uriWrapper.getId().get(), HttpMethod.GET);
  }

  private HttpCommand createPostCommand(URIWrapper uriWrapper, HttpExchange exchange, HttpMethod method) {
    if (!uriWrapper.getSessionId().isPresent()) {
      return InvalidHttpCommand.of("Session id is invalid in request: " + exchange.getRequestURI());
    }

    BodyWrapper bodyWrapper = BodyWrapper.of(exchange);
    if (!bodyWrapper.getScore().isPresent()) {
      return InvalidHttpCommand.of("Score is not present: "+ exchange.getRequestURI());
    }

    if (method == HttpMethod.POST) {
      return PostScoreHttpCommand.of(applicationContext, uriWrapper.getId().get(),
          uriWrapper.getSessionId().get(), bodyWrapper.getScore().get());
    }

    return InvalidHttpCommand.of("score only supports POST and GET methods");
  }
}
