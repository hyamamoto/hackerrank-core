package jp.freepress.hackerrank.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;

/**
 * Logging class for {@link Solver}.<br/>
 * This stores a log file in {@link CoreConstants#PATH_LOG()} directory.
 * 
 * @author Hiroshi Yamamoto
 */
public class SolverLog {

  private static SolverLog log;

  public static SolverLog get() {
    if (log == null) {
      log = new SolverLog();
    }
    return log;
  }

  public static SolverLog create(String processId) {
    return new SolverLog(processId);
  }

  private PrintWriter out = null;
  private String filename = null;

  private SolverLog() {
    initIO((String) null);
  }

  private SolverLog(String processId) {
    initIO(processId);
  }

  protected void initIO(String processId) {
    if (processId == null || processId.isEmpty()) {
      filename = CoreConstants.get().PATH_LOG() + "/solver.txt";
    } else {
      filename = CoreConstants.get().PATH_LOG() + "/solver." + processId + ".txt";
    }
    File file = new File(filename);
    BufferedOutputStream bos;
    try {
      bos = new BufferedOutputStream(new FileOutputStream(file, true));
    } catch (FileNotFoundException e) {
      System.err.println("Can not open a log file. Maybe the log directory doesn't exist? \n"
          + e.getMessage() + ". ");
      throw new RuntimeException(e); // XXX: supress this stack trace eventually.
    }
    out = new PrintWriter(bos);

    Thread shutdownThread = new Thread() {
      public void run() {
        if (out != null) {
          IOUtils.closeQuietly(out);
        }
      };
    };
    Runtime.getRuntime().addShutdownHook(shutdownThread);
  }

  public void log(String probKind, int probNo, String action, String message, Object data) {
    log(probKind, Integer.toString(probNo), action, message, data);
  }

  public void log(String probKind, String probNo, String action, String message, Object data) {
    String line = buildLine(probKind, probNo, action, message, data);
    if (CoreConstants.get().SOLVERLOG_SYSOUT()) {
      System.out.println(line);
    }
    out.println(line);
  }

  public String buildLine(String probKind, String probNo, String action, String message, Object data) {
    StringBuilder builder = new StringBuilder();
    builder.append("");
    builder.append(System.currentTimeMillis());
    builder.append(",");
    builder.append(_(probKind));
    builder.append(",");
    builder.append(_(probNo));
    builder.append(",");
    builder.append(_(action));
    builder.append(",");
    builder.append(_(message));
    builder.append(",");
    builder.append(_(data));
    builder.append("");
    String line = builder.toString();
    return line;
  }

  /**
   * Escapes double quotes for csv text. <code><b>"</b> => <b>\"</b></code>
   */
  private String _(Object obj) {
    String str;
    str = obj instanceof String ? (String) obj : obj != null ? obj.toString() : null;
    str = str != null ? "\"" + str.replace("\"", "\\\"").replace("\n", "\\n") + "\"" : "\"\"";
    return str;
  }

  public String getFilename() {
    return filename;
  }

}
