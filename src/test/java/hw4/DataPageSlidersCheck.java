package hw4;

import base.SelenideBase;
import enums.ServiceCategories;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DatesPage;
import pageObjects.HomePageSelenide;

import static com.codeborne.selenide.Selenide.*;
import static enums.HomePageData.HOME_PAGE;
import static enums.Users.PITER_CHAILOVSKII;

public class DataPageSlidersCheck extends SelenideBase {
    private HomePageSelenide homePageSelenide;
    private DatesPage datesPage;

    @BeforeMethod
    public void initTest(){
        //1 Open test site by URL
        open("https://epam.github.io/JDI/index.html");

        homePageSelenide = page(HomePageSelenide.class);
        datesPage = page(DatesPage.class);
    }

    @AfterMethod
    public void closeTest(){ close();}


    @Test
    public void dataPageSlidersCheck() {

        //TODO: rename method; get() tells that we should GET some data from object; - done
        //2 Assert Browser title
        homePageSelenide.checkTitle(HOME_PAGE);

        //3 Perform login
        homePageSelenide.login(PITER_CHAILOVSKII);

        // TODO: rename method - done
        // 4 Assert User name in the left-top side of screen that user is loggined
        homePageSelenide.checkUserIsLogged(PITER_CHAILOVSKII);

        //5 Open through the header menu Service -> Dates Page
        //TODO: avoid to use huge names; better to rename method -> openDatesPage() or openServicePage(String page)
        //done, this method can open any submenu
        homePageSelenide.openServiceSubMenu(ServiceCategories.HEADER_DATES);

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        //TODO: rename, e.g. setSlidersPosition - done
        datesPage. setSlidersPosition(0,100);

        //7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLogs("0","100");

        //8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        datesPage. setSlidersPosition(0, 0);

        //9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLogs("0","0");

        //10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        datesPage. setSlidersPosition(100, 100);

        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLogs("100","100");

        //12 Using drag-and-drop set Range sliders.
        datesPage. setSlidersPosition(30,70);

        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLogs("30","70");

    }
}
