package jp.freepress.hackerrank.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


/**
 * <p>
 * <a href="http://hackerrank.com/"><tt>HackerRank</tt></a> API wrapper.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @see SplashAPI
 * @see BetaAPI
 */
public class HackerRankAPI {

  public static final String URL_HACKERRANK = "https://www.hackerrank.com";

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
  public String send(String url, Map<String, String> param, String methodStr, int scale) {
    HttpRequestBase method = createHttpRequest(url, param, methodStr);
    return send( method, scale);
  }

  /**
   * Sends a request with json and retrieves a content body of its response.
   * 
   * @param url A target URL
   * @param json json string to be sent
   * @param methodStr HTTP method name. ex) "GET", "POST", "PUT", "DELETE", etc.
   * @param scale always 1 for normal behavior. {@link HackerRankAPI} class ignores this parameter.
   */
  public String sendJson(String url, String json, String methodStr, int scale) {
    HttpRequestBase method = createHttpRequestWithJson(url, json, (String)null, methodStr);
    return send( method, scale);
  }
  
  public String send(HttpRequestBase method, int scale) {
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
    //String responseBodyStr = EntityUtils.toString(entity, "UTF-8");
    
    return responseBodyStr;
  }

  /**
   * @param json nullable
   * @param charset nullable (UTF-8)
   * @param methodStr one of { "POST", "PUT"}
   */
  protected HttpRequestBase createHttpRequestWithJson(String url, String json, String charset, String methodStr) {
    HttpRequestBase httpMethod = createHttpRequest( url, null, methodStr);
    if (methodStr != null && methodStr.equals("POST")) {
      HttpPost postMethod = (HttpPost)httpMethod;
      if ( json != null) {
        try {
          StringEntity input = charset != null ? new StringEntity(json, charset) : new StringEntity(json, "UTF-8");
          input.setContentType("application/json");
          postMethod.setEntity( input);
        } catch( UnsupportedEncodingException ueex) {
          throw new RuntimeException(ueex); // near impossible exception
        }
      }
    } else if (methodStr != null && methodStr.equals("PUT")){
      HttpPut putMethod = (HttpPut)httpMethod;
      if ( json != null) {
        try {
          StringEntity input = charset != null ? new StringEntity(json, charset) : new StringEntity(json, "UTF-8");
          input.setContentType("application/json");
          putMethod.setEntity( input);
        } catch( UnsupportedEncodingException ueex) {
          throw new RuntimeException(ueex); // near impossible exception
        }
      }
    } else {
      if ( json != null) {
        throw new IllegalArgumentException( methodStr + " doesn't support a request with json string.");
      }
    }
    return httpMethod;
  }
  
  /**
   * @param param nullable
   * @param methodStr one of {"GET", "POST", "PUT"}
   */
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
      method.setHeader("Accept-Encoding", "deflate,sdch");
      method.setHeader("Accept-Language", "en-US,pIqaD,en,ja;q=0.8");
      method.setHeader("Connection", "keep-alive");
      method.setHeader("Origin", "http://www.hackerrank.com");
      method.setHeader("Referer", "http://www.hackerrank.com/");
      method.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) "
          + "AppleWebKit/536.11 (KHTML, like Gecko) " + "Chrome/20.0.1132.57 Safari/536.11");
      method.setHeader("X-Requested-With", "XMLHttpRequest");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return method;
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
