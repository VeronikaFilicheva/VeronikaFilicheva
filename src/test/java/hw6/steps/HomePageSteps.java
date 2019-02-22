package hw6.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.HomePageData;
import enums.hw6.User;
import pageObjects.hw6.HomePageSelenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class HomePageSteps {

    public HomePageSelenide homePage = page(HomePageSelenide.class);

    @Given("^I am on \"Home Page\"$")
    @Then("^Browser title should be 'Home Page'$")
    public void homePageTitleShouldBe(){
        homePage.checkPageTitle(HomePageData.HOME_PAGE);
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
}
