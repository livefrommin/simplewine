package catalog.filters.menu;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FiltersBase;
import webdriver.BaseTest;

@Epic("Каталог")
@Feature("Фильтры из меню")
@Story("Шампанское и игристое")
public class ChampagneFiltersMenu_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.hoverMenuItem(Menu.CHAMPAGNE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Шампанское: тип - Кава")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1070")
    @Link(name = "Link to video", value = "checkAppelasonMenu_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Кава, Cava DO"})
    public void checkAppelasonMenu_champagne(String menuItem, String appelason) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterAppelason(appelason);
    }

    @Description("Шампанское:цвет - Красное")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1071")
    @Link(name = "Link to video", value = "checkColorMenu_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Красное"})
    public void checkColorMenu_champagne(String color) {
        mainPage.chooseSubMenuItem(color);
        filtersBase.compareFilterColor(color);
    }

    @Description("Шампанское:содержание сахара - Сладкое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1072")
    @Link(name = "Link to video", value = "checkSugarMenu_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Сладкое"})
    public void checkSugarMenu_champagne(String sugar) {
        mainPage.chooseSubMenuItem(sugar);
        filtersBase.compareFilterSugar(sugar);
    }

    @Description("Шампанское: цена - От 8 000 ₽")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1073")
    @Link(name = "Link to video", value = "checkPriceMenu_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"От 8 000 ₽, от 8000 ₽"})
    public void checkPriceMenu_champagne(String menuItem, String price) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterPrice(price);
    }
}
