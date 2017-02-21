package com.fondesa.quicksavestate.exception;

/**
 * Created by antoniolig on 22/02/17.
 */
public class CoderNotFoundException extends Exception {
    private static final long serialVersionUID = 8422996591439458736L;

    public CoderNotFoundException() {
        super();
    }

    public CoderNotFoundException(String message) {
        super(message);
    }

    public CoderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoderNotFoundException(Throwable cause) {
        super(cause);
    }
}
