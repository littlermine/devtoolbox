package org.devtoolbox.errorhandling.exception;

public class FileMoverException extends RuntimeException {

	private static final long serialVersionUID = -6844989999898052651L;

	public FileMoverException(String message) {
		
		super(message);
	}

	public FileMoverException(Throwable cause) {
		
		super(cause);
	}

	public FileMoverException(String message, Throwable cause) {
		
		super(message, cause);
	}
}
