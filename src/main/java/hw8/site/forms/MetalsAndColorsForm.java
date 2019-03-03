package hw8.site.forms;

import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.ByText;
import com.epam.jdi.light.ui.html.base.HtmlChecklist;
import com.epam.jdi.light.ui.html.base.HtmlElement;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.Checklist;
import org.openqa.selenium.By;


public class MetalsAndColorsForm {

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

    private String correctLocator(String wrongLocator){
        return wrongLocator.substring(5,wrongLocator.length() - 1)+ " + *";
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

    @ByText("Submit") public static Button submit;


}
