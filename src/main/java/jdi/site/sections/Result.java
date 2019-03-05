package jdi.site.sections;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import jdi.data.MetalColor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Result extends Section {
    private MetalColor data = new MetalColor();

    @FindBy(css = ".results li")
    private List<WebElement> resultLog;

    public void check(MetalColor data) {
        for (int i = 0; i < data.result().length; i++) {
            assertEquals(resultLog.get(i).getText(), data.result()[i]);
        }
    }

}
