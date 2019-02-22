package hw6.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.hw6.Dropdown;
import enums.hw6.Radiobuttons;
import pageObjects.hw6.DifferentElementsPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;


public class DifferentElementsPageSteps {

    private DifferentElementsPage differentElementsPage = page(DifferentElementsPage.class);

    @Then("^Different elements page interface contains all needed elements$")
    public void checkDifferentElementsPage(){
        differentElementsPage.checkDifferentElementsPage();
    }

    @And("There is Right Section")
    public void checkRight() {
        differentElementsPage.checkRightSection();
    }

    @And("There is Left Section")
    public void  checkLeft() {
        differentElementsPage.checkLeftSection();
    }

    @When("^I select checkboxes (.+)$")
    public void selectCheckboxes(List<String> checkboxes) {
        differentElementsPage.selectCheckboxes(checkboxes);
    }

    @Then("(.+) checkboxes should have individual log row and value is corresponded to the status of checkbox")
    public void checkCheckboxesLogs(List<String> checkboxes) {
        differentElementsPage.checkLogsForCheckboxes(checkboxes);
    }

    @When("^I select ratio (.+)$")
    public void selectRadio(String radioName){
        differentElementsPage.selectRadio(Radiobuttons.getRadiobuttonByName(radioName));
    }

    @Then("The log should have individual entry with value which is corresponded to the status of the radiobutton (.+)")
    public void checkRadiobuttonLog(String radiobutton) {
        differentElementsPage.checkLogsForRadiobuttons(Radiobuttons.getRadiobuttonByName(radiobutton));
    }

    @When("I select (.+) in dropdown")
    public void selectDropdown(String dropdownName) {
        differentElementsPage.selectInDropdown(Dropdown.getDropdownByName(dropdownName));
    }

    @Then("The log should have individual entry with value about selected (.+) color")
    public void checkDropdownLog(String dropdownName) {
        differentElementsPage.checkdropdownLogs(Dropdown.getDropdownByName(dropdownName));
    }

    @When("^I unselect checkboxes (.+)$")
    public void unselectCheckboxes(List<String> checkboxes) {
        differentElementsPage.selectCheckboxes(checkboxes);
    }

    @Then("The log should have individual entry with value which is corresponded to the status of the (.+) checkboxes")
    public void checkUnselectedCheckboxesLogs(List<String> checkboxes) {
        differentElementsPage.checkLogsForCheckboxes(checkboxes);
    }

}