package jp.freepress.hackerrank.splash;

import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.CoreConstants;
import jp.freepress.hackerrank.core.HackerRankAPI;

/**
 * Abstract class for each main class which uses {@link HackerRankAPI} object..
 */
public class AbstractSplashMain extends AbstractMain {

  protected SplashAPI splash = new SplashAPI( h);

  public AbstractSplashMain(boolean useSolverLog) {
    super(useSolverLog);
  }

  protected boolean doLogin() {
    return doLogin((String) null, (String) null);
  }

  protected boolean doLogin(String username, String password) {
    // LOGIN
    username = username != null ? username : CoreConstants.get().USERNAME();
    password = password != null ? password : CoreConstants.get().PASSWORD();
    JsonLogin login = splash.sign_in(username, password);
    if (login == null || login.getId() == null || login.getId().isEmpty()) {
      l.log("", -1, "LOGIN", "Login Failure", login);
      if (h.getLastStatusCode() != 200) {
        l.log("", -1, "SERVICE_UNAVAILABLE", "At login.", h.getLastStatusLine());
      }
      return false;
    } else {
      l.log("", -1, "LOGIN", "Login Success", login);
      return true;
    }
  }

  protected void doUserStats() {
    // USER STATUS (this is gail, this forces user to logout?)
    {
      JsonUserStat stat = splash.userstats();
      l.log("", -1, "STATUS", "", stat);
    }
  }


}
