package io.appium.java_client.tigerSpike;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TigerSpikeContactPage {
    private WebDriver driver;
    private static By officesHeaderSelector
            = By.cssSelector("div.row.unequal.col-no-gutter p.font-weight-500 > span");

    public TigerSpikeContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return "https://tigerspike.com/contact/";
    }

    private List<WebElement> getOfficesNamesWebElementsList() {
        return driver.findElements(officesHeaderSelector);
    }

    public int getNumberOfOficeLocations(){
        return getOfficesNamesWebElementsList().size();
    }
}
