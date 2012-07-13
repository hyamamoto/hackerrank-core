package jp.freepress.hackerrank.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Abstract class for each main class which uses {@link HackerRankAPI} object..
 */
public class AbstractMain {

  protected static final int WAIT_MS = 1000;

  protected HackerRankAPI h;
  protected final SolverLog l;

  private String processId;

  protected AbstractMain(boolean useSolverLog) {
    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    processId = this.getClass().getSimpleName() + "_" + dateFormat.format(now);
    h = new HackerRankAPI();
    l = useSolverLog ? SolverLog.create(processId) : null;
  }

  protected String getProcessId() {
    return processId;
  }

  protected String getUsername() {
    return CoreConstants.get().USERNAME();
  }

  protected boolean doLogin() {
    return doLogin((String) null, (String) null);
  }

  protected boolean doLogin(String username, String password) {
    // LOGIN
    username = username != null ? username : CoreConstants.get().USERNAME();
    password = password != null ? password : CoreConstants.get().PASSWORD();
    JsonLogin login = h.sign_in(username, password);
    if (login == null || login.getId() == null || login.getId().isEmpty()) {
      llog("", -1, "LOGIN", "Login Failure", login);
      if (h.getLastStatusCode() != 200) {
        llog("", -1, "SERVICE_UNAVAILABLE", "At login.", h.getLastStatusLine());
      }
      return false;
    } else {
      llog("", -1, "LOGIN", "Login Success", login);
      return true;
    }
  }

  protected void doSafeLogout() {
    // NOTE: better skipping logout as this makes all other clients (even on different machine) to
    // logout
    // h.sign_out();
    // h.deleteCookie();
    llog("", -1, "LOGOUT", "Logout", null);
  }

  protected void doUserStats() {
    // USER STATUS (this is gail, this forces user to logout?)
    {
      JsonUserStat stat = h.userstats();
      llog("", -1, "STATUS", "", stat);
    }
  }

  private void llog(String probKind, int probNo, String action, String message, Object data) {
    if (l != null) {
      l.log(probKind, probNo, action, message, data);
    }
  }

  public HackerRankAPI getAPI() {
    return h;
  }

  public void setAPI(HackerRankAPI h) {
    this.h = h;
  }

  protected static Logger createLogger(Class<?> klazz) {
    Logger log = Logger.getLogger(klazz.getName());
    final String simpleName = klazz.getSimpleName();
    log.setUseParentHandlers(false);
    Handler conHdlr = new ConsoleHandler();
    conHdlr.setFormatter(new Formatter() {
      public String format(LogRecord record) {
        return record.getLevel() + ": " //
            + simpleName + ": "
            // + record.getSourceClassName() + " -:- " //
            // + record.getSourceMethodName() + " -:- " //
            + record.getMessage() + "\n";
      }
    });
    log.addHandler(conHdlr);
    return log;
  }

}
