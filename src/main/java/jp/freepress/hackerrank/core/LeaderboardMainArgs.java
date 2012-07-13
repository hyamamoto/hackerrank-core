package jp.freepress.hackerrank.core;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

/**
 * A command line arguments parser for leader board main classes.
 * 
 * @see p.freepress.hackerrank.LeaderboardMain
 */
public class LeaderboardMainArgs {

  public LeaderboardMainArgs() {
    super();
  }

  public static final String LIMIT = "-L";
  @Parameter(names = LIMIT, description = "limit")
  public int limit = 50;

  @Parameter(names = "--help", description = "Print this help", help = true)
  public boolean help;

  public static LeaderboardMainArgs parseMainArgs(String[] args) {
    LeaderboardMainArgs argsObj = new LeaderboardMainArgs();
    try {
      JCommander jCommander;
      jCommander = new JCommander(argsObj, args);
      if (argsObj.help) {
        jCommander.usage();
        System.exit(0);
      }
    } catch (ParameterException ex) {
      System.err.println(ex.getMessage() + "\n(Use \"--help\" option for more information.)");
      System.exit(1);
    }
    return argsObj;
  }

}
