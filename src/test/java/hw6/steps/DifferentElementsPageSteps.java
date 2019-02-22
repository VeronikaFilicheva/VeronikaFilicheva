package hw6.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.hw6.Dropdown;
import enums.hw6.Radiobuttons;
import pageObjects.hw6.DifferentElementsPage;

import static com.codeborne.selenide.Selenide.page;
import static enums.hw6.Checkboxes.getCheckboxByName;

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

    @When("^I select checkboxes ([^\"]*) and ([^\"]*)$")
    public void selectCheckboxes(String checkboxWind, String checkboxWater) {
        differentElementsPage.selectCheckboxes(getCheckboxByName(checkboxWind),getCheckboxByName(checkboxWater));
    }

    @Then("([^\"]*) and ([^\"]*) checkboxes should have individual log row and value is corresponded to the status of checkbox")
    public void checkCheckboxesLogs(String checkboxWind, String checkboxWater) {
        differentElementsPage.checkLogsForCheckboxes(getCheckboxByName(checkboxWind),getCheckboxByName(checkboxWater));
    }

    @When("^I select ratio (.+)$")
    public void selectRadio(String ratdoName){
        differentElementsPage.selectRadio(Radiobuttons.getRadiobuttonByName(ratdoName));
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

    @When("^I unselect checkboxes ([^\"]*) and ([^\"]*)$")
    public void unselectCheckboxes(String checkboxWind, String checkboxWater) {
        differentElementsPage.selectCheckboxes(getCheckboxByName(checkboxWind),getCheckboxByName(checkboxWater));
    }

    @Then("The log should have individual entry with value which is corresponded to the status of the (.+) and (.+) checkboxes")
    public void checkUnselectedCheckboxesLogs(String checkboxWind, String checkboxWater) {
        differentElementsPage.checkLogsForCheckboxes(getCheckboxByName(checkboxWind),getCheckboxByName(checkboxWater));
    }

}