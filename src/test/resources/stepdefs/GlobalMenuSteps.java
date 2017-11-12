package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.tigerSpike.NavigationBar;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class GlobalMenuSteps {

    private WebDriver driver;
    NavigationBar navigationBar = new NavigationBar(driver);
    private List<String> globalMenuTargetList = Arrays.asList("ABOUT", "OUR WAY", "WORK", "BLOG", "JOIN US", "CONTACT");

    @And("^I open Global Menu$")
    public void iOpenGlobalMenu() {
        navigationBar.clickGlobalMenuIcon();
    }

    @Then("^I verify Global Menu is opened$")
    public void iVerifyGlobalMenuIsOpened() {
        Assert.assertTrue("Global menu not displayed", navigationBar.isGlobalMenuDisplayed());
    }

    @And("^I validate Global Menu content$")
    public void iValidateGlobalMenuContent() {
        List<String> menuList = navigationBar.getGlobalMenuNamesList();
        Assert.assertThat("Global Menu list does not match target: "
                + globalMenuTargetList, menuList, is(globalMenuTargetList));
    }
}
