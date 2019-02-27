package jdi.site.sections;

import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.UI;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.Menu;
import enums.Pages;
import jdi.enums.MenuEnum;
import jdi.site.custom.MenuItem;
import org.openqa.selenium.support.FindBy;

public class Header extends Section {

    @Css(".profile-photo")
    public Button profilePhoto;

}