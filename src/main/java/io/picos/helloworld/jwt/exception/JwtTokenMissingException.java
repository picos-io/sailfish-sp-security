package io.picos.helloworld.jwt.exception;

public class JwtTokenMissingException extends RuntimeException {

    public JwtTokenMissingException() {
    }

    public JwtTokenMissingException(String message) {
        super(message);
    }

    public JwtTokenMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenMissingException(Throwable cause) {
        super(cause);
    }

    public JwtTokenMissingException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
