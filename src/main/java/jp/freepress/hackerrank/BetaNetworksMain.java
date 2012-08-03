package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
import jp.freepress.hackerrank.beta.JsonNetwork;
import jp.freepress.hackerrank.beta.JsonNetworkHolder;
import jp.freepress.hackerrank.beta.JsonNetworkSuggestion;
import jp.freepress.hackerrank.beta.NetworksMainArgs;
import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.CoreConstants;


/**
 * <p>
 * <code>hackerrank.com</code> user submission main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @since 2012/07/26
 */
public class BetaNetworksMain extends AbstractMain {

  public static void main(String[] args) {

    NetworksMainArgs mainArgs = NetworksMainArgs.parseMainArgs(args);

    BetaNetworksMain main = new BetaNetworksMain();
    BetaAPI betaAPI = new BetaAPI( main.h);

    Logger log = createLogger(BetaNetworksMain.class);

    String targetUsername = mainArgs.targetUser;

    log.info("Networks");
    log.info("========");
    log.info("Id: " + main.getProcessId());
    log.info("Username: " + targetUsername);

    // LOGIN
    log.info("Logging in...");
    if (!main.doLogin( betaAPI, mainArgs.username, mainArgs.password)) {
      log.info("Login failed for { username: \""
          + (mainArgs.username == null ? "" : mainArgs.username)
          + "\"}. use -U option or --help for more help.");
      return;
    }
    log.info("Login processed.");

    // NETWORK
    JsonNetworkHolder networkHolder = betaAPI.networks( targetUsername); 
    if ( networkHolder != null) {
      JsonNetwork model = networkHolder.getModel();
      if ( model != null) {
        StringBuilder buf = new StringBuilder();
        buf.append("Network\n");
        buf.append("=======\n");
        buf.append( JsonNetwork.Util.toSimpleLineString( model)).append("\n");
        
        JsonNetworkSuggestion[] suggestions = model.getSuggestions();
        if ( suggestions != null && suggestions.length > 0) {
          buf.append("\n");
          buf.append("Suggestion\n");
          buf.append("----------\n");
          for ( JsonNetworkSuggestion suggestion: suggestions) {
            if ( suggestion.getName() != null && !suggestion.getName().isEmpty()) {
              buf.append( JsonNetworkSuggestion.Util.toSimpleLineString( suggestion)).append("\n");
            }
          }
        }
        System.out.println(buf);
      } else {
        System.out.println( "No Such Network: " + networkHolder);
      }
    } else {
        System.out.println( "Failed to get Network data");
    }

    // LOGOUT
    // log.info("Logging out...");
    // main.doLogout();
    // log.info("Logout success. bye.");
  }

  public BetaNetworksMain() {
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
