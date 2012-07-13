package jp.freepress.hackerrank.core;

/**
 * @param <P>
 * @param <A>
 */
public interface Solver<P extends Problem, A extends Answer> {
  public A solve(P problem);
}
