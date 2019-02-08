package enums;

public enum  NavBarElements {
    HEADER_ITEMS("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");

    public String home;
    public String contactForm;
    public String service;
    public String metalsAndColors;

    NavBarElements(String home, String contactForm, String service, String metalsAndColors) {
        this.home = home;
        this.contactForm = contactForm;
        this.service = service;
        this.metalsAndColors = metalsAndColors;
    }

}
