package com.example.game.application.score;

import com.example.game.core.LevelHighScore;
import com.example.game.core.LevelScore;
import com.example.game.core.UserScore;
import com.example.game.core.repository.LevelHighScoreRepository;
import com.example.game.core.repository.UserScoreRepository;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class PostUserScoreServiceImpl implements PostUserScoreService {

  private final static Logger LOG = Logger.getLogger(PostUserScoreServiceImpl.class.getCanonicalName());

  private final UserScoreRepository userScoreRepository;
  private final LevelHighScoreRepository levelHighScoreRepository;
  private final Lock lock;

  public PostUserScoreServiceImpl(UserScoreRepository userScoreRepository, LevelHighScoreRepository levelHighScoreRepository) {
    this.userScoreRepository = userScoreRepository;
    this.levelHighScoreRepository = levelHighScoreRepository;
    lock = new ReentrantLock(true);

  }

  @Override
  public void post(Integer userId, Integer levelId, Integer score) {
    LOG.fine("Score for user: " + userId + ", level: " + ", score: " + score);

    lock.lock();

    Optional<LevelHighScore> levelHighScoreOpt = levelHighScoreRepository.get(levelId);
    LevelHighScore levelHighScore;
    if (!levelHighScoreOpt.isPresent()) {
      levelHighScore = LevelHighScore.create(levelId);
      levelHighScoreRepository.save(levelHighScore);
    } else {
      levelHighScore = levelHighScoreOpt.get();
    }

    levelHighScore.postScore(userId, score);

    Optional<UserScore> userScore = userScoreRepository.get(userId);
    if (!userScore.isPresent()) {
      lock.unlock();
      throw new IllegalStateException("No score for userId: " + userId);
    }

    userScore.get().addScore(LevelScore.of(levelId, score));

    lock.unlock();
  }
}
