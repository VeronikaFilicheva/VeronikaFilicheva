package hw4;

import base.SelenideBase;
import enums.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DifferentElementsPage;
import pageObjects.HomePageSelenide;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;
import static enums.HomePageData.HOME_PAGE;
import static enums.NavBarElements.SERVICE;
import static enums.ServiceCategories.HEADER_DIFFERENT_ELEMENTS;
import static enums.Users.PITER_CHAILOVSKII;

public class ServicePageInterfaceCheck extends SelenideBase {

    private HomePageSelenide homePageSelenide;
    private DifferentElementsPage differentElementsPage;

    @BeforeMethod
    public void initTest(){
        //1 Open test site by URL
        open("https://epam.github.io/JDI/index.html");

        homePageSelenide = page(HomePageSelenide.class);
        differentElementsPage = page(DifferentElementsPage.class);
    }

    @AfterMethod
    public void closeTest(){ close();}


    @Test
    public void servicePageInterfaceCheck () {
        //TODO; rename method - done
        //2 Assert Browser title
        homePageSelenide.checkPageTitle(HOME_PAGE);

        //3 Perform login
        homePageSelenide.login(PITER_CHAILOVSKII);

        //TODO; rename method - done
        // 4 Assert User name in the left-top side of screen that user is loggined
        homePageSelenide.checkUserName(PITER_CHAILOVSKII);

        //5 Click on "Service" subcategory in the header and check that drop down contains options
        //TODO; method should be parameterized - done
        //TODO; rename method, e.g. checkServiceHeaderMenu - done
        homePageSelenide.checkServiceHeaderMenu(SERVICE);

        //6 Click on Service subcategory in the left section and check that drop down contains options
        //TODO; method should be parameterized - done
        //TODO; rename method - done
        homePageSelenide.checkServiceLeftSectionMenu(LeftSection.SERVICE);

        //7 Open through the header menu Service -> Different Elements Page
        //TODO; rename method to short one - done
        homePageSelenide.openServiceSubMenu(HEADER_DIFFERENT_ELEMENTS);

        //8 Check interface on Different elements page, it contains all needed elements
        //TODO; rename method to short one - done
        differentElementsPage.checkDifferentElementsPageElements();

        //9 Assert that there is Right Section
        differentElementsPage.checkRightSection();

        //10 Assert that there is Left Section
        differentElementsPage.checkLeftSection();

        //11 Select checkboxes
        differentElementsPage.selectCheckboxes(Checkboxes.WATER,Checkboxes.WIND);

        //12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        differentElementsPage.checkLogsforCheckboxes(Checkboxes.WATER,Checkboxes.WIND);

        //13 Select radio (Selen)
        differentElementsPage.selectRadio(Radiobuttons.SELEN);

        //14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton
        differentElementsPage.checkLogsForRadiobuttons(Radiobuttons.SELEN);

        //15 Select in dropdown (Yellow)
        differentElementsPage.selectInDropdown(Dropdown.YELLOW);

        //16 Assert that for dropdown there is a log row and value is corresponded to the selected value.
        //TODO; rename method to "check...." - done
        differentElementsPage.checkdropdownLogs(Dropdown.YELLOW);

        //17 Unselect and assert checkboxes (Water, Wind)
        differentElementsPage.selectCheckboxes(Checkboxes.WATER,Checkboxes.WIND);

        //18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        differentElementsPage.checkLogsforCheckboxes(Checkboxes.WATER,Checkboxes.WIND);

    }
}
