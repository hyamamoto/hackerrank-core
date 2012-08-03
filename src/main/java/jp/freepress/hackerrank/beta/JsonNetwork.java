package jp.freepress.hackerrank.beta;

import org.apache.commons.lang3.StringUtils;

// {"model":{"school":"","country":"","company":"","languages":"","id":0,"username":"higon","uid":null}}
// { "model" : { "avatar" :
// "https://secure.gravatar.com/avatar/7d933dee0240cb6b0a5500034e285fa1?d=wavatar",
// "company" : "",
// "country" : "",
// "followed" : false,
// "followers" : [ ],
// "following" : [ ],
// "follows" : false,
// "languages" : "python",
// "level" : 3,
// "school" : "",
// "score" : 88.0,
// "suggestions" : [ { "hackers" : [ ],
// "name" : ""
// },
// { "hackers" : [ ],
// "name" : ""
// },
// { "hackers" : [ ],
// "name" : ""
// }
// ],
// "username" : "yushi"
// }
public final class JsonNetwork {

  private int id;

  private String username;

  private String school;

  private String country;

  private String company;

  private String languages;

  private String uid; // null, existed til 08/01/2012

  private String avatar;//

  private boolean followed = false;
  private boolean follows = false;

  private int level;

  // private JsonArray followers;
  // private JsonArray following;
  private JsonNetworkSuggestion[] suggestions;

  public JsonNetwork() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getLanguages() {
    return languages;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public boolean isFollowed() {
    return followed;
  }

  public void setFollowed(boolean followed) {
    this.followed = followed;
  }

  public boolean isFollows() {
    return follows;
  }

  public void setFollows(boolean follows) {
    this.follows = follows;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public JsonNetworkSuggestion[] getSuggestions() {
    return suggestions;
  }

  public void setSuggestions(JsonNetworkSuggestion[] suggestions) {
    this.suggestions = suggestions;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Network [username=");
    builder.append(username);
    builder.append(", school=");
    builder.append(school);
    builder.append(", country=");
    builder.append(country);
    builder.append(", company=");
    builder.append(company);
    builder.append(", languages=");
    builder.append(languages);
    builder.append(", level=");
    builder.append(level);
    builder.append("]");
    return builder.toString();
  }

  public static class Util {
    public static String toSimpleLineString(JsonNetwork model) {
      final String FOLLOWED = "followed";
      final String FOLLOWS = "follows";

      StringBuilder builder = new StringBuilder();
      // builder.append("[");
      // builder.append(StringUtils.leftPad(Integer.toString(model.id), 2));
      // builder.append("], ");
      builder.append(StringUtils.rightPad(model.username, 18));
      builder.append(", ");
      builder.append(StringUtils.rightPad("lv." + model.level, 5));
      builder.append(",");
      builder.append(StringUtils.rightPad(model.school, 8));
      builder.append(", ");
      builder.append(StringUtils.rightPad(model.company, 8));
      builder.append(", ");
      builder.append(StringUtils.rightPad(model.country, 8));
      builder.append(", ");
      builder.append(StringUtils.rightPad(Language.toLanguageNames(model.languages).toString(), 7)); 
      builder.append(", ");
      builder.append(StringUtils.rightPad(model.followed ? FOLLOWED : "", FOLLOWED.length()));
      builder.append(", ");
      builder.append(StringUtils.rightPad(model.follows ? FOLLOWS : "", FOLLOWS.length()));
      return builder.toString();
    }
  }
}
