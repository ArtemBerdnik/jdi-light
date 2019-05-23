package com.epam.jdi.light.ui.html.base;

import com.epam.jdi.light.asserts.IHasAssert;
import com.epam.jdi.light.asserts.SelectAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.complex.Selector;
import com.epam.jdi.light.ui.html.complex.Checklist;
import com.epam.jdi.tools.EnumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.jdi.light.driver.WebDriverByUtils.fillByTemplate;
import static com.epam.jdi.light.ui.html.HtmlFactory.$;
import static com.epam.jdi.tools.EnumUtils.getEnumValues;
import static com.epam.jdi.tools.LinqUtils.*;
import static com.epam.jdi.tools.PrintUtils.print;
import static java.util.Arrays.asList;
import static org.openqa.selenium.By.cssSelector;

public class HtmlChecklist extends Selector<HtmlElement> implements Checklist, IHasAssert<SelectAssert> {
    By checkbox = cssSelector("input[type=checkbox][id='%s']");
    By label = LABEL_LOCATOR;
    private String getId(String name) { return label(name).getAttribute("for"); }

    /**
     * Gets a value
     * @param value String
     * @return HtmlElement
     */
    public HtmlElement get(String value) {
        return $(fillByTemplate(checkbox, getId(value)), parent).setName(value);
    }
    private HtmlElement label(String value) {
        return $(fillByTemplate(label, value), parent).setName("label");
    }

    public HtmlChecklist() { }
    public HtmlChecklist(WebElement el) { super(el); }
    List<HtmlElement> labels() {
        return map(getAll(), el -> new HtmlElement(el).label().setName(getName()+ " label"));
    }
    List<HtmlElement> checkboxes() { return map(getAll(), HtmlElement::new); }

    /**
     * Returns the number of elements in this checklist
     * @return int
     */
    @Override
    public int size() {
        return checkboxes().size();
    }

    /**
     * Select values from parameters
     * @param names String var arg, elements with text to select
     */
    @JDIAction("Select '{0}' for '{name}'")
    @Override
    public void select(String... names) {
        for (String name : names) {
            HtmlElement value = get(name);
            if (value.isEnabled())
                value.click();
        }
    }
    public <TEnum extends Enum> void select(TEnum... value) {
        select(toStringArray(map(value, EnumUtils::getEnumValue)));
    }

    /**
     * Selects only particular elements
     * @param indexes String var arg, elements with text to select
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(int... indexes) {
        for (int i = 1; i <= indexes.length; i++) {
            HtmlElement value = checkboxes().get(indexes[i]);
            if (value.isEnabled())
                value.click();
        }
    }

    /**
     * Selects only particular elements
     * @param names String var arg, elements with text to select
     */
    @JDIAction("Check '{0}' for '{name}'")
    public void check(String... names) {
        List<String> listNames = asList(names);
        for (String name : values()) {
            HtmlElement value = get(name);
            if (value.isDisabled()) continue;
            if (isSelected(value) && !listNames.contains(value.labelText().trim())
                    || !isSelected(value) && listNames.contains(value.labelText().trim()))
                value.click();
        }
    }

    protected boolean isSelected(HtmlElement value) {
        return value.isSelected();
    }
    /**
     * Unselects only particular elements
     * @param names String var arg, elements with text to unselect
     */
    @JDIAction("Uncheck '{0}' for '{name}'")
    public void uncheck(String... names) {
        List<String> listNames = asList(names);
        for (String name : values()) {
            HtmlElement value = get(name);
            if (value.isDisabled()) continue;
            if (isSelected(value) && listNames.contains(value.labelText().trim())
                    || !isSelected(value) && !listNames.contains(value.labelText().trim()))
                value.click();
        }
    }
    public <TEnum extends Enum> void check(TEnum... values) {
        check(getEnumValues(values));
    }

    public <TEnum extends Enum> void uncheck(TEnum... values) {
        uncheck(getEnumValues(values));
    }

