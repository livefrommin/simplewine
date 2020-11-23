package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CustomWebDriverProviderFirefox implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        return new FirefoxDriver();
    }
}
