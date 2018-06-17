package com.example.game.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginHttpCommandTest {
  @Test
  public void ofShouldReturnInvalidCommandForNonNumericalUserIds() {
    HttpCommand actual = LoginHttpCommand.of("incorrect", HttpMethod.GET);
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void ofShouldReturnInvalidCommandForPOST() {
    HttpCommand actual = LoginHttpCommand.of("123", HttpMethod.POST);
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void ofShouldReturnInvalidCommandForUNKNOWN() {
    HttpCommand actual = LoginHttpCommand.of("123", HttpMethod.UNKNOWN);
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void ofShouldReturnValidLoginCommand() {
    HttpCommand actual = LoginHttpCommand.of("123", HttpMethod.GET);
    assertEquals(LoginHttpCommand.class, actual.getClass());
    assertEquals(new Integer(123), ((LoginHttpCommand)actual).getUserId());
  }
}