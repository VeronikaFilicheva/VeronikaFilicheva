package hw5;

import base.SelenideBase;
import enums.ServiceCategories;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.hw5.DatesPage;
import pageObjects.hw5.HomePageSelenide;

import static com.codeborne.selenide.Selenide.*;
import static enums.HomePageData.HOME_PAGE;
import static enums.Users.PITER_CHAILOVSKII;

@Feature("Smoke tests")
@Story("Dates page test")
@Listeners(AllureAttachmentListener.class)
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

        //2 Assert Browser title
        homePageSelenide.checkPageTitle(HOME_PAGE);

        //3 Perform login
        homePageSelenide.login(PITER_CHAILOVSKII);

        // 4 Assert User name in the left-top side of screen that user is loggined
        homePageSelenide.checkUserName(PITER_CHAILOVSKII);

        //5 Open through the header menu Service -> Dates Page
        homePageSelenide.openServiceSubMenu(ServiceCategories.HEADER_DATES);

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
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
