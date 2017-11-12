package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:cucumber.json", features = {
        "src/test/Gherkin/io.appium.java_client/tigerSpike/CookieBar.feature"},
        glue = "stepdefs")
public class CookieBarTest {

}