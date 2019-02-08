package enums;

public enum  TextsAboutBenefits {
    BENEFITS("To include good practices\n" + "and ideas from successful\n" + "EPAM project",
            "To be flexible and\n" + "customizable", "To be multiplatform", "Already have good base\n" +
            "(about 20 internal and\n" + "some external projects),\n" +
            "wish to get more…");

    public String practize;
    public String custom;
    public String platform;
    public String base;

    TextsAboutBenefits(String practize,String custom, String platform, String base){
        this.practize=practize;
        this.custom=custom;
        this.platform=platform;
        this.base=base;
    }

}
