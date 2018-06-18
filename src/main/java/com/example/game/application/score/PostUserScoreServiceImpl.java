package com.example.game.application.score;

import com.example.game.core.LevelScore;
import com.example.game.core.UserScore;
import com.example.game.core.repository.UserScoreRepository;

import java.util.Optional;
import java.util.logging.Logger;

public class PostUserScoreServiceImpl implements PostUserScoreService {

  private final static Logger LOG = Logger.getLogger(PostUserScoreServiceImpl.class.getCanonicalName());

  private final UserScoreRepository userScoreRepository;

  public PostUserScoreServiceImpl(UserScoreRepository userScoreRepository) {
    this.userScoreRepository = userScoreRepository;
  }

  @Override
  public void post(Integer userId, Integer levelId, Integer score) {
    LOG.fine("Score for user: " + userId + ", level: " + ", score: " + score);
    Optional<UserScore> userScore = userScoreRepository.get(userId);
    if (!userScore.isPresent()) {
      throw new IllegalStateException("No score for userId: " + userId);
    }

    userScore.get().addScore(LevelScore.of(levelId, score));
  }
}
