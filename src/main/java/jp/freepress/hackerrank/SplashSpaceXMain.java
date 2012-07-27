package jp.freepress.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jp.freepress.hackerrank.core.CoreConstants;
import jp.freepress.hackerrank.core.NeedsRetryException;
import jp.freepress.hackerrank.core.Solver;
import jp.freepress.hackerrank.splash.AbstractSplashMain;
import jp.freepress.hackerrank.splash.GameAnswer;
import jp.freepress.hackerrank.splash.GameProblem;
import jp.freepress.hackerrank.splash.GameSolver_10001_HybridCaesar;
import jp.freepress.hackerrank.splash.GameSolver_1_PoormanCaesar;
import jp.freepress.hackerrank.splash.JsonGame;
import jp.freepress.hackerrank.splash.JsonGameAnswerImpl;
import jp.freepress.hackerrank.splash.JsonGameGame;
import jp.freepress.hackerrank.splash.JsonGameGameImpl;
import jp.freepress.hackerrank.splash.JsonGameImpl;
import jp.freepress.hackerrank.splash.SplashMainArgs;

import com.beust.jcommander.Parameter;

/**
 * <p>
 * <code>hackerrank.com</code> "challenge" answering machine.
 * </p>
 * <p>
 * <b>Problem Text:</b>
 * 
 * <pre>
 * Emily is an astronaut applying to be a part of the SpaceX project. To get the job, she must interview with a series of researchers, following this interview pattern:
 * 
 * She asks a factual question first, which the scientist answers in an encrypted string. The scientist immediately responds with a different string that she has to decrypt with the same key as the first. The encryption algorithm is the same for all questions.
 * 
 * If you decrypt the message on your first attempt you get 5 points; on your second attempt you get 3 points; and 1 point henceforth. The output will always be a single number.
 * 
 * The highest scoring performers will be selected for the program. How many scientists can you talk to?
 * </pre>
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @since 2012/07/05
 * @see CoreConstants
 */
public class SplashSpaceXMain extends AbstractSplashMain {

  private static enum GameResult {
    WIN, LOSE, IMCOMPLETE, ERROR, SERVICEERROR, RETRY;
  }


  private static class SplashSpaceXArgs {
    public static final String WOLFRAM_APP_ID = "-waId";
    @Parameter(names = WOLFRAM_APP_ID, description = "Your Wolfram*Alpha AppId. This option is required to solve #11001-")
    public String wolframAppId = null;
  }

