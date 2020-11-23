package helpers;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SelenideHelper {

    final static Logger logger = LogManager.getLogger(SelenideHelper.class);

    /***
     * Фокусируется на элементе по центру экрана, и нажимает на него.
     * @param path - путь элемента
     */
    public static void click(String path) {
        logger.info("Scroll to element with path: " + path);
        $(path).scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}").hover().click();
    }

    public static void click(SelenideElement element) {
        logger.info("Scroll to element: " + element.getText());
        element.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}").hover().click();
    }

    public static void strollToElement(SelenideElement element) {
        logger.info("Scroll to element:" + element);
        element.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
    }

    public static void compareUrl(String currentUrl) {
        for (int i = 0; i < 10; i++) {
            sleep(500);
            boolean result = !currentUrl.equals(url());
            if (result) break;
        }
    }
}
