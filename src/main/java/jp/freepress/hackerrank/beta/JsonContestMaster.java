package jp.freepress.hackerrank.beta;

import com.google.gson.JsonElement;

// {"model":{"created_at":"2012-07-19T17:13:20Z","data":{},"id":1,"levels":3,"name":"Main Contest","slug":"master","updated_at":"2012-07-19T17:14:58Z"}}
public final class JsonContestMaster {

  private int id;

  private String name;

  private String levels;

  private String slug;

  private JsonElement data;

  private String updated_at;

  private String created_at;

  public JsonContestMaster() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLevels() {
    return levels;
  }

  public void setLevels(String levels) {
    this.levels = levels;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public JsonElement getData() {
    return data;
  }

  public void setData(JsonElement data) {
    this.data = data;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

}
