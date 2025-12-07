/*package com.frauddetection.exception;

public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
*/


package com.frauddetection.exception;

public class AppException extends RuntimeException {
    public AppException(String msg) { super(msg); }
    public AppException(String msg, Throwable t) { super(msg, t); }
}
