package jdi.site.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import jdi.data.MetalColor;
import jdi.site.forms.MetalsAndColorsForm;
import jdi.site.sections.Result;

public class MetalsAndColors extends WebPage {
    private MetalsAndColorsForm metalColorForm;
    private MetalColor data = new MetalColor();
    private Result result;

    // TODO All internal actions with form should be encapsulated in form class
    // fixed
    public void fillInFormsWithData(MetalColor data) {
        metalColorForm.fillForm(data);
    }

    public void clickSubmit() {
        metalColorForm.clickSubmitButton();
    }

    public void checkResult(MetalColor data) {
        result.check(data);
    }
}
