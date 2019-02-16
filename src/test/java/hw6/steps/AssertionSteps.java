package hw6.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import enums.HomePageData;
import enums.hw6.Dropdown;
import enums.hw6.Radiobuttons;
import enums.hw6.User;
import pageObjects.hw6.DifferentElementsPage;
import pageObjects.hw6.HomePageSelenide;

import static com.codeborne.selenide.Condition.text;
import static enums.hw6.Checkboxes.getCheckboxByName;

public class AssertionSteps {

    @Then("^Browser title should be 'Home Page'$")
    public void homePageTitleShouldBe(){
        new HomePageSelenide().checkPageTitle(HomePageData.HOME_PAGE);
    }

    @Then("^User name should be as for user '([^\"]*)'$")
    public void userNameShouldBeAsForUser(String user) {
        new HomePageSelenide().getUserName().shouldBe(text(User.getUserByUserName(user).username));
    }

    @And("Home page interface contains all needed elements")
    public void checkHomePageElements(){
        new HomePageSelenide().checkHomePageElements();
    }

    @And("^Service dropdown contains options:$")
    public void checkServiceSubMenu(DataTable serviceSubMenu){
        new HomePageSelenide().checkServiceHeaderMenu(serviceSubMenu);
    }

    @And("^Service dropdown in left section contains options:$")
    public void checkServiceLeftSubMenu(DataTable serviceSubMenuLeft){
        new HomePageSelenide().checkServiceLeftSectionMenu(serviceSubMenuLeft);
    }

    @Then("^Different elements page interface contains all needed elements$")
    public void checkDifferentElementsPage(){
        new DifferentElementsPage().checkDifferentElementsPage();
    }

    @And("There is Right Section")
    public void checkRight() {
        new DifferentElementsPage().checkRightSection();
    }

    @And("There is Left Section")
    public void  checkLeft() {
        new DifferentElementsPage().checkLeftSection();
    }

    @Then("([^\"]*) and ([^\"]*) checkboxes should have individual log row and value is corresponded to the status of checkbox")
    public void checkChexboxesLogs(String checkbox1, String checkbox2) {
        new DifferentElementsPage().checkLogsForCheckboxes(getCheckboxByName(checkbox1),getCheckboxByName(checkbox2));
    }

    @Then("The log should have individual entry with value which is corresponded to the status of the radiobutton (.+)")
    public void checkRadiobuttonLog(String radiobutton) {
        new DifferentElementsPage().checkLogsForRadiobuttons(Radiobuttons.getRadiobuttonByName(radiobutton));
    }

    @Then("The log should have individual entry with value about selected (.+) color")
    public void checkDropdownLog(String dropdownName) {
        new DifferentElementsPage().checkdropdownLogs(Dropdown.getDropdownByName(dropdownName));
    }

    @Then("The log should have individual entry with value which is corresponded to the status of the (.+) and (.+) checkboxes")
    public void checkUnselectedChexboxesLogs(String checkbox1, String checkbox2) {
        new DifferentElementsPage().checkLogsForCheckboxes(getCheckboxByName(checkbox1),getCheckboxByName(checkbox2));
    }


}
