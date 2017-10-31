import io.appium.java_client.tigerSpike.TigerSpikeMainPage;
import org.junit.Assert;

public class tigerspikeCookieBar {

    @cucumber.api.java.en.Then("^I verify Cookie Bar is displayed")
    public void iVerifyCookieBarIsDisplayed() {
        Assert.assertTrue("Cookie message is NOT displayed", tigerSpikeMainPage.isCookieBarDisplayed());
    }

    @cucumber.api.java.en.When("^I dismiss Cookie Bar$")
    public void iDismissCookieBar() {
        tigerSpikeMainPage.clickCookieBarOkayButton();
    }

    @cucumber.api.java.en.Then("^Cookie Bar is not displayed")
    public void cookieBarIsNotDisplayed() {
        Assert.assertTrue("Cookie message is NOT displayed", tigerSpikeMainPage.isCookieBarDisplayed());
    }
}
