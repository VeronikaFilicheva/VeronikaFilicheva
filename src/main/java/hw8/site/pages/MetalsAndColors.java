package hw8.site.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import hw8.data.MetalColor;
import hw8.site.forms.MetalsAndColorsForm;
import hw8.site.sections.Result;

// TODO Java code convention !
// fixed
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

