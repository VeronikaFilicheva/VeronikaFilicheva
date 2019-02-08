package pageObjects;

import enums.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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
    private WebElement header;

//Action Methods

    public void open(HomePageData homePageData) {
        driver.navigate().to(homePageData.navigateTo);
    }

    public void login(Users user) {
        loginIcon.click();
        userField.sendKeys(user.login);
        passwordField.sendKeys(user.password);
        submitButton.click();
    }

    public void userIsLogged(Users user) {
        loggedInUserName.isDisplayed();
        assertEquals(loggedInUserName.getText(), user.name);
    }

    public void switchToOriginalWindow() {
        driver.switchTo().defaultContent();
    }

//Elements Check

    public void getTitle (HomePageData homePageData) {
        assertEquals(driver.getTitle(), homePageData.title);
    }

    public void checkNavBarElements(NavBarElements navBarElements) {
        assertEquals(navBar.size(),4);

        List<String> headerItems = new ArrayList<String>();
        headerItems.add(navBarElements.home);
        headerItems.add(navBarElements.contactForm);
        headerItems.add(navBarElements.service);
        headerItems.add(navBarElements.metalsAndColors);

        for (int i=0;i<navBar.size();i++ ) {
            assertTrue(navBar.get(i).isDisplayed());
            assertEquals(navBar.get(i).getText(),headerItems.get(i));
        }

    }

    public void checkImagesDisplayed() {
        assertEquals(benefitIconsImages.size(),4);
        for (WebElement element : benefitIconsImages){
            assertTrue(element.isDisplayed());
        }
    }

    public void checkBenefitText(TextsAboutBenefits textsAboutBenefits) {
        assertEquals(benefitTexts.size(), 4);

        List<String> texts=new ArrayList<String>();
        texts.add(textsAboutBenefits.practize);
        texts.add(textsAboutBenefits.custom);
        texts.add(textsAboutBenefits.platform);
        texts.add(textsAboutBenefits.base);

        for (int i=0;i<benefitTexts.size();i++) {
            assertTrue(benefitTexts.get(i).isDisplayed());
            assertEquals(benefitTexts.get(i).getText(),texts.get(i));
        }

    }

    public void checkMainHeaderHasText() {
        assertEquals(textOnMainTitle.getText(), "EPAM FRAMEWORK WISHES…");
        assertEquals(titleText.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
                "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, " +
                "QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS " +
                "AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
    }

    public void checkIframe() {
        assertTrue(iFrame.isDisplayed());
    }

    public void checkEpamLogoIntoIframe() {
        driver.switchTo().frame(iFrame);
        assertTrue(epamLogoInIfrrame.isDisplayed());
    }

    public void checkSubHeaderText(SubHeader subHeader) {
        assertTrue(header.isDisplayed());
        assertEquals(header.getText(), subHeader.name);
    }

    public void checkSubHeaderLink(SubHeader subHeader) {
        assertEquals(header.getAttribute("href"), subHeader.link);
    }


    public void checkLeftSection() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooter() {
        assertTrue(footer.isDisplayed());
    }

}
