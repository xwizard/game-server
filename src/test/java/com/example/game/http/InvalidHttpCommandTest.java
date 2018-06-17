package com.example.game.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvalidHttpCommandTest {
  @Test
  public void ofShouldCreateCorrectCommand() {
    HttpCommand actual = InvalidHttpCommand.of("message");
    CommandResult result = actual.execute();

    assertEquals("message", result.toResponse());
    assertEquals(400, result.httpStatus());
  }
}