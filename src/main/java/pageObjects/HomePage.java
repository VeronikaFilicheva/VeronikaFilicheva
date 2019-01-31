package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    @FindBy(css = "[id='name']")
    private WebElement userField;

    @FindBy(css = ("[id='password']"))
    private WebElement passwordField;

    @FindBy(css = "[id='login-button']")
    private WebElement submitButton;

    @FindBy(css = "[id='user-icon']")
    private WebElement loginIcon;

    @FindBy (css = ".profile-photo [ui = 'label']")
    private WebElement loggedInUserName;

    @FindBy(css = ".nav > li")
    private List<WebElement> navBar;

    @FindBy(css =".benefit-icon")
    private List<WebElement> benefitIconsImages;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> benefitTexts;

    @FindBy(css = "h3.main-title.text-center")
    private WebElement textOnMainTitle;

    @FindBy(css = "p.main-txt")
    private WebElement titleText;

    @FindBy(css = ("[id = 'iframe']"))
    private WebElement iFrame;

    @FindBy(css = ("[id='epam_logo']"))
    private WebElement epamLogoInIfrrame;

    @FindBy(css = ".mCustomScrollBox")
    private WebElement leftSection;

    @FindBy(css = "footer")
    private WebElement footer;

    @FindBy(css = "h3.text-center > a")
    private WebElement subHeader;

//Action Methods

    public void open(Object object){
        driver.navigate().to(object.toString());
    }

    public void login(String name, String password){
        loginIcon.click();
        userField.sendKeys(name);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public void userIsLogged(String s) {
        loggedInUserName.isDisplayed();
        assertEquals(loggedInUserName.getText(), s);
    }

    public void switchToOriginalWindow(){
        driver.switchTo().defaultContent();
    }

//Elements Check

    public void getTitle(Object object) {
        assertEquals(driver.getTitle(), object.toString());
    }

    public void checkNavBarElements(){
        assertEquals(navBar.size(),4);
        for (WebElement element : navBar) {
            assertTrue(element.isDisplayed());
        }
        assertEquals(navBar.get(0).getText(), "HOME");
        assertEquals(navBar.get(1).getText(), "CONTACT FORM");
        assertEquals(navBar.get(2).getText(), "SERVICE");
        assertEquals(navBar.get(3).getText(), "METALS & COLORS");
    }

    public void checkImagesDisplayed(){
        assertEquals(benefitIconsImages.size(),4);
        for (WebElement element : benefitIconsImages){
            assertTrue(element.isDisplayed());
        }
    }

    public void checkBenefitText() {
        assertEquals(benefitTexts.size(), 4);
        for (WebElement element : benefitTexts) {
            element.isDisplayed();
        }
        assertEquals(benefitTexts.get(0)
                .getText(), "To include good practices\n" +
                "and ideas from successful\n" +
                "EPAM project");
        assertEquals(benefitTexts.get(1)
                .getText(), "To be flexible and\n" +
                "customizable");
        assertEquals(benefitTexts.get(2)
                .getText(), "To be multiplatform");
        assertEquals(benefitTexts.get(3)
                .getText(), "Already have good base\n" +
                "(about 20 internal and\n" +
                "some external projects),\n" +
                "wish to get more…");
    }

    public void checkMainHeaderHasText() {
        assertEquals(textOnMainTitle.getText(), "EPAM FRAMEWORK WISHES…");
        assertEquals(titleText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
                "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, " +
                "QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS " +
                "AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
    }

    public void checkIframe(){
        assertTrue(iFrame.isDisplayed());
    }

    public void checkEpamLogointoIframe(){
        driver.switchTo().frame(iFrame);
        assertTrue(epamLogoInIfrrame.isDisplayed());
    }

    public void checkSubHeaderText(){
        assertTrue(subHeader.isDisplayed());
        assertEquals(subHeader.getText(),"JDI GITHUB");
    }

    public void checkSubHeaderLink(){
        assertEquals(subHeader.getAttribute("href"),"https://github.com/epam/JDI");
    }


    public void checkLeftSection(){
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooter(){
        assertTrue(footer.isDisplayed());
    }

}
