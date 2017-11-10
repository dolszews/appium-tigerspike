package io.appium.java_client.tigerSpike;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class OfficesListTest {

    private WebDriver driver;
    TigerSpikeMainPage tigerSpikeMainPage;
    TigerSpikeContactPage tigerSpikeContactPage;
    private int officesNumber = 9;
    private AppiumDriverLocalService service;

    /**
     * The setting up.
     */
    @Before
    public void setUp() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
        driver = new AndroidDriver<RemoteWebElement>(service.getUrl(), capabilities);
        //This time out is set because test can be run on slow Android SDK emulator
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
    }

    /**
     * finishing.
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }

        if (service != null) {
            service.stop();
        }
    }

    @Test
    public void OfficesListValidationTest() {

        tigerSpikeMainPage = new TigerSpikeMainPage(driver);
        driver.get(tigerSpikeMainPage.getUrl());
        tigerSpikeMainPage.clickCookieBarOkayButton();

        tigerSpikeContactPage = tigerSpikeMainPage.clickFooterMenuItem("Offices");
        Assert.assertEquals("contact page was not opened", tigerSpikeContactPage.getUrl(), driver.getCurrentUrl());

        tigerSpikeContactPage.getNumberOfOficeLocations();
        Assert.assertEquals("Office number is not as expected: " + officesNumber,
                officesNumber, tigerSpikeContactPage.getNumberOfOficeLocations());
    }
}
