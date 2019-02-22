package hw6.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.hw6.UserTablePage;

import static com.codeborne.selenide.Selenide.page;

public class UserTablePageSteps {

    private UserTablePage userTablePage = page(UserTablePage.class);

    @Then("\"User Table\" page is opened")
    public void userTablePageTitleShouldBe() {
        userTablePage.checkTitle();
    }

    @And("(.+) NumberType Dropdowns are displayed on Users Table on User Table Page")
    public void checkDropdowns(int dropdownsCount) {
        userTablePage.checkDropdowns(dropdownsCount);
    }

    @And("(.+) User names are displayed on Users Table on User Table Page")
    public void checkUserNames(int namesCount) {
        userTablePage.checkNames(namesCount);
    }

    @And("(.+) Description images are displayed on Users Table on User Table Page")
    public void checkImages(int imagesCount) {
        userTablePage.checkImages(imagesCount);
    }

    @And("(.+) Description texts under images are displayed on Users Table on User Table Page")
    public void checkTexts(int textsCount) {
        userTablePage.checkTexts(textsCount);
    }

    @And("(.+) checkboxes are displayed on Users Table on User Table Page")
    public void checkCheckboxes(int checkboxesCount) {
        userTablePage.checkCheckBoxes(checkboxesCount);
    }

    @And("User table contains following values:")
    public void checkUserTable(DataTable userTable) {
        userTablePage.checkUserTableValues(userTable);
    }

    @When("I select 'vip' checkbox for \"(.+)\"")
    public void selectCheckbox(String userName) {
        userTablePage.selectVipCheckbox(userName);
    }

    @Then("(.+) log row has \"(.+)\" text in log section")
    public void checkLogForVip(int logIndex , String logRow) {
        userTablePage.logRowHasTextInLogSection(logIndex ,logRow);
    }

    @When("^I click on dropdown in column Type for user Roman$")
    public void iClickOnDropdownInColumnTypeForUserRoman() {
        userTablePage.iClickOnDropdownForRoman();
    }

    @Then("^droplist contains values$")
    public void droplistContainsValues(DataTable dataTable) {
        userTablePage.checkDroplist(dataTable);
    }

}
