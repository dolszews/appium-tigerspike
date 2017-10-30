package io.appium.java_client.pagefactory;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Defines set of chained/possible locators. Each one locator
 * should be defined with {@link AndroidFindAll}
 */
@Target(value = {TYPE, FIELD})
@Retention(value = RUNTIME)
public @interface AndroidFindByAllSet {
    /**
     * @return an array of {@link AndroidFindAll} which builds a sequence of
     * the chained searching for elements or a set of possible locators
     */
    AndroidFindAll[] value();
}
