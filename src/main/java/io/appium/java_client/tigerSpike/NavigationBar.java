package io.appium.java_client.tigerSpike;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * Main page object for tigetspike page.
 */
public class NavigationBar {

    private WebDriver driver;
    private static By tigerSpikeGlobalMenuId = By.id("menu-tigerspike-global-menu");
    private static By navigationBarMenuSelector = By.cssSelector("div.mobile-menu-button.mobile-menu-button-light.lines-button.x2");
    private static By globalMenuItemsSelector = By.cssSelector("ul#menu-tigerspike-global-menu a");

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getMenuBarWebElement() {
        return driver.findElement(navigationBarMenuSelector);
    }

    public void clickGlobalMenuIcon() {
        getMenuBarWebElement().click();
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.visibilityOf(getGlobalMenuWebElement()));
    }

    private WebElement getGlobalMenuWebElement() {
        return driver.findElement(tigerSpikeGlobalMenuId);
    }

    public boolean isGlobalMenuDisplayed() {
        return getGlobalMenuWebElement().isDisplayed();
    }

    private List<WebElement> getGlobalMenuWebElementsList() {
        return driver.findElements(globalMenuItemsSelector);
    }

    public List<String> getGlobalMenuNamesList() {
        Iterator<WebElement> itr = getGlobalMenuWebElementsList().iterator();
        List<String> menuLabels = new ArrayList();
        while (itr.hasNext()) {
            menuLabels.add(itr.next().getText());
        }
        return menuLabels;
    }
}
