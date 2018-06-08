package com.example.game.core;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class SessionTest {
  @Test(expected = IllegalArgumentException.class)
  public void createForShouldThrowForNullUserId() {
    Session.createFor(null, LocalDateTime.now());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createForShouldThrowForEmptyUserId() {
    Session.createFor("", LocalDateTime.now());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createForShouldThrowForNullNow() {
    Session.createFor("user", null);
  }

  @Test
  public void createForShouldCreateCorrectSession() {
    Session actual = Session.createFor("user", LocalDateTime.of(2018, 6, 8, 21, 0));

    assertEquals("user", actual.getUserId());
    assertEquals(LocalDateTime.of(2018, 6, 8, 21, 10), actual.getExpiryDate());
  }
}