package org.usfirst.frc.team5687.robot.commands;

/**
 * Custom exception class for Outliers script parsing exceptions.
 * @author Ben Bernard
 *
 */
public class ScriptParseException extends RuntimeException {

	public ScriptParseException() {
		super("An unknown script parse exception occurred.");
	}

	public ScriptParseException(String message) {
		super(message);
	}

	public ScriptParseException(Throwable cause) {
		super(cause);
	}

	public ScriptParseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Convenience constructor which accepts a format string and format arguments.
	 * @param message
	 * @param args
	 */
	public ScriptParseException(String message, Object...args) {
		super(String.format(message,args));
	}

}
