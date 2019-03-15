package api2.core.constants;

public enum ErrorCodes {
    ERROR_UNKNOWN_WORD(1),
    ERROR_REPEAT_WORD(2),
    ERROR_CAPITALIZATION(3),
    ERROR_TOO_MANY_ERRORS(4);

    public int errorCode;

    ErrorCodes(int errorCode) {
        this.errorCode = errorCode;
    }
}