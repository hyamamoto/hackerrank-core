package jp.freepress.hackerrank.splash;
import static jp.freepress.hackerrank.core.HackerRankAPI.URL_HACKERRANK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.freepress.hackerrank.core.HackerRankAPI;

import com.google.gson.Gson;

/**
 * <p>
 * HackerRank API Wrapper. <br/>
 * This covers the two games on HackerRank pre-beta challenges.
 * <dl>
 * <dt>Account Management</dt>
 * <dd>
 * <ul>
 * <li>sign_in()</li>
 * <li>sign_out()</li>
 * <li>sign_up()</li>
 * </ul>
 * </dd>
 * <dt>Score Records</dt>
 * <dd>
 * <ul>
 * <li>userstats()</li>
 * <li>leaderboard()</li>
 * </ul>
 * </dd>
 * <dt>SpaceX</dt>
 * <dd>
 * <ul>
 * <li>spacexProblem()</li>
 * <li>spacexAnswer()</li>
 * </ul>
 * </dd>
 * <dt>Candies</dt>
 * <dd>
 * <ul>
 * <li>candiesProblem()</li>
 * <li>candiesAnswer()</li>
 * </ul>
 * </dd>
 * <dt>Others</dt>
 * <dd>
 * <ul>
 * <li>game()</li>
 * <li>gameAnswer()</li>
 * <li>challenge()</li>
 * <li>challengeAnswer()</li>
 * </ul>
 * </dd>
 * </dl>
 * </p>
 * All methods are using {@link HackerRankAPI}'s lower level methods to communicate with a
 * server.
 * 
 * @author Hiroshi Yamamoto
 * @see HackerRankAPI
 */
public class SplashAPI {

  public static final String URL_SIGNIN = URL_HACKERRANK + "/users/sign_in.json";
  public static final String URL_SIGNUP = URL_HACKERRANK + "/users.json";
  public static final String URL_SIGNOUT = URL_HACKERRANK
      + "/users/sign_out?remote=true&commit=Sign+out&utf8=%E2%9C%93";
  public static final String URL_USERSTATS = URL_HACKERRANK + "/splash/userstats.json";
  public static final String URL_COMMANDCOUNT = URL_HACKERRANK + "/splash/command_count.json";
  public static final String URL_LEADERBOARD = URL_HACKERRANK + "/splash/leaderboard.json";
  public static final String URL_CHALLENGE = URL_HACKERRANK + "/splash/challenge.json";
  public static final String URL_GAME = URL_HACKERRANK + "/game.json";
  
  private final HackerRankAPI h;

  public SplashAPI(HackerRankAPI api) {
    super();
    this.h = api;
  }

  public JsonGame spacexProblem(int problemNo) {
    JsonGame game = game(JsonGameImpl.class, problemNo);
    return game;
  }

  public JsonGameAnswer spacexAnswer(String gameId, String answerText) {
    JsonGameAnswer answer = gameAnswer(JsonGameAnswerImpl.class, gameId, answerText);
    return answer;
  }

  public JsonChallengeGame candiesProblem(int amountOfCandies) {
    JsonChallengeGame game = challenge(JsonChallengeGameImpl.class, amountOfCandies);
    return game;
  }

  public JsonChallenge candiesAnswer(int candiesToMove) {
    JsonChallenge challenge = challengeAnswer(JsonChallengeImpl.class, candiesToMove);
    return challenge;
  }


  /**
   * Requests sign-in to the server.
   */
  public JsonLogin sign_in(String username, String password) {
    Map<String, String> param = new HashMap<String, String>();
    {
      param.put("user[login]", username);
      param.put("user[remember_me]", "1");
      param.put("user[password]", password);
      param.put("commit", "Sign in");
      param.put("remote", "true");
      param.put("utf8", "true");
    }
    String response = h.send(URL_SIGNIN, param, "POST", 1);
    JsonLoginImpl jsonLogin = new Gson().fromJson(response, JsonLoginImpl.class);
    return jsonLogin;
  }

  /**
   * Requests sign-up to the server.
   */
  public JsonLogin sign_up(String email, String username, String password) {
    Map<String, String> param = new HashMap<String, String>();
    {
      param.put("remote", "true");
      param.put("commit", "Sign up");
      param.put("user[email]", email);
      param.put("user[username]", username);
      param.put("user[password]", password);
      // param.put("utf8", "true");
    }
    String response = h.send(URL_SIGNUP, param, "POST", 1);
    // Post -> Fail -> 422 Unprocessable Entity
    // {"errors":{"email":["has already been taken"],"username":["has already been taken"]}}
    // Post -> Success -> 201 Created
    // {"created_at":"2012-07-10T07:12:58+05:30","email":"a@a.com","id":00000,"updated_at":"2012-07-10T07:12:59+05:30","username":"a"}
    // https://www.hackerrank.com/splash/signup_callback.json
    // GET -> 304 Not Modified
    // {"share_hash":"disabled"}
    JsonLoginImpl jsonLogin = new Gson().fromJson(response, JsonLoginImpl.class);
    return jsonLogin;
  }