  public static void main(String[] args) {

    SplashSpaceXArgs optArgs = new SplashSpaceXArgs();
    SplashMainArgs mainArgs = SplashMainArgs.parseMainArgs(args, optArgs);
    SplashSpaceXMain.SCALE = Math.max(1, mainArgs.scale);
    if (optArgs.wolframAppId != null) {
      CoreConstants.get().WOLFRAMALPHA_APPID(optArgs.wolframAppId);
    }

    SplashSpaceXMain main = new SplashSpaceXMain();

    // Boost
    // main.setAPI(new HackerRankAPIEx());

    final int CHALLENGENO_START = Math.max(mainArgs.from, 1);
    final int CHALLENGENO_END = Math.min(mainArgs.to, 50000);
    final int RETRY_LIMIT_SERVICEERROR = 5;
    final int RETRY_LIMIT_GENERAL = 10;
    final boolean RETRY_LIMIT_GENERAL_THEN_RELOGIN = false;

    Logger log = createLogger(SplashSpaceXMain.class);

    log.info("SpaceX");
    log.info("======");
    log.info("Id: " + main.getProcessId());
    log.info("Log: " + main.l.getFilename());
    log.info("Range: " + CHALLENGENO_START + " - " + CHALLENGENO_END);

    MAIN: while (true) {

      int countErrorRetry = 0;
      int countRetry = 0;

      log.info("Logging in...");
      if (!main.doLogin(mainArgs.username, mainArgs.password)) {
        log.info("Login failed for { username: \""
            + (mainArgs.username == null ? "" : mainArgs.username)
            + "\"}. use -U option or --help for more help.");
        return;
      }
      log.info("Login success.");

      Solver<GameProblem, GameAnswer> solver_1_10000 = new GameSolver_1_PoormanCaesar();
      // Solver<GameProblem, GameAnswer> solver_1_10000 = new GameSolver_1_Fullspec();
      Solver<GameProblem, GameAnswer> solver_10001_11100 = new GameSolver_10001_HybridCaesar();

      SOLVE: for (int gameNo = CHALLENGENO_START; gameNo <= CHALLENGENO_END; gameNo++) {

        log.info("No." + gameNo + ": Started");
        GameResult result;
        result = main.doGame((gameNo > 10000) ? solver_10001_11100 : solver_1_10000, gameNo);
        switch (result) {
          case WIN:
            log.info("No." + gameNo + ": Finished");
            continue SOLVE;
          case RETRY:
            if (countRetry > RETRY_LIMIT_GENERAL) {
              if (RETRY_LIMIT_GENERAL_THEN_RELOGIN) {
                main.doSafeLogout();
                main.doWaitForRelogin(gameNo);
                log.info("No." + gameNo + ": Retry limit [" + RETRY_LIMIT_GENERAL
                    + "] has been reached. Re-login");
                continue MAIN;
              } else {
                log.info("No." + gameNo + ": Retry limit [" + RETRY_LIMIT_GENERAL
                    + "] has been reached. Aborting");
                break SOLVE;
              }
            }
            log.info("No." + gameNo + ": Retring");// TODO: I need more Expressive error message
            countRetry++;
            gameNo--;
            continue SOLVE;
          case SERVICEERROR:
            if (countErrorRetry > RETRY_LIMIT_SERVICEERROR) {
              log.info("No." + gameNo + ": Retry limit for ServiceError ["
                  + RETRY_LIMIT_SERVICEERROR + "] has been reached. Aborting");
              break SOLVE;
            }
            log.info("No." + gameNo + ": ServiceError. Retring");
            countErrorRetry++;
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

      break MAIN;
    }

  }

  public SplashSpaceXMain() {
    super(true);
  }

  protected void doWaitForRetry(int gameNo) {
    try {
      l.log("SCIENTIST", gameNo, "WAITING_FOR_RETRY", "Waiting " + WAIT_MS + " ms", null);
      Thread.sleep(WAIT_MS);
    } catch (InterruptedException e) {}
  }

  protected void doWaitForRelogin(int gameNo) {
    try {
      int wait_ms = 120000;
      l.log("SCIENTIST", gameNo, "WAITING_FOR_LOGIN", "Waiting " + wait_ms + " ms", null);
      Thread.sleep(wait_ms);
    } catch (InterruptedException e) {}
  }

  protected GameResult doGame(Solver<GameProblem, GameAnswer> solver, int gameNo) {
    // QUERY
    List<JsonGame> games = splash.<JsonGame, JsonGameImpl>games(JsonGameImpl.class, gameNo, SCALE);
    List<JsonGameGame> gamegames = new ArrayList<JsonGameGame>();
    for (JsonGame game : games) {
      // Check JsonGame
      if (game != null && "true".equalsIgnoreCase(game.getOk())) {
        l.log("SCIENTIST", gameNo, "QUERY_SUCCESS", "Got data.", game);
      } else {
        if (h.getLastStatusCode() != 200) {
          l.log("SCIENTIST", gameNo, "SERVICE_UNAVAILABLE", "At quering data.",
              h.getLastStatusLine());
          return GameResult.SERVICEERROR;
        } else {
          l.log("SCIENTIST", gameNo, "QUERY_FAILED", "Failed to get data.", game);
          return GameResult.ERROR; // ABORT
        }
      }

      // Check JsonGameGame
      JsonGameGameImpl gamegame = game.getGame();
      if (gamegame != null) {
        String gameId = gamegame.getId();
        if (gameId == null || gameId.isEmpty()) {
          l.log("SCIENTIST", gameNo, "QUERY_FAILED", "Game ID is null.", gamegame);
          return GameResult.ERROR; // ABORT
        }
        gamegames.add(gamegame);
      }
    }
    JsonGameGame.Util.removeIdDupes(gamegames);

    for (JsonGameGame gamegame : gamegames) {
      // SOLVE
      String gameId = gamegame.getId();
      GameProblem problem = new GameProblem(gamegame);
      GameAnswer answer;
      try {
        answer = solver.solve(problem);
      } catch (NeedsRetryException ex) {
        l.log("SCIENTIST", gameNo, "RETRY", "Requesting retry: " + ex.getMessage(), gamegame);
        return GameResult.RETRY; // RETRY
      }

      // ANSWER
      if (answer != null && answer.isDone()) {
        l.log("SCIENTIST", gameNo, "SOLVE_SOLVED", "Solved. [n=" + gameNo + ", id=" + gameId + "]",
            answer);
        // POST
        JsonGameAnswerImpl result = null;
        if (gameNo >= 10001) {
          result = splash.gameAnswer(JsonGameAnswerImpl.class, gameId, answer.getText());
        } else {
          result =
              splash.gameAnswer(JsonGameAnswerImpl.class, gameId, Integer.toString(answer.getNumber()));
        }
        if (result != null && result.getExit() == 0) {
          l.log("SCIENTIST", gameNo, "RESULT_WIN", "Won. [n=" + gameNo + ", id=" + gameId + "]",
              result);
          continue;
          // return GameResult.WIN;
        } else {
          if (h.getLastStatusCode() != 200) {
            l.log(
                "SCIENTIST",
                gameNo,
                "SERVICE_UNAVAILABLE",
                "Service unavailable on commiting an answer. [n=" + gameNo + ", id=" + gameId + "]",
                h.getLastStatusLine());
            return GameResult.SERVICEERROR; // ABORT
          } else {
            l.log("SCIENTIST", gameNo, "RESULT_LOSE", "Lose. [n=" + gameNo + ", id=" + gameId + "]"
                + (result != null ? ": " + result.getMessage() : ""), result);
            return GameResult.LOSE; // ABORT
          }
        }
      } else {
        // SKIP IMCOMPLETED
        l.log("SCIENTIST", gameNo, "SOLVE_IMCOMPLETE", "Solution imcomplete. [n=" + gameNo
            + ", id=" + gameId + "]", answer);
        return GameResult.IMCOMPLETE; // ABORT
      }
    }
    return GameResult.WIN;
  }

  private static int SCALE = 1;

}
