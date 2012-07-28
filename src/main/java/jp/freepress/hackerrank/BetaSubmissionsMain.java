package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
import jp.freepress.hackerrank.beta.JsonSubmissionStatus;
import jp.freepress.hackerrank.beta.JsonSubmissionStatusList;
import jp.freepress.hackerrank.beta.SubmissionsMainArgs;
import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.CoreConstants;

/**
 * <p>
 * <code>hackerrank.com</code> user submission statuses main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @since 2012/07/26
 */
public class BetaSubmissionsMain extends AbstractMain {

  public static void main(String[] args) {

    SubmissionsMainArgs mainArgs = SubmissionsMainArgs.parseMainArgs(args);

    BetaSubmissionsMain main = new BetaSubmissionsMain();
    BetaAPI betaAPI = new BetaAPI(main.h);

    Logger log = createLogger(BetaSubmissionsMain.class);

    log.info("Submissions");
    log.info("===========");
    log.info("Id: " + main.getProcessId());

    // LOGIN
    log.info("Logging in...");
    if (!main.doLogin(betaAPI, mainArgs.username, mainArgs.password)) {
      log.info("Login failed for { username: \""
          + (mainArgs.username == null ? "" : mainArgs.username)
          + "\"}. use -U option or --help for more help.");
      return;
    }
    log.info("Login success.");

    // Submissions
    int offset = mainArgs.offset;
    JsonSubmissionStatusList submissions = betaAPI.submissions(offset);
    if (submissions != null) {
      if (submissions.getModels() != null) {
        for (JsonSubmissionStatus submission : submissions.getModels()) {
          System.out.println(JsonSubmissionStatus.Util.toSimpleLineString(submission));
        }
      } else {
        System.out.println("No Submissions");
      }
    } else {
      System.out.println("No Data");
    }

    // LOGOUT
    // log.info("Logging out...");
    // main.doLogout();
    // log.info("Logout success. bye.");
  }

  public BetaSubmissionsMain() {
    super(true);
  }

  protected boolean doLogin(BetaAPI api, String username, String password) {
    // LOGIN
    username = username != null ? username : CoreConstants.get().USERNAME();
    password = password != null ? password : CoreConstants.get().PASSWORD();
    api.login(username, password);
    if (h.getLastStatusCode() != 200 && h.getLastStatusCode() != 302) {
      l.log("", -1, "LOGIN", "Login Failure", null);
      // l.log("", -1, "SERVICE_UNAVAILABLE", "At login.", h.getLastStatusLine());
      return false;
    } else {
      l.log("", -1, "LOGIN", "Login Success", null);
      return true;
    }
  }

}
