package jp.freepress.hackerrank.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.freepress.hackerrank.splash.SplashAPI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;


/**
 * <p>
 * <a href="http://hackerrank.com/"><tt>HackerRank</tt></a> API wrapper.
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
 * <dt>Server Communication (you better use {@link SplashAPI} instead)</dt>
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
 * 
 * @author Hiroshi Yamamoto
 * @see SplashAPI
 */
public class HackerRankAPI {

  public static final String URL_HACKERRANK = "https://www.hackerrank.com";
  public static final String URL_SIGNIN = URL_HACKERRANK + "/users/sign_in.json";
  public static final String URL_SIGNUP = URL_HACKERRANK + "/users.json";
  public static final String URL_SIGNOUT = URL_HACKERRANK
      + "/users/sign_out?remote=true&commit=Sign+out&utf8=%E2%9C%93";
  public static final String URL_USERSTATS = URL_HACKERRANK + "/splash/userstats.json";
  public static final String URL_COMMANDCOUNT = URL_HACKERRANK + "/splash/command_count.json";
  public static final String URL_LEADERBOARD = URL_HACKERRANK + "/splash/leaderboard.json";
  public static final String URL_CHALLENGE = URL_HACKERRANK + "/splash/challenge.json";
  public static final String URL_GAME = URL_HACKERRANK + "/game.json";

  protected int lastStatusCode; // concurrency? what's that?
  // be used only on the same thread.
  protected String lastStatusLine; // concurrency? what's that?

  protected BearHttpClient client;

  public HackerRankAPI() {
    super();
    // Create an instance of HttpClient.
    client = createHttpClient();
  }

  private BearHttpClient createHttpClient() {
    BearHttpClient httpClient = new BearHttpClient();
    // Provide custom retry handler
    httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
    return httpClient;
  }

  /**
   * Sends a request and retrieves a content body of its response.
   * 
   * @param url A target URL
   * @param param A {key:value} map for query parameters.
   * @param methodStr HTTP method name. ex) "GET", "POST", "PUT", "DELETE", etc.
   */
  // public final String send(String url, Map<String, String> param, String methodStr) {
  // return send(url, param, methodStr, 1);
  // }

