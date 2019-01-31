package enums;

import pageObjects.HomePage;

public enum HomePageData {

    HOME_PAGE("https://epam.github.io/JDI/index.html", "Home Page");

    public String navigateTo;
    public String title;

    HomePageData(String navigateTo, String title) {
        this.navigateTo = navigateTo;
        this.title = title;
    }

}
