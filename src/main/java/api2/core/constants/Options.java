package api2.core.constants;

public enum Options {
    IGNORE_DIGITS("2"),
    IGNORE_URLS("4"),
    FIND_REPEAT_WORDS("8"),
    IGNORE_CAPITALIZATION("512");

    public String code;

    Options(String code) {
        this.code = code;
    }

    public static String sumOfOptions(Options ... options) {
        int sum = 0;
        for (Options option: options) {
            sum+= Integer.parseInt(option.code);
        }
        return String.valueOf(sum);
    }
}
