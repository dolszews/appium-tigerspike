package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.tigerSpike.TigerSpikeContactPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ContactPageSteps {

    private WebDriver driver;
    TigerSpikeContactPage tigerSpikeContactPage = new TigerSpikeContactPage(driver);

    @Then("^I verify Contacts page is opened$")
    public void iVerifyContactsPageIsOpened() {
        Assert.assertEquals("contact page was not opened", tigerSpikeContactPage.getUrl(), driver.getCurrentUrl());
    }

    @And("^I validate number of Tigerspike offices around the world is '(\\d+)'$")
    public void iValidateNumberOfTigerspikeOfficesArroundTheWorldIs(int officesNumber) {
        tigerSpikeContactPage.getNumberOfOficeLocations();
        Assert.assertEquals("Office number is not as expected: " + officesNumber,
                officesNumber, tigerSpikeContactPage.getNumberOfOficeLocations());
    }
}
