package jp.freepress.hackerrank.beta;

import java.util.HashMap;
import java.util.Map;

import jp.freepress.hackerrank.core.HackerRankAPI;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import com.google.gson.Gson;

/**
 * <p>
 * <a href="http://hackerrank.com/"><tt>HackerRank</tt></a> API wrapper for HackerRank Beta.
 * <dl>
 * <dt>Account Management</dt>
 * <dd>
 * <ul>
 * <li>login()</li>
 * <li>logout()</li>
 * <li>hacker()</li>
 * </ul>
 * </dd>
 * <dt>Network</dt>
 * <dd>
 * <ul>
 * <li>leaderboard()</li>
 * <li>networks()</li>
 * </ul>
 * </dd>
 * <dt>Contest / Challenges</dt>
 * <dd>
 * <ul>
 * <li>contest_master()</li>
 * <li>challenges()</li>
 * <li>submissions()</li>
 * </ul>
 * </dd>
 * </dl>
 * </p>
 * 
 * <p>
 * <dl>
 * <dt>7/26: Initial version</dt>
 * </dl>
 * <p>
 * 
 * @author higon@freepress.jp
 * @see HackerRankAPI
 */
public class BetaAPI {

  private static final String URL_HACKERRANK = "https://" + //
      (char) 112 + (char) 114 + (char) 105 + (char) 118 + (char) 97 + (char) 116 + //
      (char) 0x65 + (char) 0x62 + (char) 0x65 + "ta.hackerrank.com/";

  public static final String URL_HACKERRANK_REST = URL_HACKERRANK + "rest/";

  public static final String URL_LOGIN = URL_HACKERRANK + "login";

  public static final String URL_LOGOUT = URL_HACKERRANK + "logout";

  public static final String URL_LEADERBOARD = URL_HACKERRANK_REST + "leaderboard";

  public static final String URL_CHALLENGES = URL_HACKERRANK_REST + "challenges";

  public static final String URL_CONTESTS_MASTER = URL_HACKERRANK_REST + "contests/master";

  public static final String URL_HACKER = URL_HACKERRANK_REST + "hacker";

  public static final String URL_SUBMISSIONS = URL_HACKERRANK_REST + "submissions";

  public static final String URL_NETWORKS = URL_HACKERRANK_REST + "networks";

  private final HackerRankAPI api;

  public BetaAPI(HackerRankAPI api) {
    super();
    this.api = api;
  }

  /**
   * Requests sign-in to the server.
   */
  public void login(String username, String password) {
    final String authToken = getAuthToken();
    Map<String, String> param = new HashMap<String, String>();
    {
      param.put("authenticity_token", authToken);
      param.put("hacker[login]", username);
      param.put("hacker[password]", password);
      // param.put("utf8", "\u2713");
      param.put("utf8", "&#x2713;");
    }
    // 302 found
    api.send(URL_LOGIN, param, "POST", 1);
  }

  public void logout() {
    // https://{phost}/logout
    // 302 found
    api.send(URL_LOGOUT, null, "GET", 1);
  }

  /**
   * Retrieves the leader board information from the server.
   * 
   * @return A leader board object.
   */
  public JsonLeaderBoardList leaderboard() {
    return leaderboard(-1, -1);
  }

  /**
   * Retrieves the leader board information from the server.
   * 
   * @param limit a limit count. <i>the hard limit on the server looks to be 763. (2012/07/06) </i>
   * @param offset a offset ( offset > 0 )
   * @return A leader board object.
   */
  public JsonLeaderBoardList leaderboard(int offset, int limit) {
    return leaderboard(offset, limit, (LeaderBoardCategory)null, null);
  }
  
  /**
   * Retrieves the leader board information from the server.
   * 
   * @param limit a limit count. <i>the hard limit on the server looks to be 763. (2012/07/06) </i>
   * @param offset a offset ( offset > 0 )
   * @param category A category of the leaderboard to be retrieved
   * @param categoryValue 
   * @return A leader board object.
   */
  public JsonLeaderBoardList leaderboard(int offset, int limit, LeaderBoardCategory category, String categoryValue) {
    // https://{phost}/rest/leaderboard?offset=0&_=1343170094503
    // https://{phost}/rest/leaderboard/?network=true&kind=level&value=6&offset=0&_=1343700794632
    String url = URL_LEADERBOARD; 
    if (limit < 0) {
      limit = 20;
    }
    url += "?offset=" + offset;
    if (limit > 0) {
      url += "&limit=" + limit;
    }
    if ( category != null && categoryValue != null) {
      url += "&network=" + true; // this is unknown parameter, but necessary for any category grouping.
      url += "&kind=" + category.getId() + "&value=" + categoryValue;
    }
    url += _time();
    String response = api.send(url, null, "GET", 1);
    JsonLeaderBoardList json = new Gson().fromJson(response, JsonLeaderBoardList.class);
    return json;
  }

  /**
   * Retrieves the challenge information.
   * 
   * @param level
   * @return
   */
  public JsonChallengeList challenges(int level) {
    String url = URL_CHALLENGES;
    url += "?level=" + level;
    url += _time();
    String response = api.send(url, null, "GET", 1);
    JsonChallengeList json = new Gson().fromJson(response, JsonChallengeList.class);
    return json;
  }

