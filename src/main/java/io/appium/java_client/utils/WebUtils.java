package io.appium.java_client.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebUtils {
    private WebDriver driver;
    public WebUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollElementIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }
}
