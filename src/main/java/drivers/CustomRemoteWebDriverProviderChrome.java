package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CustomRemoteWebDriverProviderChrome implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        Map < String, Object > prefs = new HashMap < String, Object > ();
        Map < String, Object > profile = new HashMap < String, Object > ();
        Map < String, Object > contentSettings = new HashMap < String, Object > ();
        contentSettings.put("geolocation", 1);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return null;
    }
}

