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
    Session.createFor(-10, LocalDateTime.now());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createForShouldThrowForNullNow() {
    Session.createFor(10, null);
  }

  @Test
  public void createForShouldCreateCorrectSession() {
    Session actual = Session.createFor(10, LocalDateTime.of(2018, 6, 8, 21, 0));

    assertEquals(new Integer(10), actual.getUserId());
    assertEquals(LocalDateTime.of(2018, 6, 8, 21, 10), actual.getExpiryDate());
  }
}