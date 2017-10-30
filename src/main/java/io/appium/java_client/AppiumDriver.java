/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_NAME;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.internal.JsonToMobileElementConverter;
import io.appium.java_client.remote.AppiumCommandExecutor;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.html5.RemoteLocationContext;
import org.openqa.selenium.remote.http.HttpClient;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @param <T> the required type of class which implement {@link org.openqa.selenium.WebElement}.
 *          Instances of the defined type will be returned via findElement* and findMElements*
 *          Warning (!!!). Allowed types:
 *          {@link org.openqa.selenium.WebElement}
 *          {@link org.openqa.selenium.remote.RemoteWebElement}
 *          {@link io.appium.java_client.MobileElement} and its subclasses that designed
 *          specifically
 *          for each target mobile OS (still Android and iOS)
*/
@SuppressWarnings("unchecked")
public class AppiumDriver<T extends WebElement>
    extends DefaultGenericMobileDriver<T> {

    private static final ErrorHandler errorHandler = new ErrorHandler(new ErrorCodesMobile(), true);
    // frequently used command parameters
    private URL remoteAddress;
    private RemoteLocationContext locationContext;
    private ExecuteMethod executeMethod;

    /**
     * @param executor is an instance of {@link org.openqa.selenium.remote.HttpCommandExecutor}
     *                 or class that extends it. Default commands or another vendor-specific
     *                 commands may be specified there.
     * @param capabilities take a look
     *                     at {@link org.openqa.selenium.Capabilities}
     */
    public AppiumDriver(HttpCommandExecutor executor, Capabilities capabilities) {
        super(executor, capabilities);
        this.executeMethod = new AppiumExecutionMethod(this);
        locationContext = new RemoteLocationContext(executeMethod);
        super.setErrorHandler(errorHandler);
        this.remoteAddress = executor.getAddressOfRemoteServer();
        this.setElementConverter(new JsonToMobileElementConverter(this, this));
    }

    public AppiumDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        this(new AppiumCommandExecutor(MobileCommand.commandRepository, remoteAddress),
            desiredCapabilities);
    }

    public AppiumDriver(URL remoteAddress, HttpClient.Factory httpClientFactory,
        Capabilities desiredCapabilities) {
        this(new AppiumCommandExecutor(MobileCommand.commandRepository, remoteAddress,
            httpClientFactory), desiredCapabilities);
    }

    public AppiumDriver(AppiumDriverLocalService service, Capabilities desiredCapabilities) {
        this(new AppiumCommandExecutor(MobileCommand.commandRepository, service),
            desiredCapabilities);
    }

    public AppiumDriver(AppiumDriverLocalService service, HttpClient.Factory httpClientFactory,
        Capabilities desiredCapabilities) {
        this(new AppiumCommandExecutor(MobileCommand.commandRepository, service, httpClientFactory),
            desiredCapabilities);
    }

    public AppiumDriver(AppiumServiceBuilder builder, Capabilities desiredCapabilities) {
        this(builder.build(), desiredCapabilities);
    }

    public AppiumDriver(AppiumServiceBuilder builder, HttpClient.Factory httpClientFactory,
        Capabilities desiredCapabilities) {
        this(builder.build(), httpClientFactory, desiredCapabilities);
    }

    public AppiumDriver(HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        this(AppiumDriverLocalService.buildDefaultService(), httpClientFactory,
            desiredCapabilities);
    }

    public AppiumDriver(Capabilities desiredCapabilities) {
        this(AppiumDriverLocalService.buildDefaultService(), desiredCapabilities);
    }

    /**
     * @param originalCapabilities the given {@link Capabilities}.
     * @param newPlatform a {@link MobileCapabilityType#PLATFORM_NAME} value which has
     *                    to be set up
     * @return {@link Capabilities} with changed mobile platform value
     */
    protected static Capabilities substituteMobilePlatform(Capabilities originalCapabilities,
        String newPlatform) {
        DesiredCapabilities dc = new DesiredCapabilities(originalCapabilities);
        dc.setCapability(PLATFORM_NAME, newPlatform);
        return dc;
    }

    public List<T> findMElements(By by) {
        return super.findElements(by);
    }

    public List<T> findMElements(String by, String using) {
        return super.findElements(by, using);
    }

    public List<T> findMElementsById(String id) {
        return super.findElementsById(id);
    }

    public List<T> findMElementsByLinkText(String using) {
        return super.findElementsByLinkText(using);
    }

    public List<T> findMElementsByPartialLinkText(String using) {
        return super.findElementsByPartialLinkText(using);
    }

    public List<T> findMElementsByTagName(String using) {
        return super.findElementsByTagName(using);
    }

    public List<T> findMElementsByName(String using) {
        return super.findElementsByName(using);
    }

    public List<T> findMElementsByClassName(String using) {
        return super.findElementsByClassName(using);
    }

    public List<T> findMElementsByCssSelector(String using) {
        return super.findElementsByCssSelector(using);
    }

    public List<T> findMElementsByXPath(String using) {
        return super.findElementsByXPath(using);
    }

    @Override public List<T> findElementsByAccessibilityId(String using) {
        return super.findElementsByAccessibilityId(using);
    }

    @Override public ExecuteMethod getExecuteMethod() {
        return executeMethod;
    }

    @Override public WebDriver context(String name) {
        checkNotNull(name, "Must supply a context name");
        try {
            execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", name));
            return this;
        } catch (WebDriverException e) {
            throw new NoSuchContextException(e.getMessage(), e);
        }
    }

    @Override public Set<String> getContextHandles() {
        Response response = execute(DriverCommand.GET_CONTEXT_HANDLES);
        Object value = response.getValue();
        try {
            List<String> returnedValues = (List<String>) value;
            return new LinkedHashSet<>(returnedValues);
        } catch (ClassCastException ex) {
            throw new WebDriverException(
                "Returned value cannot be converted to List<String>: " + value, ex);
        }
    }

    @Override public String getContext() {
        String contextName =
            String.valueOf(execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE).getValue());
        if ("null".equalsIgnoreCase(contextName)) {
            return null;
        }
        return contextName;
    }

    @Override public DeviceRotation rotation() {
        Response response = execute(DriverCommand.GET_SCREEN_ROTATION);
        DeviceRotation deviceRotation =
                new DeviceRotation((Map<String, Number>) response.getValue());
        if (deviceRotation.getX() < 0 || deviceRotation.getY() < 0 || deviceRotation.getZ() < 0) {
            throw new WebDriverException("Unexpected orientation returned: " + deviceRotation);
        }
        return deviceRotation;
    }

    @Override public void rotate(DeviceRotation rotation) {
        execute(DriverCommand.SET_SCREEN_ROTATION, rotation.parameters());
    }


    @Override public void rotate(ScreenOrientation orientation) {
        execute(DriverCommand.SET_SCREEN_ORIENTATION,
                ImmutableMap.of("orientation", orientation.value().toUpperCase()));
    }

    @Override public ScreenOrientation getOrientation() {
        Response response = execute(DriverCommand.GET_SCREEN_ORIENTATION);
        String orientation = response.getValue().toString().toLowerCase();
        if (orientation.equals(ScreenOrientation.LANDSCAPE.value())) {
            return ScreenOrientation.LANDSCAPE;
        } else if (orientation.equals(ScreenOrientation.PORTRAIT.value())) {
            return ScreenOrientation.PORTRAIT;
        } else {
            throw new WebDriverException("Unexpected orientation returned: " + orientation);
        }
    }

    @Override public Location location() {
        return locationContext.location();
    }

    @Override public void setLocation(Location location) {
        locationContext.setLocation(location);
    }

    public URL getRemoteAddress() {
        return remoteAddress;
    }

    @Override public boolean isBrowser() {
        return super.isBrowser()
                && !containsIgnoreCase(getContext(), "NATIVE_APP");
    }
}