  /**
   * Sends a request and retrieves a content body of its response.
   * 
   * @param url A target URL
   * @param param A {key:value} map for query parameters.
   * @param methodStr HTTP method name. ex) "GET", "POST", "PUT", "DELETE", etc.
   * @param scale always 1 for normal behavior. {@link HackerRankAPI} class ignores this parameter.
   */
  protected String send(String url, Map<String, String> param, String methodStr, int scale) {
    HttpRequestBase method = createHttpRequest(url, param, methodStr);

    try {
      return executeAndGetBody(method);
    } catch (ClientProtocolException e) {
      Logger log = Logger.getLogger(HackerRankAPI.class.getName());
      log.log(Level.WARNING, "Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      Logger log = Logger.getLogger(HackerRankAPI.class.getName());
      log.log(Level.WARNING, "Fatal transport error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      // Release the connection.
      if (method != null) method.releaseConnection();
    }

    return null;
  }

  protected String executeAndGetBody(HttpRequestBase method) throws IOException,
      ClientProtocolException {
    // Execute the method.
    HttpResponse response = client.execute(method);
    int statusCode = response.getStatusLine().getStatusCode();
    lastStatusCode = statusCode;

    if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED) {
      Logger log = Logger.getLogger(HackerRankAPI.class.getName());
      String statusLine = response.getStatusLine().toString();
      lastStatusLine = statusLine;
      log.log(Level.WARNING, "Method failed: [" + statusLine + "], [" + method.getMethod() + "], ["
          + method.getURI() + "]");
    } else {
      lastStatusLine = null;
    }

    // Read the response body.
    HttpEntity entity = response.getEntity();
    byte[] responseBody = EntityUtils.toByteArray(entity);

    // Deal with the response.
    // Use caution: ensure correct character encoding and is not binary data
    String responseBodyStr = new String(responseBody);
    return responseBodyStr;
  }

  protected HttpRequestBase createHttpRequest(String url, Map<String, String> param,
      String methodStr) {
    // Create a method instance.
    HttpRequestBase method = null;
    try {
      if (methodStr != null && methodStr.equals("GET")) {
        HttpGet getMethod = new HttpGet(url);
        // if ( param != null) {
        // for ( Map.Entry<String, String> e: param.entrySet()) {
        // getMethod.setQueryString( e.getKey(), e.getValue());
        // }
        // }
        method = getMethod;
      } else if (methodStr != null && methodStr.equals("PUT")) {
        HttpPut putMethod = new HttpPut(url);
        if (param != null) {
          List<NameValuePair> pairs = new ArrayList<NameValuePair>(param.size());
          for (Map.Entry<String, String> e : param.entrySet()) {
            pairs.add(new BasicNameValuePair(e.getKey(), e.getValue()));
          }
          putMethod.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
        }
        method = putMethod;
      } else {
        HttpPost postMethod = new HttpPost(url);
        if (param != null) {
          List<NameValuePair> pairs = new ArrayList<NameValuePair>(param.size());
          for (Map.Entry<String, String> e : param.entrySet()) {
            pairs.add(new BasicNameValuePair(e.getKey(), e.getValue()));
          }
          postMethod.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
        }
        method = postMethod;
        method.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=");
      }
      method.setHeader("Accept", "*/*");
      method.setHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
      method.setHeader("Accept-Encoding", "gzip,deflate,sdch");
      method.setHeader("Accept-Language", "en-US,pIqaD,en,ja;q=0.8");
      method.setHeader("Connection", "keep-alive");
      method.setHeader("Origin", "http://www.hackerrank.com");
      method.setHeader("Referer", "http://www.hackerrank.com/");
      method.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) "
          + "AppleWebKit/536.11 (KHTML, like Gecko) " + "Chrome/20.0.1132.47 Safari/536.11");
      method.setHeader("X-Requested-With", "XMLHttpRequest");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return method;
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
    String response = send(URL_SIGNIN, param, "POST", 1);
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
    String response = send(URL_SIGNUP, param, "POST", 1);
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
    String response = send(URL_SIGNOUT, null, "GET", 1);
    return response;
  }

  /**
   * Retrieves a user status object from the server. <br/>
   * <i> * It seems that a <code>userstats</code> request causes logout. (2012/07/05)</i>
   * 
   * @return A user status object for the current user, or for "guest" user if you haven't sign-in.
   */
  public JsonUserStat userstats() {
    String response = send(URL_USERSTATS, null, "GET", 1);
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
    String response = send(url, null, "GET", 1);
    JsonLeaderBoard json = new Gson().fromJson(response, JsonLeaderBoard.class);
    return json;
  }

  public JsonCommandCount command_count() {
    String response = send(URL_COMMANDCOUNT, null, "GET", 1);
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
    String response = send(URL_CHALLENGE, param, "PUT", scale);
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
    String response = send(URL_GAME, param, "PUT", 1);
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
    String response = send(url, param, "POST", scale);
    T json = new Gson().fromJson(response, responseClass);
    return json;
  }

  public <T> T sendChallenge(String url, Class<T> responseClass, String candies) {
    return sendChallenge(url, responseClass, candies, 1);
  }

  public int getLastStatusCode() {
    return lastStatusCode;
  }

  public String getLastStatusLine() {
    return lastStatusLine;
  }

  /**
   * Delete cookies by recreating inner {@link HttpClient} object.
   */
  public void deleteCookie() {
    // refresh client
    client = createHttpClient();
  }

  /**
   * Extended HttpClient you can extract more client infomation from. </br> This is for those who
   * desires more customization.
   */
  protected static class BearHttpClient extends DefaultHttpClient {
    @Override
    public HttpContext createHttpContext() {
      return super.createHttpContext();
    }

    @Override
    public BasicHttpProcessor createHttpProcessor() {
      return super.createHttpProcessor();
    }

    @Override
    public HttpParams createHttpParams() {
      return super.createHttpParams();
    }
  }
}
