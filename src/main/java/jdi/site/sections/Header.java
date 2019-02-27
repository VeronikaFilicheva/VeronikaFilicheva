package jdi.site.sections;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Button;


public class Header extends Section {

    @Css(".profile-photo")
    public Button profilePhoto;

}