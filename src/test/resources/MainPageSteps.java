import io.appium.java_client.tigerSpike.NavigationBar;
import io.appium.java_client.tigerSpike.TigerSpikeMainPage;
import org.junit.Assert;

public class tigerspikeCookieBar {
    @cucumber.api.java.en.Given("^I open tigerspike webpage$")
    public void iOpenTigerspikeWebpage() {
        tigerSpikeMainPage = new TigerSpikeMainPage(driver);
        driver.get(tigerSpikeMainPage.getUrl());
    }

    @cucumber.api.java.en.Then("^I open Global Menu")
    public void iOpenGlobalMenu() {
        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickGlobalMenuIcon();
    }

    Given I open tigerspike webpage
    When I dismiss Cookie Bar
    And I open Global Menu
    Then I verify Global Menu is opened
    And I validate Global Menu content
}
