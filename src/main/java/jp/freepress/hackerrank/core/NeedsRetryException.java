package jp.freepress.hackerrank.core;

/**
 * Exception thrown when the client needs to stop the procedure and retry.
 */
@SuppressWarnings("serial")
public class NeedsRetryException extends RuntimeException {

  public NeedsRetryException() {
    super();
  }

  public NeedsRetryException(String message, Throwable th) {
    super(message, th);
  }

  public NeedsRetryException(String message) {
    super(message);
  }

  public NeedsRetryException(Throwable th) {
    super(th);
  }

}
