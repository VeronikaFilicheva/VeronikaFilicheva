package pageObjects.hw6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.hw6.Checkboxes;
import enums.hw6.Dropdown;
import enums.hw6.Radiobuttons;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static enums.Checkboxes.*;
import static enums.Logs.*;
import static enums.Radiobuttons.*;

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

    public DifferentElementsPage() { page(this); }

    public void checkDifferentElementsPage() {
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

    public void selectCheckboxes(List<String> checkboxesList) {
        for ( String checkbox : checkboxesList) {
            differentElementsPageCheckboxes.findBy(text(checkbox)).click();
        }
    }

    public void checkLogsForCheckboxes(List<String> checkboxesList) {
        for (String checkbox : checkboxesList) {
            for (SelenideElement element : differentElementsPageCheckboxes) {
                if (element.parent().text().equals(checkbox))
                    for (SelenideElement selenideElement : logs) {
                        selenideElement.shouldHave(Condition.text(checkbox + ": condition changed to " + element.isSelected()));
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

    public void checkdropdownLogs(Dropdown... dropdowns) {
        for (Dropdown dropdown : dropdowns) {
            logsPanel.shouldBe(visible).shouldHave(text(String.valueOf(COLORS)+VALUE_CHANGED_TO+dropdown.color));
        }
    }
}