  /**
   * Requests sign-out to the server.
   */
  public String sign_out() {
    String response = h.send(URL_SIGNOUT, null, "GET", 1);
    return response;
  }

  /**
   * Retrieves a user status object from the server. <br/>
   * <i> * It seems that a <code>userstats</code> request causes logout. (2012/07/05)</i>
   * 
   * @return A user status object for the current user, or for "guest" user if you haven't sign-in.
   */
  public JsonUserStat userstats() {
    String response = h.send(URL_USERSTATS, null, "GET", 1);
    JsonUserStat json = new Gson().fromJson(response, JsonUserStat.class);
    return json;
  }

  /**
   * Retrieves the leader board information from the server.
   * 
   * @return A leader board object.
   */
  public JsonLeaderBoard leaderboard() {
    return leaderboard(-1);
  }

  /**
   * Retrieves the leader board information from the server.
   * 
   * @param limit a limit count. <i>the hard limit on the server looks to be 763. (2012/07/06) </i>
   * @return A leader board object.
   */
  public JsonLeaderBoard leaderboard(int limit) {
    String url = URL_LEADERBOARD;
    if (limit > 0) {
      url += "?limit=" + limit;
    }
    String response = h.send(url, null, "GET", 1);
    JsonLeaderBoard json = new Gson().fromJson(response, JsonLeaderBoard.class);
    return json;
  }

  public JsonCommandCount command_count() {
    String response = h.send(URL_COMMANDCOUNT, null, "GET", 1);
    JsonCommandCount json = new Gson().fromJson(response, JsonCommandCount.class);
    return json;
  }

  public <T> T challenge(Class<T> responseClass, int candies) {
    return challenge(responseClass, Integer.toString(candies));
  }

  public <T> T challenge(Class<T> responseClass, String candies) {
    return sendChallenge(URL_CHALLENGE, responseClass, candies);
  }

  public <T> T challengeAnswer(Class<T> responseClass, int candiesMove) {
    return challengeAnswer(responseClass, candiesMove, 1);
  }

  public <T> T challengeAnswer(Class<T> responseClass, int candiesMove, int scale) {
    Map<String, String> param = new HashMap<String, String>();
    {
      param.put("move", Integer.toString(candiesMove));
      // param.put( "id", game_id);
      param.put("remote", "true");
      param.put("utf8", "true");
    }
    String response = h.send(URL_CHALLENGE, param, "PUT", scale);
    T json = new Gson().fromJson(response, responseClass);
    return json;
  }

  public <T> T game(Class<T> responseClass, int candies) {
    return game(responseClass, Integer.toString(candies));
  }  
  
  public <T, U extends T> List<T> games(Class<U> responseClass, int candies, int scale) {
    List<T> gameList = new ArrayList<T>();
    for (int i = 0; i < scale; i++) {
      T game = game(responseClass, Integer.toString(candies));
      if (game != null) {
        gameList.add(game);
      }
    }
    return gameList;
  }

  public <T> T game(Class<T> responseClass, String candies) {
    // "n" : scientist_id,
    // "remote" : "true"
    return sendChallenge(URL_GAME, responseClass, candies, 1);
  }

  public <T> T gameAnswer(Class<T> responseClass, String game_id, String answer) {
    Map<String, String> param = new HashMap<String, String>();
    {
      param.put("answer", answer);
      param.put("id", game_id);
      param.put("remote", "false");
      param.put("utf8", "true");
    }
    String response = h.send(URL_GAME, param, "PUT", 1);
    // System.err.println(response);
    T json = new Gson().fromJson(response, responseClass);
    return json;
  }

  // "n" : scientist_id,
  // "remote" : "true"
  protected <T> T sendChallenge(String url, Class<T> responseClass, String candies, int scale) {
    Map<String, String> param = new HashMap<String, String>();
    {
      param.put("n", candies);
      param.put("remote", "true");
      // param.put( "utf8", "true");
    }
    String response = h.send(url, param, "POST", scale);
    T json = new Gson().fromJson(response, responseClass);
    return json;
  }

  public <T> T sendChallenge(String url, Class<T> responseClass, String candies) {
    return sendChallenge(url, responseClass, candies, 1);
  }
  
}
