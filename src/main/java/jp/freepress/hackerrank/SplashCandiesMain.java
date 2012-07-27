package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.core.Solver;
import jp.freepress.hackerrank.splash.AbstractSplashMain;
import jp.freepress.hackerrank.splash.JsonChallenge;
import jp.freepress.hackerrank.splash.JsonChallengeGame;
import jp.freepress.hackerrank.splash.JsonChallengeGameImpl;
import jp.freepress.hackerrank.splash.JsonChallengeImpl;
import jp.freepress.hackerrank.splash.SplashMainArgs;


/**
 * <p>
 * <code>hackerrank.com</code> "archive" answering machine.
 * <p>
 * <b>Problem Text:</b>
 * 
 * <pre>
 * == Play a game.
 * There are N candies and its you and me. We alternate turns, each taking away between 1 and 5 candies. The person to take away the last candy wins.
 * 
 * Type archive <N candies> to start the game. You make the first move.
 * For example: If you want to play with 10 candies type archive 10, with 100 candies it'll be archive 100.
 * 
 * The score depends on the number of games you win. You get the idea. Go ahead and play.
 * </pre>
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @since 2012/07/05
 */
public class SplashCandiesMain extends AbstractSplashMain {

  private static enum ArchiveResult {
    WIN, LOSE, IMCOMPLETE, ERROR, SERVICEERROR;
  }

  // 1814 (1)
  public static void main(String[] args) {

    SplashMainArgs mainArgs = SplashMainArgs.parseMainArgs(args, null);
    SplashCandiesMain.SCALE = Math.max(1, mainArgs.scale);

    SplashCandiesMain main = new SplashCandiesMain();

    // get malicious
    // main.setAPI(new HackerRankAPIEx());

    final int ARCHIVENO_START = Math.max(mainArgs.from, 1);
    final int ARCHIVENO_END = Math.min(mainArgs.to, 10000);
    final int CHALLENGERETRY_LIMIT = 5;

    Logger log = createLogger(SplashCandiesMain.class);

    log.info("Candies");
    log.info("=======");
    log.info("Id: " + main.getProcessId());
    log.info("Log: " + main.l.getFilename());
    log.info("Range: " + ARCHIVENO_START + " - " + ARCHIVENO_END);

    int retriedCount = 0;

    log.info("Logging in...");
    if (!main.doLogin(mainArgs.username, mainArgs.password)) {
      log.info("Login failed for { username: \""
          + (mainArgs.username == null ? "" : mainArgs.username)
          + "\"}. use -U option or --help for more help.");
      return;
    }
    log.info("Login success.");

    SOLVE: for (int gameNo = ARCHIVENO_START; gameNo <= ARCHIVENO_END; gameNo++) {

      if (gameNo % 6 == 0) continue; // Skip losing game.

      log.info("No." + gameNo + ": Started");

      ArchiveResult result = main.doGame((Solver<?, ?>) null, gameNo);
      switch (result) {
        case WIN:
          log.info("No." + gameNo + ": Finished");
          continue SOLVE;
        case SERVICEERROR:
          if (retriedCount > CHALLENGERETRY_LIMIT) {
            log.info("No." + gameNo + ": Retry limit [" + CHALLENGERETRY_LIMIT
                + "] has been reached. Aborting");
            break SOLVE;
          }

          log.info("No." + gameNo + ": Retring");
          retriedCount++;
          gameNo--;
          continue SOLVE;
        default:
          log.info("No." + gameNo + ": Aborting [reason=" + result + "]. Check ["
              + main.l.getFilename() + "] for more detail");
          break SOLVE;
      }
    }

    main.doUserStats();

    log.info("Logging out...");
    main.doSafeLogout();
    log.info("Logout success. bye.");
  }

  public SplashCandiesMain() {
    super(true);
  }

  public ArchiveResult doGame(Solver<?, ?> solver, int candiesTotal) {

    int candiesLeft;
    candiesLeft = candiesTotal;

    JsonChallengeGameImpl newChallenge = splash.challenge(JsonChallengeGameImpl.class, candiesTotal);
    if (newChallenge == null || newChallenge.getN() == 0) {
      l.log("CANDIES", candiesTotal, "QUERY_FAILED", "" + newChallenge.getN(), newChallenge);
      return ArchiveResult.ERROR;
    } else {
      l.log("CANDIES", candiesTotal, "QUERY_SUCCESS", "" + newChallenge.getN(), newChallenge);
    }
    int requestCounter = 0;
    while (true) {
      // Decide
      int mySweeto = candiesLeft % 6;
      boolean finito = candiesLeft == mySweeto;
      // Pick
      JsonChallenge challenge;
      challenge = splash.challengeAnswer(JsonChallengeImpl.class, mySweeto, finito ? SCALE : 1);
      if (challenge == null || challenge.getGame() == null) {
        l.log("CANDIES", candiesTotal, "DRAW_FAILED", challenge.getMessage(), challenge);
        return ArchiveResult.ERROR;
      } else {
        l.log("CANDIES", candiesTotal, "DRAW_SUCCESS", challenge.getMessage(), challenge);
      }
      JsonChallengeGame game = challenge.getGame();
      requestCounter++;
      candiesLeft = game.getCurrent();
      if (candiesLeft == 0) {
        if ("true".equalsIgnoreCase(game.getSolved())) {
          l.log("CANDIES", candiesTotal, "RESULT_WIN", "Won game " + candiesTotal + " with "
              + requestCounter + " requests", null);
          return ArchiveResult.WIN;
        } else {
          l.log("CANDIES", candiesTotal, "RESULT_LOSE", "Lose game " + candiesTotal + " with "
              + requestCounter + " requests", null);
          return ArchiveResult.LOSE;
        }
      }
    }
  }

  private static int SCALE = 1;
}
