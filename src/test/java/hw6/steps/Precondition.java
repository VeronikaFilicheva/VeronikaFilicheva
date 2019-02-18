package hw6.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import enums.hw6.User;
import pageObjects.hw6.HomePageSelenide;

public class Precondition {
    @Given("I am on the (.*) page")
    public void iOnDifferentElementsPage(String subCategory){
        new HomePageSelenide().openSubMenu("SERVICE");
        new HomePageSelenide().openServiceSubMenu(subCategory);
    }

    @And("I login as user \"(.+)\"")
    public void login(String userName) {
        new HomePageSelenide().login(User.getUserByUserName(userName.toUpperCase()));
    }
}
