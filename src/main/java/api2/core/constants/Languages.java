package api2.core.constants;

public enum Languages {
    EN("en"),
    RU("ru"),
    UK("uk"),
    IT("it");

    private final String lang;

    Languages(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
