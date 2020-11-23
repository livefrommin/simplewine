package webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CatalogPage;
import pages.MainPage;
import pages.FiltersBase;
import pages.items.User;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    final static Logger logger = LogManager.getLogger(FiltersBase.class);

    private static final boolean MAXIMIZED = Boolean.parseBoolean(ConfigProperties.getProperty("browser_start_maximized"));
    private static final int TIMEOUT = Integer.parseInt(ConfigProperties.getProperty("browser_timeout"));
    protected static User user;
    protected MainPage mainPage;
    protected CatalogPage catalogPage;
    public BaseTest() {
        logger.info("BaseTest init");
    }

    @BeforeAll
    public static void beforeAll() {
        user = new User();
        logger.info("Create user for test: " + user.toString());
    }

    @BeforeEach
    public void before(TestInfo testInfo) {
        String testName = testInfo.getDisplayName();
        createConfiguration(testName.substring(0, testName.indexOf("(")));
        logger.info("Test " + testName + " started.");
        mainPage = new MainPage();
        catalogPage = new CatalogPage();
        open("");
        mainPage.openAndConfirmAge();
    }

    private void createConfiguration(String testName) {
        logger.info("Create configuration for browser.");
        Configuration.timeout = TIMEOUT;
        Configuration.startMaximized = MAXIMIZED;
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("videoName", testName);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    @AfterEach
    public void after() {
        logger.info("Test finished.");
        Selenide.closeWebDriver();
    }
}
