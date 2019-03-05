package hw8.site.forms;

import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.ByText;
import com.epam.jdi.light.ui.html.base.HtmlChecklist;
import com.epam.jdi.light.ui.html.base.HtmlElement;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.Checklist;
import hw8.data.MetalColor;
import hw8.enums.Vegetables;
import org.openqa.selenium.By;

// TODO Code convention !!
// fixed
public class MetalsAndColorsForm extends Form{

    public Summary summary;

    @FindBy(css = "p input[type='checkbox']")
    public Checklist checklist = new HtmlChecklist() {
        @Override
        public void select(String... names) {
            for (String name : names) {
                HtmlElement value = get(name);
                value.setLocator(By.cssSelector(correctLocator(value.locator.toString())));
                if (value.isEnabled())
                    value.click();
            }
        }

        private String correctLocator(String wrongLocator) {
            return wrongLocator.substring(5, wrongLocator.length() - 1) + " + *";
        }
    };

    @JDropdown(root = "div[ui=dropdown]", value = ".filter-option",
            list = "li", expand = ".caret")
    public static Droplist colors;

    @JDropdown(root = "div[ui=combobox]", value = "input",
            list = "li", expand = ".caret")
    public static Droplist metals;


    @JDropdown(root = "#salad-dropdown", value = ".dropdown-toggle", list = "li", expand = ".caret")
    public static Droplist vegetables;

    @ByText("Submit")
    public static Button submit;

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

    private void selectSummary(int[] summaryArr) {
        summary.setOddsSummary(summaryArr[0]);
        summary.setEvenSummary(summaryArr[1]);
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
            checklist.check(element);
            checklist.select(element);
        }
    }

    private void selectMetals(String metal) {
        metals.select(metal);
    }

}
