package jdi.site.sections;

import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import jdi.data.MetalColor;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResultSection {

    @FindBy(css = ".results li")
    private List<WebElement> res;




}
