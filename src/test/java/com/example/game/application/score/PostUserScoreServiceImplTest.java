package com.example.game.application.score;

import com.example.game.core.LevelHighScore;
import com.example.game.core.LevelScore;
import com.example.game.core.UserHighScore;
import com.example.game.core.UserScore;
import com.example.game.core.repository.LevelHighScoreRepository;
import com.example.game.core.repository.UserScoreRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostUserScoreServiceImplTest {
  private PostUserScoreService service;
  private UserScore userScore;
  private LevelHighScore levelHighScore;
  private LevelHighScoreRepository levelHighScoreRepository;

  @Before
  public void before() {
    userScore = UserScore.create(1);
    UserScoreRepository userScoreRepository = mock(UserScoreRepository.class);
    when(userScoreRepository.get(eq(1))).thenReturn(Optional.of(userScore));

    levelHighScore = LevelHighScore.create(1);
    levelHighScoreRepository = mock(LevelHighScoreRepository.class);
    when(levelHighScoreRepository.get(eq(1))).thenReturn(Optional.of(levelHighScore));
    service = new PostUserScoreServiceImpl(userScoreRepository, levelHighScoreRepository);
  }

  @Test(expected = IllegalStateException.class)
  public void postShouldThrowWhenUserScoreIsMissing() {
    service.post(0, 0, 100);
  }

  @Test
  public void postShouldPostCorrectScore() {
    service.post(1, 1, 100);

    List<LevelScore> actual = userScore.getUserSores();
    assertEquals(1, actual.size());
    LevelScore ls = actual.get(0);
    assertEquals(Integer.valueOf(1), ls.getLevelId());
    assertEquals(Integer.valueOf(100), ls.getScore());
  }

  @Test
  public void postShouldPostToExistingLevel() {
    service.post(1, 1, 200);
    List<UserHighScore> actual = levelHighScore.getHighScore(1);
    assertEquals(1, actual.size());
    assertEquals(Integer.valueOf(200), actual.get(0).getScore());
    assertEquals(Integer.valueOf(1), actual.get(0).getUserId());
  }

  @Test
  public void postShouldCreateLevel() {
    service.post(1, 10, 200);
    verify(levelHighScoreRepository).save(any());
  }
}