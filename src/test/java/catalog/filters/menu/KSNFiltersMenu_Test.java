package catalog.filters.menu;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.FiltersBase;
import webdriver.BaseTest;

@Epic("Каталог")
@Feature("Фильтры из меню")
@Story("Крепкие напитки")
public class KSNFiltersMenu_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.hoverMenuItem(Menu.KSN_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Крепкое: тип - Кашаса")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1075")
    @Link(name = "Link to video", value = "checkTypedMenu_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Кашаса"})
    public void checkTypedMenu_KSN(String menuItem) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterEqInName(menuItem);
    }

    @Description("Крепкое: популярные бренды - Angostura")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1076")
    @Link(name = "Link to video", value = "checkProducerMenu_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Angostura"})
    public void checkProducerMenu_KSN(String menuItem) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterProducer(menuItem);
    }

    @Description("Крепкое: цена - От 5 000 до 7 000 ₽")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1077")
    @Link(name = "Link to video", value = "checkPriceMenu_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"От 5 000 до 7 000 ₽, 5 000–7 000 ₽"})
    public void checkPriceMenu_KSN(String menuItem, String price) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterPrice(price);
    }

}