  public JsonChallengeHolder challenges(String slug) {
    // https://{phost}/rest/challenges/tic-tac-toe?_=1343169442935
    String url = URL_CHALLENGES + "/" + slug;
    url += "?a" + _time();
    String response = api.send(url, null, "GET", 1);
    JsonChallengeHolder json = new Gson().fromJson(response, JsonChallengeHolder.class);
    return json;
  }

  public JsonContestMasterHolder contests_master() {
    // https://{phost}/rest/contests/master?_=1343170595726
    String url = URL_CONTESTS_MASTER;
    url += "?a" + _time();
    String response = api.send(url, null, "GET", 1);
    JsonContestMasterHolder json = new Gson().fromJson(response, JsonContestMasterHolder.class);
    return json;
  }

  public JsonHackerHolder hacker() {
    // https://{phost}/rest/hacker?_=1343168607380
    String url = URL_HACKER;
    url += "?a" + _time();
    String response = api.send(url, null, "GET", 1);
    JsonHackerHolder json = new Gson().fromJson(response, JsonHackerHolder.class);
    return json;
  }

  public JsonSubmissionStatusList submissions(int offset) {
    // https://{phost}/rest/submissions?offset=0&_=1343170272611
    String url = URL_SUBMISSIONS;
    url += "?offset=" + offset;
    url += _time();
    String response = api.send(url, null, "GET", 1);
    JsonSubmissionStatusList json = new Gson().fromJson(response, JsonSubmissionStatusList.class);
    return json;
  }

  public JsonSubmissionHolder submission(int submissionId, int gameoffset) {
    // https://{phost}/rest/submissions/XXXX?gameoffset=0&_=1343352462132
    // Usually returns a list with a size of 10
    String url = URL_SUBMISSIONS + "/" + submissionId;
    url += "?gameoffset=" + gameoffset;
    url += _time();
    String response = api.send(url, null, "GET", 1);
    JsonSubmissionHolder json = new Gson().fromJson(response, JsonSubmissionHolder.class);
    return json;
  }

  public JsonSubmissionTestResultHolder compile_and_test(int contest_id, int level, int challenge_id, String language, String code) {
    //https://{phost}/rest/submissions
    String url = URL_SUBMISSIONS;
    JsonSubmissionCode submission = new JsonSubmissionCode();
    {
      submission.setId(0);
      submission.setContest_id(contest_id);
      submission.setLevel(level);
      submission.setChallenge_id(challenge_id);
      submission.setLanguage(language);
      submission.setCode(code);
    }
    String jsonStr = new Gson().toJson(submission);
    String response = api.sendJson(url, jsonStr, "PUT", 1);

    // EXPERIMENTAL ERROR HANDLING: We're sorry, but something went wrong (500)
    if ( response != null && response.indexOf("<!DOCTYPE html") != -1) {
      System.err.println("ERROR: Received a HTML document.\n" + response);
    }
   
    JsonSubmissionTestResultHolder json = new Gson().fromJson(response, JsonSubmissionTestResultHolder.class);
    return json;
  }
  /**
   * Submit a code for a challenge.
   * @deprecated UNTESTED (07/28/2012)
   * @see #compile_and_test(int, int, int, String, String)
   */
  public boolean submit_code(int contest_id, int level, int challenge_id, String language, String code) {
    //https://{phost}/rest/submissions
    String url = URL_SUBMISSIONS;
    JsonSubmissionCode submission = new JsonSubmissionCode();
    {
      //submission.setId(id);
      submission.setContest_id(contest_id);
      submission.setLevel(level);
      submission.setChallenge_id(challenge_id);
      submission.setLanguage(language);
      submission.setCode(code);
    }
    String jsonStr = new Gson().toJson(submission);
    String response = api.sendJson(url, jsonStr, "POST", 1);
    //Response => true
    return "true".equals(response);
  }

  public JsonNetworkHolder networks() {
    // https://{phost}/rest/networks?_=1343168607404
    String url = URL_NETWORKS;
    url += "?a" + _time();
    String response = api.send(url, null, "GET", 1);
    JsonNetworkHolder json = new Gson().fromJson(response, JsonNetworkHolder.class);
    return json;
  }

  private String getAuthToken() {
    String sourceUrlString = URL_HACKERRANK;
    MasonTagTypes.register();
    Source source;
    String html = api.send(sourceUrlString, null, "GET", 1);
    source = new Source(html);
    source = new Source(source);
    return getMetaValue(source, "csrf-token");
  }

  private static String getMetaValue(Source source, String key) {
    for (int pos = 0; pos < source.length();) {
      StartTag startTag = source.getNextStartTag(pos, "name", key, false);
      if (startTag == null) return null;
      if (startTag.getName() == HTMLElementName.META) return startTag.getAttributeValue("content");
      pos = startTag.getEnd();
    }
    return null;
  }

  private static final String _time() {
    return "&_=" + System.currentTimeMillis();
  }

}

// https://{phost}/rest/network_leaderboard?_=1343168607374
// {"models":[]}

// https://{phost}/rest/extended_network?_=1343168607378

// https://{phost}/rest/challenges_activities?level=1&_=1343168607393
// {"models":[],"total":0}

// https://{phost}/rest/networks?_=1343700795096
// {"model":{"school":"","country":"Japan","company":"","languages":"scala","id":45,"username":"higon","uid":null,"suggestions":[{"name":"","hackers":[]},{"name":"","hackers":[]}]}}
