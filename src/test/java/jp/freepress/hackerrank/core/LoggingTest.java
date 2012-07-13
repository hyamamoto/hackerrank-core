package jp.freepress.hackerrank.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// NOTE: this really isn't a test. they are just snippets
public class LoggingTest extends TestCase {

	public void testLog() {
		System.err.println();
		System.err.println( "initating commons-logging");
		System.err.println( "=========================");
		Log LOG = LogFactory.getLog(LoggingTest.class);
		LOG.trace("trace");
		LOG.debug("debug");
		LOG.info("info");
		LOG.warn("warn");
		LOG.error("error");
		LOG.fatal("fatal");
	}

	public void testLogging() {
		System.err.println();
		System.err.println( "initating java-logging");
		System.err.println( "======================");
		Logger LOG = Logger.getLogger( LoggingTest.class.getName());
		LOG.log( Level.FINER, "FINER");
		LOG.log( Level.FINE, "FINE");
		LOG.log( Level.INFO, "INFO");
		LOG.log( Level.WARNING, "WARN");
		LOG.log( Level.SEVERE, "SEVERE");
	}
}
