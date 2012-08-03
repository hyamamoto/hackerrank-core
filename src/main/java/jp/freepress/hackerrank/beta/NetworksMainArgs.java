package jp.freepress.hackerrank.beta;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

/**
 * A command line arguments parser for submissions main classes.
 * 
 * @see jp.freepress.hackerrank.BetaSubmissionsMain
 */
public class NetworksMainArgs {

  public NetworksMainArgs() {
    super();
  }

  public static final String USERNAME = "-U";
  @Parameter(names = USERNAME, description = "Your username. Use with -P option.", required=true)
  public String username = null;

  public static final String PASSWORD = "-P";
  @Parameter(names = PASSWORD, description = "A password for your username")
  public String password = null;

  public static final String TARGETUSERNAME = "-targetUser";
  @Parameter(names = TARGETUSERNAME, description = "A name of the user to retrieve network information from", required=true)
  public String targetUser = null;
  
  @Parameter(names = "--help", description = "Print this help", help = true)
  public boolean help;

  public static NetworksMainArgs parseMainArgs(String[] args) {
    NetworksMainArgs argsObj = new NetworksMainArgs();
    try {
      JCommander jCommander;
      jCommander = new JCommander(argsObj, args);
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
    return argsObj;
  }

}
