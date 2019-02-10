package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.HomePageData;
import enums.ServiceCategories;
import enums.Users;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.LeftSectionMenu.getLeftSectionCategoriesList;
import static enums.ServiceCategories.getHeaderCategoriesList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageSelenide {

    @FindBy(css = "[id='name']")
    private SelenideElement userField;

    @FindBy(css = ("[id='password']"))
    private SelenideElement passwordField;

    @FindBy(css = "[id='login-button']")
    private SelenideElement submitButton;

    @FindBy(css = "[id='user-icon']")
    private SelenideElement loginIcon;

    @FindBy (css = ".profile-photo [ui = 'label']")
    private SelenideElement loggedInUserName;

    @FindBy(css = ".nav > li")
    private ElementsCollection navBarElements;

    @FindBy(css = "[class = 'dropdown open'] li")
    private ElementsCollection serviceDropdownElements;

    @FindBy(css = "[class = 'sidebar-menu'] > [index = '3']")
    private SelenideElement leftSideMenuElements;

    @FindBy(css = "[class = 'sub'] li")
    private ElementsCollection serviceLeftSectionCategories;


    //Action methods
        public void login(Users user) {
            loginIcon.click();
            userField.sendKeys(user.login);
            passwordField.sendKeys(user.password);
            submitButton.click();
        }

        public void openElementsViaHeaderServiceMenu(ServiceCategories serviceCategories) {
            navBarElements.get(2).click();
            serviceDropdownElements.find(text(serviceCategories.name)).click();
        }

    // Check Elements
        public void getTitle (HomePageData homePageData) {
            assertEquals(getWebDriver().getTitle(),homePageData.title);
        }

        public void userIsLogged(Users user) {
            loggedInUserName.isDisplayed();
            assertEquals(loggedInUserName.getText(), user.name);
        }

        public void checkServiceHeaderContainsOptions() {
            navBarElements.get(2).click();
            serviceDropdownElements.shouldHaveSize(8);
            for (SelenideElement element : serviceDropdownElements) {
                 element.isDisplayed();
            }
            for (String category : getHeaderCategoriesList()) {
                assertTrue(serviceDropdownElements.texts().contains(category));

            }
        }

        public void checkServiceSubCategoryLeftSectionContainsOptions() {
            leftSideMenuElements.click();
            serviceLeftSectionCategories.shouldHaveSize(8);
            for (SelenideElement element : serviceLeftSectionCategories){
                 element.isDisplayed();
            }
            for (String category : getLeftSectionCategoriesList()){
                assertTrue(serviceLeftSectionCategories.texts().contains(category));
            }
        }

    }



