package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.tigerSpike.TigerSpikeMainPage;
import org.junit.Assert;

public class CookieBarSteps {
    TigerSpikeMainPage tigerSpikeMainPage = new TigerSpikeMainPage(driver);

    @When("^I dismiss Cookie Bar$")
    public void iDismissCookieBar() {
        tigerSpikeMainPage.clickCookieBarOkayButton();
    }

    @Then("^I verify Cookie Bar is displayed$")
    public void iVerifyCookieBarIsDisplayed() {
        Assert.assertTrue("Cookie message is NOT displayed", tigerSpikeMainPage.isCookieBarDisplayed());
    }

    @Then("^Cookie Bar is not displayed$")
    public void cookieBarIsNotDisplayed() {
        Assert.assertFalse("Cookie message is still displayed", tigerSpikeMainPage.isCookieBarDisplayed());
    }
}
