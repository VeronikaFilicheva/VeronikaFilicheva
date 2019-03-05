package hw8.site.forms;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.ui.html.complex.RadioButtons;

import java.util.Objects;

import static jdi.enums.Radiobuttons.getValueOf;

public class Summary extends Form {
    @FindBy(css = "#odds-selector .radio")
    private RadioButtons oddsSummary;

    @FindBy(css = "#even-selector .radio")
    private RadioButtons evenSummary;

    public void setOddsSummary(int oddSummary) {
        this.oddsSummary.select(Objects.requireNonNull(getValueOf(oddSummary)));
    }

    public void setEvenSummary(int evenSummary) {
        this.evenSummary.select(Objects.requireNonNull(getValueOf(evenSummary)));
    }

}
