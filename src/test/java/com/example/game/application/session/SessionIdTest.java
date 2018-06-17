package com.example.game.application.session;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SessionIdTest {


  private final static int ENOUGH = 5_000;

  @Test
  public void randomIsRandomEnough() {
    List<SessionId> ids = randomIds();

    for (int i = 0; i < ENOUGH; i++) {
      for (int j = 0; j < ENOUGH; j++) {
        if (i != j) {
          assertNotEquals("" + i + " " + j + " are not different", ids.get(i), ids.get(j));
        }
      }
    }
  }

  private List<SessionId> randomIds() {
    List<SessionId> ids = new ArrayList<>();

    for (int i = 0; i < ENOUGH; i++) {
      ids.add(SessionId.random());
    }
    return ids;
  }

  @Test
  public void randomGeneratesA_ZCharactersOnly() {
    List<SessionId> ids = randomIds();
    ids.forEach(id -> {
      String value = id.toString();
      for (int i = 0; i < value.length(); i++) {
        char c = value.charAt(i);
        assertTrue("Character not in A-Z range", c >= 'A' && c <= 'Z');
      }
    });
  }
}