package pageObjects.hw6;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.DataTable;
import gherkin.lexer.Da;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.hw6.UserNames.getUserIdByName;
import static org.testng.Assert.assertEquals;

public class UserTablePage {
    @FindBy(css = "#user-table tr > td > select")
    private ElementsCollection dropdowns;

    @FindBy(css = "#user-table tr > td > a")
    private ElementsCollection userNames;

    @FindBy(css = "#user-table tr > td > img")
    private ElementsCollection images;

    @FindBy(css = "#user-table tr > td > div > span")
    private ElementsCollection texts;

    @FindBy(css = "#user-table tr > td > div > input")
    private ElementsCollection checkboxes;

    @FindBy(css = "[type ='checkbox']")
    private ElementsCollection VIPcheckboxes;

    @FindBy(css = "[class='panel-body-list logs'] > li")
    private ElementsCollection logs;

    @FindBy(css = "tbody > tr:nth-child(2) option")
    private ElementsCollection optionsInDropdown;

    public UserTablePage() {
        page(this);
    }


    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(),"User Table");
    }

    public void checkDropdowns(int amountOfDropdowns) {
        dropdowns.shouldHaveSize(amountOfDropdowns);
    }

    public void checkNames(int amountOfNames) {
        userNames.shouldHaveSize(amountOfNames);
    }

    public void checkImages(int amountOfImages) {
        images.shouldHaveSize(amountOfImages);
    }

    public void checkTexts(int amountOfTexts) {
        texts.shouldHaveSize(amountOfTexts);
    }

    public void checkCheckBoxes(int amountCheckboxes) {
        checkboxes.shouldHaveSize(amountCheckboxes);

    }

    public void checkUserTableValues(DataTable table) {
        List<Map<String, String>> list = table.asMaps(String.class, String.class);

        for (Map<String, String> options : list) {
            userNames.findBy(text(options.get("User"))).shouldHave(text(options.get("User")));
            texts.findBy(text(options.get("Description"))).shouldHave(text(options.get("Description")));
        }
    }

    public void selectVipCheckbox(String name) {
        VIPcheckboxes.findBy(id(getUserIdByName(name))).click();
    }

    public void logRowHasTextInLogSection(int numberOfRows, String text) {
        logs.shouldHaveSize(numberOfRows);
        logs.shouldHave(texts(text));
    }

    public void iClickOnDropdownForRoman() {
        dropdowns.first().click();
    }

    public void checkDroplist(DataTable droplist) {
        List<String> list = droplist.asList(String.class);

        for (int i = 1; i < list.size(); i++) {
            optionsInDropdown.get(i).shouldHave(text(list.get(i)));
        }
    }
}
