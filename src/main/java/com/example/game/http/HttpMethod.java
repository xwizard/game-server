package com.example.game.http;

/**
 * HTTP methods used in application.
 */
public enum HttpMethod {
  GET("GET"),
  POST("POST"),
  UNKNOWN("UNKNOWN");

  public final String method;

  HttpMethod(String method) {
    this.method = method;
  }

  public static HttpMethod parse(String string) {
    for (HttpMethod value : values()) {
      if (value.method.equals(string)) {
        return value;
      }
    }
    return UNKNOWN;
  }

  @Override
  public String toString() {
    return method;
  }
}
