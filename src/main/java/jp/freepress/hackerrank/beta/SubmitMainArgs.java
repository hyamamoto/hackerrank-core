package jp.freepress.hackerrank.beta;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

/**
 * A command line arguments parser for the submit main classes.
 * 
 * @see jp.freepress.hackerrank.BetaSubmitMain
 */
public class SubmitMainArgs {

  public SubmitMainArgs() {
    super();
  }

  public static final String USERNAME = "-U";
  @Parameter(names = USERNAME, description = "Your username. Use with -P option.")
  public String username = null;

  public static final String PASSWORD = "-P";
  @Parameter(names = PASSWORD, description = "A password for your username")
  public String password = null;
  
  public static final String MODE = "-mode";
  @Parameter(names = MODE, description = "\"compile\" for [Compile & Test], or \"submit\" for [Submit Code]", required=true)
  public int mode;
  
  public static final String CONTEST_ID = "-contestId";
  @Parameter(names = LEVEL, description = "A contestId of a new submission you are about to make. (eg. 1)", required=true)
  public int contestId;
  
  public static final String LEVEL = "-level";
  @Parameter(names = LEVEL, description = "A level of a new submission you are about to make. (eg. 1)", required=true)
  public int level;

  public static final String CHALLENGE_ID = "-challengeId";
  @Parameter(names = CHALLENGE_ID, description = "A challengeId of a new submission you are about to make. (eg. 1)", required=true)
  public int challengeId;

  public static final String CODEFILENAME = "-codefile";
  @Parameter(names = CODEFILENAME, description = "A filepath to your source code for this submission.", required=true)
  public int codefile;

  public static final String LANGUAGE = "-language";
  @Parameter(names = LANGUAGE, description = "A name of the language. (eg. php, python, java, scala, ruby...)", required=true)
  public int language;
  
  @Parameter(names = "--help", description = "Print this help", help = true)
  public boolean help;

  public static SubmitMainArgs parseMainArgs(String[] args) {
    SubmitMainArgs argsObj = new SubmitMainArgs();
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
