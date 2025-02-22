package com.epam.jdi.light.asserts.generic;

import com.epam.jdi.light.common.JDIAction;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.is;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public interface ITextAssert<A> {
    @JDIAction("Assert that '{name}' text {0}")
    A text(Matcher<String> condition);
    @JDIAction("Assert that '{name}' text {0}")
    default A text(String text) { return text(is(text)); }
    @JDIAction("Assert that '{name}' value {0}")
    default A value(Matcher<String> condition) {
        return text(condition);
    }
    @JDIAction("Assert that '{name}' value {0}")
    default A value(String condition) {
        return text(condition);
    }
}