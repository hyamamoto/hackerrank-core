package jp.freepress.hackerrank;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
import jp.freepress.hackerrank.beta.JsonSubmissionGameData_Alt;
import jp.freepress.hackerrank.beta.JsonSubmissionTestResult;
import jp.freepress.hackerrank.beta.JsonSubmissionTestResultHolder;
import jp.freepress.hackerrank.beta.SubmitMainArgs;
import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.CoreConstants;

import org.apache.commons.io.FileUtils;


/**
 * <p>
 * <code>hackerrank.com</code> user submission main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @since 2012/07/26
 */
public class BetaSubmitMain extends AbstractMain {

  public static void main(String[] args) {

    SubmitMainArgs mainArgs = SubmitMainArgs.parseMainArgs(args);

    BetaSubmitMain main = new BetaSubmitMain();
    BetaAPI betaAPI = new BetaAPI( main.h);

    Logger log = createLogger(BetaSubmitMain.class);

    //
    // Compile and Test
    //
    
//    log.info("'Submit', or 'Compile and Test'");
//    log.info("===============================");
    log.info("Compile and Test");
    log.info("================");
    log.info("Id: " + main.getProcessId());

    // CHECK and LOAD a code from a file
    String codefilename = mainArgs.codefile;
    File file = new File( codefilename);
    String codeData;
    try {
      codeData = FileUtils.readFileToString(file, "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println( "Failed to load a file: [" + codefilename + "]");
      return;
    }
    
    // LOGIN
    if ( mainArgs.username != null && !mainArgs.username.isEmpty()) {
      log.info("Logging in...");
      if (!main.doLogin( betaAPI, mainArgs.username, mainArgs.password)) {
        log.info("Login failed for { username: \""
            + (mainArgs.username == null ? "" : mainArgs.username)
            + "\"}. use -U option or --help for more help.");
        return;
      }
      log.info("Login processed.");
    }
    //TODO: Login not needed?

    // Submit
    int contest_id = mainArgs.contestId;
    int level = mainArgs.level;
    int challenge_id = mainArgs.challengeId;
    String language = mainArgs.language;
    
    JsonSubmissionTestResultHolder testResult = betaAPI.compile_and_test(contest_id, level, challenge_id, language, codeData);
    
    if ( testResult != null) {
      JsonSubmissionTestResult model = testResult.getModel();
      if ( model != null) {
        JsonSubmissionGameData_Alt[] gamedataArray = model.getGame();
        if ( gamedataArray != null) {
          StringBuilder buf = new StringBuilder();
          for ( JsonSubmissionGameData_Alt gamedata: gamedataArray) {
            buf.append("\n");
            JsonSubmissionGameData_Alt.Util.appendGameData(buf, gamedata);
          }
          System.out.println(buf);
        } else {
          System.out.println( "No gamedata was returned: " + testResult);
        }
      } else {
        System.out.println( "No game holder was returned: " + testResult);
      }
   } else {
        System.out.println( "Failed to get the result");
    }

    // LOGOUT
    // log.info("Logging out...");
    // main.doLogout();
    // log.info("Logout success. bye.");
  }

  public BetaSubmitMain() {
    super(true);
  }
  
 protected boolean doLogin(BetaAPI api, String username, String password) {
    // LOGIN
    username = username != null ? username : CoreConstants.get().USERNAME();
    password = password != null ? password : CoreConstants.get().PASSWORD();
    api.login(username, password);
    if (h.getLastStatusCode() != 200 && h.getLastStatusCode() != 302) {
      l.log("", -1, "LOGIN", "Login Failure", null);
      //l.log("", -1, "SERVICE_UNAVAILABLE", "At login.", h.getLastStatusLine());
      return false;
    } else {
      l.log("", -1, "LOGIN", "Login Success", null);
      return true;
    }
  }
}
