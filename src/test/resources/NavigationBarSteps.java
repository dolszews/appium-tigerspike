import io.appium.java_client.tigerSpike.NavigationBar;
import io.appium.java_client.tigerSpike.TigerSpikeMainPage;
import org.junit.Assert;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class tigerspikeCookieBar {
    @cucumber.api.java.en.Then("^I verify Global Menu is opened$")
    public void iOpenTigerspikeWebpage() {
        Assert.assertTrue("Global menu not displayed", navigationBar.isGlobalMenuDisplayed());
    }

    @cucumber.api.java.en.Then("^I validate Global Menu content")
    public void iOpenGlobalMenu() {
        List<String> menuList = navigationBar.getGlobalMenuNamesList();
        Assert.assertThat("Global Menu list does not match target: " + globalMenuTargetList ,menuList,is(globalMenuTargetList));
    }
}
