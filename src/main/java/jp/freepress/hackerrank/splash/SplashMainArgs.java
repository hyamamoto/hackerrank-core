package jp.freepress.hackerrank.splash;


import jp.freepress.hackerrank.core.CoreConstants;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

/**
 * A command line arguments parser for main classes.
 * 
 * @See {@link jp.freepress.hackerrank.SplashCandiesMain SplashSpaceXMain}
 * @See {@link jp.freepress.hackerrank.SplashSpaceXMain SplashCandiesMain}
 */
public class SplashMainArgs {

  public SplashMainArgs() {
    super();
  }

  // public static final String OUTPUT_DIRECTORY = "-d";
  // @Parameter(names = OUTPUT_DIRECTORY, description = "Output directory")
  // public String outputDirectory = "log";

  public static final String FROM = "-from";
  @Parameter(names = FROM, description = "First # of the challenge", required = true)
  public Integer from = 1;

  public static final String TO = "-to";
  @Parameter(names = TO, description = "Last # of the challenge", required = true)
  public Integer to;

  public static final String SLOG = "-slog";
  @Parameter(names = SLOG, description = "Solver log output directory")
  public String slog = "log";

  public static final String USERNAME = "-U";
  @Parameter(names = USERNAME, description = "Your username. Use with -P option.")
  public String username = null;

  public static final String PASSWORD = "-P";
  @Parameter(names = PASSWORD, description = "A password for your username")
  public String password = null;

  public static final String SCALE = "-scale";
  @Parameter(names = SCALE, description = "Experimental value")
  public int scale = 1;

  @Parameter(names = "--help", description = "Print this help", help = true)
  public boolean help;

  public static SplashMainArgs parseMainArgs(String[] args, Object extraArgsObj) {
    SplashMainArgs argsObj = new SplashMainArgs();
    try {
      JCommander jCommander;
      if (extraArgsObj != null) {
        jCommander = new JCommander(new Object[] {argsObj, extraArgsObj}, args);
      } else {
        jCommander = new JCommander(argsObj, args);
      }
      if (argsObj.help) {
        jCommander.usage();
        System.exit(0);
      }
      String username = argsObj.username;
      if ((username != null && !username.isEmpty()) && argsObj.password == null) {
        System.out.print("A password for the username (" + username + "): ");
        argsObj.password = new String(JCommander.getConsole().readPassword(false));
      }
    } catch (ParameterException ex) {
      System.err.println(ex.getMessage() + "\n(Use \"--help\" option for more information.)");
      System.exit(1);
    }
    CoreConstants.get().PATH_LOG(argsObj.slog);
    return argsObj;
  }

  public static void main(String[] args) {
    // args = new String[] {};
    // args = new String[] { "--help"};
    // args = new String[] { "-from", "1", "-to", "100", "-U", "testuser", "-P", "test"};
    // args = new String[] { "-from", "1", "-to", "100", "-U", "testuser"};
    parseMainArgs(args, null);
  }
}
