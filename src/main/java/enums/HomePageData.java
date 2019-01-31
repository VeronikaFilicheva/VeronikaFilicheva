package enums;

import pageObjects.HomePage;

public enum HomePageData {

    HOME_PAGE_TITLE ("Home Page"),
    INDEX_HTML_URL ("https://epam.github.io/JDI/");

    private String value;

    HomePageData(String value){this.value=value;}

    @Override
    public String toString(){return value;}

}
