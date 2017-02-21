package com.fondesa.quicksavestate.exception;

/**
 * Created by antoniolig on 22/02/17.
 */
public class CoderUnsupportedException extends Exception {
    private static final long serialVersionUID = -3697148243138638518L;

    public CoderUnsupportedException() {
        super();
    }

    public CoderUnsupportedException(String message) {
        super(message);
    }

    public CoderUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoderUnsupportedException(Throwable cause) {
        super(cause);
    }
}