package com.epam.jdi.bdd.stepdefs;

import com.epam.jdi.light.ui.html.elements.complex.Combobox;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.light.elements.init.entities.collection.EntitiesCollection.getUI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ComboboxSteps {
    public static Combobox combobox(String name) { return getUI(name, Combobox.class); }

    @When("^select index (\\d+) in \"([^\"]*)\"$")
    public void comboboxSelect(int index, String name) {
        combobox(name).select(index);
    }
    @Then("^the \"([^\"]*)\" combobox selected value is \"([^\"]*)\"$")
    public void comboboxSelectedValue(String name, String value) {
        assertThat(combobox(name).selected(), is(value));
    }
}