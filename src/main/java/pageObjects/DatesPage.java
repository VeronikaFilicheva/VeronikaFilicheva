package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Logs.*;

public class DatesPage {
    @FindBy(css = ".ui-slider")
    private SelenideElement slider;
    Actions actions = new Actions(getWebDriver());

    @FindBy(css = "a.ui-slider-handle:nth-child(1)")
    private SelenideElement sliderLeft;

    @FindBy(css = "a.ui-slider-handle:nth-child(3)")
    private SelenideElement sliderRight;

    @FindBy(css = "div#mCSB_2_container li")
    private ElementsCollection logs;

    public void checkLogs(String logFrom, String logTo) {
        logs.get(1).should(Condition.text(logFrom + " link clicked"));
        logs.get(0).should(Condition.text(logTo + " link clicked"));
    }

    public void moveSlder(int position1, int position2) {
        Actions builder = new Actions(getWebDriver());
        int to = (position1 - Integer.valueOf(sliderLeft.$("span").text())) * slider.getSize().width / 100 - 1;
        builder.clickAndHold(sliderLeft).moveByOffset(to, 0).release().build().perform();
        int from = (position2 - Integer.valueOf(sliderRight.$("span").text())) * slider.getSize().width / 100 - 1;
        builder.clickAndHold(sliderRight).moveByOffset(from, 0).release().build().perform();

    }
}
