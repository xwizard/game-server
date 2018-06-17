package com.example.game.http;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class URIWrapperTest {
private final static URI TEST_URI;
private final static URI INCORRECT_URI;

  static {
    try {
      TEST_URI = new URI("http://localhost:8081/2/score?sessionkey=UICSNDK");
      INCORRECT_URI = new URI("http://localhost:8081");
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void ofShoulParseCommand() {
    URIWrapper actual = URIWrapper.of(TEST_URI);
    assertEquals("score", actual.getCommand().get());
  }

  @Test
  public void ofShoulParseSessionId() {
    URIWrapper actual = URIWrapper.of(TEST_URI);
    assertEquals("UICSNDK", actual.getSessionId().get());
  }

  @Test
  public void ofShoulParseId() {
    URIWrapper actual = URIWrapper.of(TEST_URI);
    assertEquals("2", actual.getId().get());
  }

  @Test
  public void ofShoulParseIncorrectURI() {
    URIWrapper actual = URIWrapper.of(INCORRECT_URI);
    assertFalse(actual.getCommand().isPresent());
    assertFalse(actual.getId().isPresent());
    assertFalse(actual.getSessionId().isPresent());
  }
}