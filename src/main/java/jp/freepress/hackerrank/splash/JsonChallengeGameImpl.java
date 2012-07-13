package jp.freepress.hackerrank.splash;

public class JsonChallengeGameImpl implements JsonChallengeGame {

  private int n; // number of candies

  private int current; // number of remain candies

  private int limit = -1; //

  private int moves = -1; // often empty

  private String solved; // Boolean

  @Override
  public int getN() {
    return n;
  }

  @Override
  public void setN(int n) {
    this.n = n;
  }

  @Override
  public int getCurrent() {
    return current;
  }

  @Override
  public void setCurrent(int current) {
    this.current = current;
  }

  @Override
  public int getLimit() {
    return limit;
  }

  @Override
  public void setLimit(int limit) {
    this.limit = limit;
  }

  @Override
  public int getMoves() {
    return moves;
  }

  @Override
  public void setMoves(int moves) {
    this.moves = moves;
  }

  @Override
  public String getSolved() {
    return solved;
  }

  @Override
  public void setSolved(String solved) {
    this.solved = solved;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ChallengeGame [n=");
    builder.append(n);
    builder.append(", current=");
    builder.append(current);
    if (limit != -1) {
      builder.append(", limit=");
      builder.append(limit);
    }
    if (moves != -1) {
      builder.append(", moves=");
      builder.append(moves);
    }
    builder.append(", solved=");
    builder.append(solved);
    builder.append("]");
    return builder.toString();
  }

}
