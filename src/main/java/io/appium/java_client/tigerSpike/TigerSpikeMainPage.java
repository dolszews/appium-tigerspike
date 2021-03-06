package io.appium.java_client.tigerSpike;

import io.appium.java_client.utils.WebUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Main page object for tigetspike page.
 */
public class TigerSpikeMainPage {
    private WebDriver driver;
    private static By cookieBarId = By.id("catapult-cookie-bar");
    private static By cookieBarOkayButtonId = By.id("catapultCookie");
    private static By footerMenuSelector = By.cssSelector("div.footerism.vc_custom_1500631604176 h2 a");

    public TigerSpikeMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return "http://tigerspike.com/";
    }

    private WebElement getCookieBarWebElement() {
        return driver.findElement(cookieBarId);
    }

    public boolean isCookieBarDisplayed() {
        return getCookieBarWebElement().isDisplayed();
    }

    /**
     * Dismiss cookie bar.
     * Clicks in Okay Button and waits until cookieBar is not visible.
     */
    public void clickCookieBarOkayButton() {
        driver.findElement(cookieBarOkayButtonId).click();
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.invisibilityOf(getCookieBarWebElement()));
    }

    private void scrollToFooterMenu() {
        new WebUtils(driver).scrollElementIntoView(driver.findElement(footerMenuSelector));
    }

    /**
     * Click on defined menu item.
     * @param menuItemToClick - title of menu item.
     * @return TigerSpikeContactPage
     */
    public TigerSpikeContactPage clickFooterMenuItem(String menuItemToClick) {
        scrollToFooterMenu();
        driver.findElement(By.xpath("//a[contains(text(),\"" + menuItemToClick + "\")]")).click();
        return new TigerSpikeContactPage(driver);
    }
}
