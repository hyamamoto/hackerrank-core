package jp.freepress.hackerrank.beta;

import java.util.Arrays;

// {
// "hackers" : [ ],
// "name" : ""
// }

public final class JsonNetworkSuggestion {


  private String[] hackers;

  private String name;

  public JsonNetworkSuggestion() {
    super();
  }

  public String[] getHackers() {
    return hackers;
  }

  public void setHackers(String[] hackers) {
    this.hackers = hackers;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Suggestion [hackers=");
    builder.append(Arrays.toString(hackers));
    builder.append(", name=");
    builder.append(name);
    builder.append("]");
    return builder.toString();
  }

  public static class Util {
    public static String toSimpleLineString(JsonNetworkSuggestion model) {
      StringBuilder builder = new StringBuilder();
      builder.append(model.getName());
      builder.append(", ");
      builder.append(Arrays.toString(model.getHackers()));
      return builder.toString();
    }
  }
}
