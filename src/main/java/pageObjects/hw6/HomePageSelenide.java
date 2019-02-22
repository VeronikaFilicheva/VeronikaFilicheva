package pageObjects.hw6;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.DataTable;
import enums.*;
import enums.hw6.User;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
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

    @FindBy(css =".benefit-icon")
    private ElementsCollection benefitImages;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection benefitTexts;

    @FindBy(css = "h3.main-title.text-center")
    private SelenideElement textOnMainTitle;

    @FindBy(css = "p.main-txt")
    private SelenideElement titleText;

    @FindBy(css = "[class = 'dropdown open'] li")
    private ElementsCollection serviceDropdownElements;

    @FindBy(css = "[class = 'sidebar-menu']")
    private ElementsCollection leftSideMenuElements;

    @FindBy(css = "[class = 'sub'] li")
    private ElementsCollection serviceLeftSection;

    public HomePageSelenide() {
        page(this);
    }

    // methods
         public void openPage(HomePageData homePageData){
             open(homePageData.navigateTo);
         }

         public void checkPageTitle (String homePageTitle) {
            assertEquals(getWebDriver().getTitle(),homePageTitle);
         }

         public SelenideElement getUserName() {
            return loggedInUserName;
         }

         public void login(User user) {
            loginIcon.click();
            userField.sendKeys(user.login);
            passwordField.sendKeys(user.password);
            submitButton.click();
         }

         public void checkHomePageElements(){
            benefitImages.shouldHaveSize(4);
            benefitImages.forEach(SelenideElement::isDisplayed);

            benefitTexts.shouldHaveSize(4);
            benefitTexts.forEach(SelenideElement::isDisplayed);

            textOnMainTitle.shouldBe(visible);
            titleText.shouldBe(visible);
         }


         private List<String> makeDataTableList(DataTable dataTable, boolean upperCase) {
            List<String> list = dataTable.asList(String.class);
            if (upperCase){
                        return list.stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList());
            }
            else return  list;
         }

         public void checkServiceHeaderMenu(DataTable serviceDropdown) {
            List<String> optionsUpperCase = makeDataTableList(serviceDropdown, true);

            navBarElements.find(text("SERVICE")).click();

            serviceDropdownElements.shouldHaveSize(optionsUpperCase.size());
            serviceDropdownElements.forEach(SelenideElement::isDisplayed);

            for (String category : optionsUpperCase) {
                assertTrue(serviceDropdownElements.texts().contains(category));
            }
         }

         public void checkServiceLeftSectionMenu(DataTable leftSection) {
            List<String> leftSectionList = makeDataTableList(leftSection, false);

            leftSideMenuElements.find(text("Service")).click();

            serviceLeftSection.shouldHaveSize(leftSectionList.size());
            serviceLeftSection.forEach(SelenideElement::isDisplayed);
            serviceLeftSection.shouldHave(textsInAnyOrder(leftSectionList));
         }

         public void openServiceSubMenu(String menu) {
            serviceDropdownElements.find(text(menu.toUpperCase())).click();
         }

         public void openSubMenu(String subMenu) {
             navBarElements.find(text(subMenu)).click();
         }

    }



