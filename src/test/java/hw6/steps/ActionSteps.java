package hw6.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import enums.hw6.Dropdown;
import enums.hw6.Radiobuttons;
import enums.hw6.User;
import pageObjects.hw6.DifferentElementsPage;
import pageObjects.hw6.HomePageSelenide;
import pageObjects.hw6.UserTablePage;

import static enums.hw6.Checkboxes.getCheckboxByName;

public class ActionSteps {

    @When("^I login as user '([^\"]*)'$")
    public void iLoginAsUser(String userName) {
        new HomePageSelenide().login(User.getUserByUserName(userName));
    }

    @When("^I select checkboxes ([^\"]*) and ([^\"]*)$")
    public void selectCheckboxes(String checkbox1, String checkbox2) {
         new DifferentElementsPage().selectCheckboxes(getCheckboxByName(checkbox1),getCheckboxByName(checkbox2));
    }

    @When("^I select ratio (.+)$")
    public void selectRadio(String ratdoName){
        new DifferentElementsPage().selectRadio(Radiobuttons.getRadiobuttonByName(ratdoName));
    }

    @When("I select (.+) in dropdown")
    public void selectDropdown(String dropdownName) {
        new DifferentElementsPage().selectInDropdown(Dropdown.getDropdownByName(dropdownName));
    }

    @When("^I unselect checkboxes ([^\"]*) and ([^\"]*)$")
    public void unselectCheckboxes(String checkbox1, String checkbox2) {
        new DifferentElementsPage().selectCheckboxes(getCheckboxByName(checkbox1),getCheckboxByName(checkbox2));
    }

    @When("I click on \"(.+)\" button in Header")
    public void openSubMenu(String subMenu) {
        new HomePageSelenide().openSubMenu(subMenu.toUpperCase());
    }

    @And("I click on \"(.+)\" button in Service dropdown")
    public void openUserTablePage(String buttonName) {
        new HomePageSelenide().openServiceSubMenu(buttonName.toUpperCase());
    }

    @When("I select 'vip' checkbox for \"(.+)\"")
    public void selectCheckbox(String name) {
        new UserTablePage().selectVipCheckbox(name);
    }

    @When("^I click on dropdown in column Type for user Roman$")
    public void iClickOnDropdownInColumnTypeForUserRoman() {
        new UserTablePage().iClickOnDropdownForRoman();
    }

}
