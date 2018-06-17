package com.example.game.http;

import com.sun.net.httpserver.HttpExchange;

/**
 * Abstract factory creating {@link HttpCommand} objects based on {@link HttpExchange}.
 */
public interface HttpCommandFactory {
  /**
   * Creates {@link HttpCommand} objects based on {@link HttpExchange} content (URI, body, http method).
   * It closes the exchange but doesn't send any response.
   * @param exchange http exchange
   * @return http command implementation
   */
  HttpCommand create(HttpExchange exchange);
}
