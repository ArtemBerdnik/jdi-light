package com.epam.jdi.light.ui.html.elements.common;

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.base.UIBaseElement;
import com.epam.jdi.light.elements.interfaces.base.HasLabel;
import com.epam.jdi.light.elements.interfaces.base.SetValue;
import com.epam.jdi.light.ui.html.asserts.ColorAssert;

public class ColorPicker extends UIBaseElement<ColorAssert> implements HasLabel, SetValue {
    // region Actions
    @JDIAction("Get '{name}' color")
    public String color() { return uiElement.attr("value"); }
    @JDIAction("Set color '{0}' for '{name}'")
    public void setColor(String color) {
        uiElement.setAttribute("value", color);
    }
    // endregion

    // region Set and get value for Forms
    public void setValue(String value) {
        setColor(value);
    }
    public String getValue() {
        return color();
    }
    // endregion

    // region Extend assertions
    @Override
    public ColorAssert is() {
        return new ColorAssert().set(this);
    }
    // endregion
}
