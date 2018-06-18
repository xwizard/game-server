package com.example.game.core;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SessionTest {
  @Test(expected = IllegalArgumentException.class)
  public void createForShouldThrowForNullUserId() {
    Session.createFor(null, LocalDateTime.now());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createForShouldThrowForNegativeUserId() {
    Session.createFor(-1, LocalDateTime.now());
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

  @Test
  public void expiredShouldReturnTrueForSessionOlderThan10Minutes() {
    Session session = Session.createFor(10, LocalDateTime.of(2018, 6, 18, 10, 0));

    assertTrue(session.expired(LocalDateTime.of(2018, 6, 18, 10, 11)));
  }

  @Test
  public void expiredShouldReturnTrueForSessionYoungerThan10Minutes() {
    Session session = Session.createFor(10, LocalDateTime.of(2018, 6, 18, 10, 0));

    assertFalse(session.expired(LocalDateTime.of(2018, 6, 18, 10, 9)));
  }
}