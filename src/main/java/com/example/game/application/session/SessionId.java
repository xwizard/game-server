package com.example.game.application.session;

import java.util.Objects;
import java.util.Random;

/**
 * Random session id.
 */
public class SessionId {

  private final static Random RANDOM = new Random();

  private final String value;

  private SessionId(String value) {
    this.value = value;
  }

  public static SessionId random() {
    StringBuilder randomId = new StringBuilder();

    for (int i = 0; i < 7; i++) {
      char c = (char)(RANDOM.nextInt(25) + 'A');
      randomId.append(c);
    }
    return new SessionId(randomId.toString());
  }

  public static SessionId of(String string) {
    if (string == null || string.length() != 7) throw new IllegalArgumentException("Session id has to be exactly 7 characters");
    for (char c : string.toCharArray()) {
      if (c < 'A' || c > 'Z') throw new IllegalArgumentException("Session id can contain only A-Z characters");
    }

    return new SessionId(string);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SessionId sessionId = (SessionId) o;
    return Objects.equals(value, sessionId.value);
  }

  @Override
  public int hashCode() {

    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }
}
