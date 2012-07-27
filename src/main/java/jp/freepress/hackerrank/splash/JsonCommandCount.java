package jp.freepress.hackerrank.splash;

// https://www.hackerrank.com/splash/command_count.json
// {"ls":9,"pwd":1}

/**
 * Unknown counter data.S
 */
public final class JsonCommandCount {

  private int ls;
  private int pwd;

  public JsonCommandCount() {
    super();
  }

  public int getLs() {
    return ls;
  }

  public void setLs(int ls) {
    this.ls = ls;
  }

  public int getPwd() {
    return pwd;
  }

  public void setPwd(int pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return "CommandCount [ls=" + ls + ", pwd=" + pwd + "]";
  }

}
