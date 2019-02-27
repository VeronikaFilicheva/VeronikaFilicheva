package jdi.site;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import jdi.site.pages.HomePage;
import jdi.site.pages.MetalsAndColors;

@JSite("https://epam.github.io/JDI/")
public class JDITestSite {
    @Url("index.html")
    public static HomePage homepage;
    public static MetalsAndColors metalsAndColors;
}

