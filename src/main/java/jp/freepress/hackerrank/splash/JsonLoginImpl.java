package jp.freepress.hackerrank.splash;

import java.util.List;
import java.util.Map;

// "{\"created_at\":\"2012-07-03T13:34:21+05:30\",\"email\":\"xxx@xxxx.com\",\"id\":9999,\"updated_at\":\"2012-07-04T15:21:38+05:30\",\"username\":\"XXXXX\"}";

public class JsonLoginImpl implements JsonLogin {

  private String created_at;

  private String email;

  private String id;

  private String updated_at;

  private String username;

  private Map<String, List<String>> errors;

  private JsonLoginImpl() {
    super();
  }

  @Override
  public String getCreated_at() {
    return created_at;
  }

  @Override
  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getUpdated_at() {
    return updated_at;
  }

  @Override
  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public Map<String, List<String>> getErrors() {
    return errors;
  }

  @Override
  public void setErrors(Map<String, List<String>> errors) {
    this.errors = errors;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Login [created_at=");
    builder.append(created_at);
    builder.append(", email=");
    builder.append(email);
    builder.append(", id=");
    builder.append(id);
    builder.append(", updated_at=");
    builder.append(updated_at);
    builder.append(", username=");
    builder.append(username);
    if (errors != null && !errors.isEmpty()) {
      builder.append(", errors=");
      builder.append(errors);
    }
    builder.append("]");
    return builder.toString();
  }

}
