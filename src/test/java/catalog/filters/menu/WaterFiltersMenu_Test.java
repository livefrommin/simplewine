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
@Story("Вода и соки")
public class WaterFiltersMenu_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.hoverMenuItem(Menu.WATER_AND_JUICE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Вода и соки: цена - От 5 000 ₽")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1079")
    @Link(name = "Link to video", value = "checkPriceMenu_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"От 5 000 ₽, от 5000 ₽"})
    public void checkPriceMenu_water(String menuItem, String price) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterPrice(price);
    }

    @Description("Вода и соки: популярные бренды - Sairme")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1081")
    @Link(name = "Link to video", value = "checkBrandMenu_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Sairme"})
    public void checkBrandMenu_water(String menuItem) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterEqInName(menuItem);
    }

    @Description("Вода и соки: газация - Негазированная")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1080")
    @Link(name = "Link to video", value = "checkGazMenu_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Негазированная"})
    public void checkGazMenu_water(String menuItem) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterEqInName(menuItem);
    }
}
