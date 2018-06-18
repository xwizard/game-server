package com.example.game.http;


import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Wraps request body.
 */
public class BodyWrapper {
  private final Integer score;

  private BodyWrapper(Integer score) {
    this.score = score;
  }

  public static BodyWrapper of(HttpExchange exchange) {
    if (exchange == null) throw new IllegalArgumentException("httpExchange cannot be null");

    InputStream inputStream = exchange.getRequestBody();
    String body = new BufferedReader(new InputStreamReader(inputStream))
        .lines().collect(Collectors.joining("\n"));

    Integer score = null;
    if (!body.isEmpty()) {
      try {
        score = Integer.parseInt(body);
        if (score < 0) {
          score = null;
        }
      } catch (NumberFormatException e) {
        // this is fine here
      }
    }
    closeStream(inputStream);

    return new BodyWrapper(score);
  }

  private static void closeStream(InputStream inputStream) {
    try {
      inputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Integer> getScore() {
    return Optional.ofNullable(score);
  }
}
