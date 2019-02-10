package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.Checkboxes;
import enums.Dropdown;
import enums.Radiobuttons;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static enums.Checkboxes.*;
import static enums.Checkboxes.FIRE;
import static enums.Logs.*;
import static enums.Radiobuttons.*;
import static enums.Radiobuttons.SELEN;

public class DifferentElementsPage {
    @FindBy(css = "[class='label-checkbox']")
    private ElementsCollection differentElementsPageCheckboxes;

    @FindBy(css = "[class='label-radio']")
    private ElementsCollection differentElementsPageRadioButtons;

    @FindBy(css = "[class = 'colors']")
    private SelenideElement colorsDropdown;

    @FindBy(css = "select[class='uui-form-element'] option")
    private ElementsCollection dropdownPositions;

    @FindBy(css = "[class = 'uui-button']")
    private ElementsCollection buttons;

    @FindBy(css = "[class = 'right-fix-panel']")
    private SelenideElement rightSection;

    @FindBy(css = "[class = 'mCSB_vertical']")
    private SelenideElement leftSection;

    @FindBy(css = "[class = 'panel-body-list logs']")
    private SelenideElement logsPanel;

    @FindBy(css = "[class = '.logs']")
    private ElementsCollection logs;


    public void checkIfNeededElementsVisibleDifferentElementsPage() {
        differentElementsPageCheckboxes.shouldHaveSize(4).shouldHave(texts(String.valueOf(WATER), String.valueOf(EARTH), String.valueOf(WIND), String.valueOf(FIRE)));
        differentElementsPageRadioButtons.shouldHaveSize(4).shouldHave(texts(String.valueOf(GOLD), String.valueOf(SILVER), String.valueOf(BRONZE), String.valueOf(SELEN)));
        colorsDropdown.isDisplayed();
        buttons.shouldHaveSize(2);
        buttons.get(0).shouldHave(text("DEFAULT BUTTON"));
        buttons.get(1).shouldHave(value("BUTTON"));
    }

    public void checkRightSection() {
        rightSection.isDisplayed();
    }

    public void checkLeftSection() {
        leftSection.isDisplayed();
    }

    public void selectCheckboxes(Checkboxes... checkboxes) {
        for (Checkboxes checkbox : checkboxes) {
            differentElementsPageCheckboxes.findBy(text(checkbox.checkboxesName)).click();
        }
    }

    public void checkLogsforCheckboxes(Checkboxes... checkboxes) {
        for (Checkboxes checkbox : checkboxes) {
            for (SelenideElement element : differentElementsPageCheckboxes) {
                if (element.parent().text().equals(checkbox.checkboxesName))
                    for (SelenideElement selenideElement : logs) {
                        selenideElement.shouldHave(Condition.text(checkbox.checkboxesName + ": condition changed to " + element.isSelected()));
                    }
            }
        }
    }


    public void selectRadio(Radiobuttons... radiobuttons) {
        for(Radiobuttons radiobutton : radiobuttons){
            differentElementsPageRadioButtons.findBy(text(radiobutton.name)).click();
        }
    }

    public void checkLogsForRadiobuttons(Radiobuttons... radiobuttons) {
        for (Radiobuttons radiobutton : radiobuttons) {
            logsPanel.shouldBe(visible).shouldHave(text(String.valueOf(METAL) + VALUE_CHANGED_TO + radiobutton.name));
        }
    }

    public void selectInDropdown(Dropdown dropdown) {
        colorsDropdown.click();
        dropdownPositions.find(text(dropdown.color)).click();
    }

    public void dropdownLogs(Dropdown... dropdowns) {
        for (Dropdown dropdown : dropdowns) {
            logsPanel.shouldBe(visible).shouldHave(text(String.valueOf(COLORS)+VALUE_CHANGED_TO+dropdown.color));
        }
    }
}
