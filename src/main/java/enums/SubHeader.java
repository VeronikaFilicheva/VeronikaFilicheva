package enums;

public enum  SubHeader {
    JDI_GITHUB("JDI GITHUB", "https://github.com/epam/JDI");

    public String name;
    public String link;

    SubHeader(String name, String link){
        this.name = name;
        this.link = link;
    }
}