    /**
     * Checks particular elements by index
     * @param indexes int var arg, ids to check
     */
    @JDIAction("Check '{0}' for '{name}'")
    public void check(int... indexes) {
        List<Integer> listIndexes = toList(indexes);
        for (int i = 1; i <= values().size(); i++) {
            HtmlElement value = checkboxes().get(i-1);
            if (value.isDisabled()) continue;
            if (isSelected(value) && !listIndexes.contains(i)
                    || !isSelected(value) && listIndexes.contains(i))
                value.click();
        }
    }

    /**
     * Checks all elements
     */
    @JDIAction("Check '{name}' unchecked options")
    public void checkAll() {
        for (HtmlElement checkbox : checkboxes()) {
            if (checkbox.isEnabled() && !isSelected(checkbox)) {
                checkbox.click();
            }
        }
    }
    /**
     * Unchecks particular elements by index
     * @param indexes int var arg, ids to uncheck
     */
    @JDIAction("Uncheck '{0}' for '{name}'")
    public void uncheck(int... indexes) {
        List<Integer> listIndexes = toList(indexes);
        for (int i = 1; i <= values().size(); i++) {
            HtmlElement value = checkboxes().get(i-1);
            if (value.isDisabled()) continue;
            if (isSelected(value) && listIndexes.contains(i)
                    || !isSelected(value) && !listIndexes.contains(i))
                value.click();
        }
    }

    /**
     * Unchecks all elements
     */
    @JDIAction("Uncheck '{name}' checked options")
    public void uncheckAll() {
        for (HtmlElement checkbox : checkboxes()) {
            if (checkbox.isEnabled() && isSelected(checkbox)) {
                checkbox.click();
            }
        }
    }

    /**
     * Gets checked values in checklist
     * @return List<String>
     */
    @JDIAction("Get '{name}' checked options")
    public List<String> checked() {
        return ifSelect(checkboxes(), HtmlElement::isSelected, HtmlElement::labelText);
    }

    /**
     * Selects a value in checklist
     * @param value String var arg
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(String value) {
        select(new String[]{value});
    }

    /**
     * Selects a value with index in checklist
     * @param index int var arg
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(int index) {
        select(new int[]{index});
    }

    /**
     * Gets a list of text from each values from checklist
     * @return List<String>
     */
    @JDIAction("Get '{name}' values")
    public List<String> values() {
        return map(labels(), element -> element.getText().trim());
    }

    /**
     * Gets a list of innerText from each values from checklist
     * @return List<String>
     */
    @JDIAction("Get '{name}' values")
    public List<String> innerValues() {
        return map(labels(), element -> element.innerText().trim());
    }

    /**
     * Gets enabled values from checklist
     * @return List<String>
     */
    @JDIAction("Get '{name}' enabled options")
    public List<String> listEnabled() {
        return ifSelect(checkboxes(),
                HtmlElement::isEnabled,
                HtmlElement::labelText);
    }

    /**
     * Gets disabled values from checklist
     * @return List<String>
     */
    @JDIAction("Get '{name}' disabled options")
    public List<String> listDisabled() {
        return ifSelect(checkboxes(),
                HtmlElement::isDisabled,
                HtmlElement::labelText);
    }

    /**
     * Sets values in checklist
     * @param value String with values separated ";"
     */
    @Override
    public void setValue(String value) {
        check(value.split(";"));
    }

    /**
     * Gets selected values separated ";"
     * @return String
     */
    @Override
    @JDIAction("Get '{name}' selected option")
    public String selected() {
        return print(ifSelect(checkboxes(), HtmlElement::isSelected, HtmlElement::labelText));
    }

    /**
     * Checks if a value is selected in a checklist
     * @param value String to select
     * @return boolean
     */
    @JDIAction("Is '{0}' selected in '{name}'")
    public boolean selected(String value) {
        return get(value).isSelected();
    }

    /**
     * Gets selected values separated ";"
     * @return String
     */
    @Override
    public String getValue() {
        return selected();
    }

    public SelectAssert assertThat() {
        return is();
    }
    public SelectAssert has() {
        return is();
    }
    public SelectAssert waitFor() {
        return is();
    }
    public SelectAssert shouldBe() {
        return is();
    }
}
