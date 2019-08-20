package com.epam.jdi.bdd.stepdefs;

import com.epam.jdi.light.ui.html.common.FileInput;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.light.driver.get.DriverData.PROJECT_PATH;
import static com.epam.jdi.light.elements.composite.WebPage.ELEMENTS;
import static com.epam.jdi.tools.PathUtils.mergePath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

public class FileInputSteps {
    @When("^I upload file \"([^\"]*)\" by \"([^\"]*)\" file input element$")
    public void iUploadFileByFileInputElement(String pathToFile, String elementName) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        fileInput.uploadFile(mergePath(PROJECT_PATH, pathToFile));
    }

    @When("^I try to upload file \"([^\"]*)\" by \"([^\"]*)\" file input element$")
    public void iTryToUploadFileByFileInputElement(String pathToFile, String elementName) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        try {
            fileInput.uploadFile(mergePath(PROJECT_PATH, pathToFile));
        } catch (Exception e) {
            assertEquals(
                    String.format("\r\nCan't do uploadFile for disabled element '%s'", elementName),e.getMessage());
        }
    }

    @Then("^Assert that \"([^\"]*)\" file input element label equals to \"([^\"]*)\"$")
    public void assertThatFileInputElementLabelEqualsTo(String elementName, String labelText) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        fileInput.label().is().text(labelText);
    }

    @Then("^Assert that \"([^\"]*)\" file input element label contains \"([^\"]*)\"$")
    public void assertThatFileInputElementLabelContains(String elementName, String labelText) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        fileInput.label().is().text(containsString(labelText));
    }

    @Then("^Assert that \"([^\"]*)\" file input element text equals to \"([^\"]*)\"$")
    public void assertThatFileInputElementTextEqualsTo(String elementName, String text) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        fileInput.is().text(is(text));
    }

    @Then("^Assert that \"([^\"]*)\" file input element text contains \"([^\"]*)\"$")
    public void assertThatFileInputElementTextContains(String elementName, String text) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        fileInput.is().text(containsString(text));
    }

    @And("^Assert that \"([^\"]*)\" file input element value equals to \"([^\"]*)\"$")
    public void assertThatFileInputElementValueEqualsTo(String elementName, String value) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        assertEquals(fileInput.value(), value);
    }

    @And("^Assert that \"([^\"]*)\" file input element value contains \"([^\"]*)\"$")
    public void assertThatFileInputElementValueContains(String elementName, String value) {
        FileInput fileInput = (FileInput) ELEMENTS.get(elementName).get(0);
        MatcherAssert.assertThat(fileInput.value(), CoreMatchers.containsString(value));
    }
}
