package hw3;

import base.SeleniumBase;
import enums.NavBarElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import static enums.HomePageData.HOME_PAGE;
import static enums.SubHeader.JDI_GITHUB;
import static enums.TextsAboutBenefits.BENEFITS;
import static enums.Users.PITER_CHAILOVSKII;

public class CheckElementsWithPageObject extends SeleniumBase {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void checkElementsWithPageObject() {

        //1 Open test site by URL
        homePage.open(HOME_PAGE);

        //2 Assert Browser title
        homePage.getTitle(HOME_PAGE);

        //3 Perform login
        homePage.login(PITER_CHAILOVSKII);

        //4 Assert User name in the left-top side of screen that user is loggined
        homePage.userIsLogged(PITER_CHAILOVSKII);

        //5 Assert Browser title
        homePage.getTitle(HOME_PAGE);

        //6 Assert that there are 4 items on the header section are displayed and they have proper texts
        homePage.checkNavBarElements();

        //7 Assert that there are 4 images on the Index Page and they are displayed
        homePage.checkImagesDisplayed();

        //8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        homePage.checkBenefitText(BENEFITS);

        //9 Assert a text of the main headers
        homePage.checkMainHeaderHasText();

        //10 The iframe exists
        homePage.checkIframe();

        //11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        homePage.checkEpamLogoIntoIframe();

        //12 Switch to original window back
        homePage.switchToOriginalWindow();

        //13 Assert a text of the sub header
        homePage.checkSubHeaderText(JDI_GITHUB);

        //14 Assert that JDI GITHUB is a link and has a proper URL
        homePage.checkSubHeaderLink(JDI_GITHUB);

        //15 Assert that there is Left Section
        homePage.checkLeftSection();

        //16 Assert that there is Footer
        homePage.checkFooter();

    }
}
