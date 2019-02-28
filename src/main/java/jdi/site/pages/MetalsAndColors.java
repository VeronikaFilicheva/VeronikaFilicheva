package jdi.site.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import jdi.data.MetalColor;
import jdi.enums.Vegetables;
import jdi.site.forms.MetalsAndColorsForm;
import org.openqa.selenium.WebElement;

import java.util.List;

import static jdi.site.forms.MetalsAndColorsForm.*;
import static org.testng.Assert.assertEquals;


public class MetalsAndColors extends WebPage {
    private MetalsAndColorsForm metalColorForm;

    @FindBy(css = ".results li")
    private List<WebElement> resultLog;

    public void fillForm(MetalColor data) {
        selectSummary(data.summary);
        selectElements(data.elements);
        selectColors(data.color);
        selectMetals(data.metals);
        selectVegetables(data.vegetables);
    }

    public void clickSubmitButton() {
        submit.click();
    }

    public void check(MetalColor data) {
        for (int i=0;i<data.result().length;i++) {
            assertEquals(resultLog.get(i).getText(),data.result()[i]);
        }
    }

    private void selectSummary(int[] summaryArr) {
        metalColorForm.summary.setOddsSummary(summaryArr[0]);
        metalColorForm.summary.setEvenSummary(summaryArr[1]);
    }


    private void selectVegetables(String... vegetable) {
        vegetables.select(Vegetables.VEGETABLES);
        for (String element : vegetable) {
            vegetables.select(element);
        }
    }

    private void selectColors(String color) {
        colors.select(color);
    }

    private void selectElements(String... checkboxes) {
        for (String element : checkboxes) {
            metalColorForm.checklist.check(element);
            metalColorForm.checklist.select(element);
        }
    }

    private void selectMetals(String metal) {
        metals.select(metal);
    }

}