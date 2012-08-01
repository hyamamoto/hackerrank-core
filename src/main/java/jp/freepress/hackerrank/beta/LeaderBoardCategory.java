package jp.freepress.hackerrank.beta;

public enum LeaderBoardCategory {
  Level("level"), Language("language"), Country("country");
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private LeaderBoardCategory(String slug) {
    this.id = slug;
  }

  public static LeaderBoardCategory match( String id) {
    for (LeaderBoardCategory c: values()) {
      if ( c.name().equals( id) || c.getId().equals( id)) {
        return c;
      }
    }
    return null;
  }

  public static String toCategoryIdListString() {
    StringBuilder buf = new StringBuilder();
    boolean vgn = true;
    for (LeaderBoardCategory cat : values()) {
      if (!vgn) {
        buf.append(", ");
      }
      buf.append(cat.getId());
      vgn = false;
    }
    return buf.toString();
  }
}
