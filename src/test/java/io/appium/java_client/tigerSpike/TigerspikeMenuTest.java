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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;

public class TigerspikeMenuTest {

    private WebDriver driver;
    TigerSpikeMainPage tigerSpikeMainPage;
    private AppiumDriverLocalService service;
    private List<String> globalMenuTargetList = Arrays.asList("ABOUT", "OUR WAY", "WORK", "BLOG", "JOIN US", "CONTACT");

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
    public void TigerSpikeGlobalMenuTest() {

        tigerSpikeMainPage = new TigerSpikeMainPage(driver);
        driver.get(tigerSpikeMainPage.getUrl());

        Assert.assertTrue("Cookie message is NOT displayed", tigerSpikeMainPage.isCookieBarDisplayed());
        tigerSpikeMainPage.clickCookieBarOkayButton();
        Assert.assertFalse("Cookie message is still displayed", tigerSpikeMainPage.isCookieBarDisplayed());

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickGlobalMenuIcon();
        Assert.assertTrue("Global menu not displayed", navigationBar.isGlobalMenuDisplayed());

        List<String> menuList = navigationBar.getGlobalMenuNamesList();
        Assert.assertThat("Global Menu list does not match target: "
                + globalMenuTargetList ,menuList,is(globalMenuTargetList));
    }
}
