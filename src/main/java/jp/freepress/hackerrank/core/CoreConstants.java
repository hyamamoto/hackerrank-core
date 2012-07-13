package jp.freepress.hackerrank.core;

// NOTE: come to think of it, this is not constants class ... >.>

public class CoreConstants {

  private static final String USERNAME = "";

  private static final String PASSWORD = "";

  private static final boolean SOLVERLOG_SYSOUT = false;

  private static String PATH_LOG = "log";

  private static String WOLFRAMALPHA_APPID = "";

  public static CoreConstants _instants;

  public static CoreConstants get() {
    if (_instants == null) {
      _instants = new CoreConstants();
    }
    return _instants;
  }

  public String PATH_LOG() {
    return PATH_LOG;
  }

  public void PATH_LOG(String pathLog) {
    PATH_LOG = pathLog;
  }

  public boolean SOLVERLOG_SYSOUT() {
    return SOLVERLOG_SYSOUT;
  }

  public String WOLFRAMALPHA_APPID() {
    return WOLFRAMALPHA_APPID;
  }

  public void WOLFRAMALPHA_APPID(String appId) {
    WOLFRAMALPHA_APPID = appId;
  }

  public String USERNAME() {
    return USERNAME;
  }

  public String PASSWORD() {
    return PASSWORD;
  }
}
