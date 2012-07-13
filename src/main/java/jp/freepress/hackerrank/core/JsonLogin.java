package jp.freepress.hackerrank.core;

import java.util.List;
import java.util.Map;

/**
 * A class for a login information JSON Object. you get this object as the result of
 * <code>sign_up()</code>, <code>sign_in()</code>
 */
public interface JsonLogin {
  public String getCreated_at();

  public void setCreated_at(String created_at);

  public String getEmail();

  public void setEmail(String email);

  public String getId();

  public void setId(String id);

  public String getUpdated_at();

  public void setUpdated_at(String updated_at);

  public String getUsername();

  public void setUsername(String username);

  public Map<String, List<String>> getErrors();

  public void setErrors(Map<String, List<String>> errors);
}
