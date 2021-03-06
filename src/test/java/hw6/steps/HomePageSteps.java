package hw6.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import enums.hw6.User;
import pageObjects.hw6.HomePageSelenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class HomePageSteps {

    private HomePageSelenide homePage = page(HomePageSelenide.class);

    @Given("^I am on \"Home Page\"$")
    public void openHomePage() {
        open("https://epam.github.io/JDI/index.html");
    }

    @Then("^Browser title should be \"(.+)\"$")
    public void homePageTitleShouldBe(String homePageTitle){
        homePage.checkPageTitle(homePageTitle);
    }

    @When("^I login as user '([^\"]*)'$")
    public void iLoginAsUser(String userName) {
        homePage.login(User.getUserByUserName(userName));
    }

    @Then("^User name should be as for user '([^\"]*)'$")
    public void userNameShouldBeAsForUser(String user) {
        homePage.getUserName().shouldBe(text(User.getUserByUserName(user).username));
    }

    @And("Home page interface contains all needed elements")
    public void checkHomePageElements(){
        homePage.checkHomePageElements();
    }

    @And("^Service dropdown contains options:$")
    public void checkServiceSubMenu(DataTable serviceSubMenu){
        homePage.checkServiceHeaderMenu(serviceSubMenu);
    }

    @And("^Service dropdown in left section contains options:$")
    public void checkServiceLeftSubMenu(DataTable serviceSubMenuLeft){
        homePage.checkServiceLeftSectionMenu(serviceSubMenuLeft);
    }

    @Given("I am on the (.*) page")
    public void iOnDifferentElementsPage(String subCategory){
        homePage.openSubMenu("SERVICE");
        homePage.openServiceSubMenu(subCategory);
    }

    @And("I login as user \"(.+)\"")
    public void login(String userName) {
        homePage.login(User.getUserByUserName(userName.toUpperCase()));
    }

    @When("I click on \"(.+)\" button in Header")
    public void openSubMenu(String subMenu) {
        homePage.openSubMenu(subMenu.toUpperCase());
    }

    @And("I click on \"(.+)\" button in Service dropdown")
    public void openUserTablePage(String buttonName) {
        homePage.openServiceSubMenu(buttonName.toUpperCase());
    }
}
