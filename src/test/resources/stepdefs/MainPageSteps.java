package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.tigerSpike.TigerSpikeMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

public class MainPageSteps {
    DesiredCapabilities capabilities;
    private WebDriver driver;
    TigerSpikeMainPage tigerSpikeMainPage;
    private AppiumDriverLocalService service;

    @Given("^I open tigerspike webpage$")
    public void iOpenTigerspikeWebpage() {

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
        driver = new AndroidDriver<RemoteWebElement>(service.getUrl(), capabilities);
        //This time out is set because test can be run on slow Android SDK emulator
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
        tigerSpikeMainPage = new TigerSpikeMainPage(driver);
        driver.get(tigerSpikeMainPage.getUrl());
    }

    @When("^I click footer '(.*)' link$")
    public void iClickFooterOfficeLink(String link)  {
        tigerSpikeContactPage = tigerSpikeMainPage.clickFooterMenuItem(link);
    }
}
