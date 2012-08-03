package jp.freepress.hackerrank.beta;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

/**
 * A command line arguments parser for leader board main classes.
 * 
 * @see jp.freepress.hackerrank.BetaLeaderboardMain
 */
public class LeaderboardMainArgs {

  public LeaderboardMainArgs() {
    super();
  }
  
  public static final String USERNAME = "-U";
  @Parameter(names = USERNAME, description = "Your username. Use with -P option.", required=true)
  public String username = null;

  public static final String PASSWORD = "-P";
  @Parameter(names = PASSWORD, description = "A password for your username")
  public String password = null;

  // public static final String LIMIT = "-L";
  // @Parameter(names = LIMIT, description = "limit")
  // public int limit = 50;

  public static final String OFFSET = "-O";
  @Parameter(names = OFFSET, description = "offset")
  public int offset = 0;

  public static final String CATEGORYNAMEVALUE = "-C";
  @Parameter(names = CATEGORYNAMEVALUE, description = "A pair of a category name and its value. (eg. -C language:python")
  public String categoryNameValue;
  public LeaderBoardCategory category;
  public String categoryValue;

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

      String username = argsObj.username;
      if ((username != null && !username.isEmpty()) && argsObj.password == null) {
        System.out.print("A password for the username (" + username + "): ");
        argsObj.password = new String(JCommander.getConsole().readPassword(false));
      }
      
      if ( argsObj.categoryNameValue != null && !argsObj.categoryNameValue.isEmpty()) {
        int colonIndex = argsObj.categoryNameValue.indexOf(":");
        if (colonIndex  == -1) {
          System.err.println("You must specify -C option in a format \"{CATEGORY_NAME}:{CATEGORY_VALUE}\"");
          System.exit(1);
        }
        String categoryName = argsObj.categoryNameValue.substring(0, colonIndex);
        LeaderBoardCategory category = LeaderBoardCategory.match( categoryName);
        if (category == null) {
          System.err.println("Unknown category [" + categoryName + "]. The category name must be one of ( " + LeaderBoardCategory.toCategoryIdListString() + ")");
          System.exit(1);
        }
        argsObj.category = category;
        argsObj.categoryValue = argsObj.categoryNameValue.substring( colonIndex + 1, argsObj.categoryNameValue.length());
      }
    } catch (ParameterException ex) {
      System.err.println(ex.getMessage() + "\n(Use \"--help\" option for more information.)");
      System.exit(1);
    }
    return argsObj;
  }
}
