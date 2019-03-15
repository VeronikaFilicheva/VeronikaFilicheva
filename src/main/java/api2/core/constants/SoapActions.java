package api2.core.constants;

public enum SoapActions {
    CHECK_TEXT("checkText"),
    CHECK_TEXTS("checkTexts");
    private String method;

    public String getMethod() {
        return method;
    }


    SoapActions(String action) {
        this.method = action;

    }
}


