package jdi.site;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import enums.Pages;
import jdi.site.pages.HomePage;
import jdi.site.pages.MetalsAndColors;
import jdi.site.sections.Header;
import jdi.site.sections.ResultSection;

@JSite("https://epam.github.io/JDI/")
public class JDITestSite {
    @Url("index.html")
    public static HomePage homepage;
    public static Header header;
    public static MetalsAndColors metalsAndColors;
}

