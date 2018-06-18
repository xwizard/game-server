package com.example.game.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class BodyWrapperTest {
  @Test(expected = IllegalArgumentException.class)
  public void ofShouldThrowOnNull() {
    BodyWrapper.of(null);
  }

  @Test
  public void ofShouldReturnEmptyScoreForEmptyBody() {
    BodyWrapper actual = BodyWrapper.of(StubHttpExchange.ofBody(""));
    assertFalse(actual.getScore().isPresent());
  }

  @Test
  public void ofShouldReturnEmptyScoreForNonNumericalBody() {
    BodyWrapper actual = BodyWrapper.of(StubHttpExchange.ofBody("not a score"));
    assertFalse(actual.getScore().isPresent());
  }

  @Test
  public void ofShouldReturnEmptyScoreFornegativeBody() {
    BodyWrapper actual = BodyWrapper.of(StubHttpExchange.ofBody("-1"));
    assertFalse(actual.getScore().isPresent());
  }

  @Test
  public void ofShouldReturnScore() {
    BodyWrapper actual = BodyWrapper.of(StubHttpExchange.ofBody("345"));
    assertTrue(actual.getScore().isPresent());
    assertEquals(Integer.valueOf(345), actual.getScore().get());
  }
}