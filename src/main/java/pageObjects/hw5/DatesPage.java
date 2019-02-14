package pageObjects.hw5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


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

   @Step
   public void checkLogs(String logFrom, String logTo) {
       for (SelenideElement element : logs) {
           if (element.text().indexOf(logFrom)!=-1) {
               element.shouldHave(Condition.text((logFrom + " link clicked"))); }
       }
       for (SelenideElement selenideElement : logs) {
           if (selenideElement.text().indexOf(logTo)!=-1) {
               selenideElement.shouldHave(Condition.text(logTo + " link clicked")); }
       }
    }

   @Step
   public void  setSlidersPosition(int position1, int position2) {
        Actions builder = new Actions(getWebDriver());
        int to = (position1 - Integer.valueOf(sliderLeft.$("span").text())) * slider.getSize().width / 100 - 1;
        builder.clickAndHold(sliderLeft).moveByOffset(to, 0).release().build().perform();
        int from = (position2 - Integer.valueOf(sliderRight.$("span").text())) * slider.getSize().width / 100 - 1;
        builder.clickAndHold(sliderRight).moveByOffset(from, 0).release().build().perform();

    }
}
