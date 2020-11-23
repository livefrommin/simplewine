package catalog.filters.menu;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FiltersBase;
import webdriver.BaseTest;

@Epic("Каталог")
@Feature("Фильтры из меню")
@Story("Вино")
public class WineFiltersMenu_Test extends BaseTest {
    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.hoverMenuItem(Menu.WINE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Вино: цвет - розовое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1064")
    @Link(name = "Link to video", value = "checkColorMenu_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Розовое"})
    public void checkColorMenu_wine(String color) {
        mainPage.chooseSubMenuItem(color);
        filtersBase.compareFilterColor(color);
    }

    @Description("Вино: страна - Германия")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1065")
    @Link(name = "Link to video", value = "checkCountryMenu_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Германия"})
    public void checkCountryMenu_wine(String country) {
        mainPage.chooseSubMenuItem(country);
        filtersBase.compareFilterCountry(country);
    }

    @Description("Вино: крепленые вина - Херес")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1066")
    @Link(name = "Link to video", value = "checkTypeMenu_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Херес"})
    public void checkTypeMenu_wine(String type) {
        mainPage.chooseSubMenuItem(type);
        filtersBase.compareFilterEqInName(type);
    }

    @Description("Вино: сорт винограда - Шардоне")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1067")
    @Link(name = "Link to video", value = "checkSortMenu_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Шардоне"})
    public void checkSortMenu_wine(String type) {
        mainPage.chooseSubMenuItem(type);
        filtersBase.compareFilterResultFromDesc(type);
    }

    @Description("Вино: цена - От 1 500 до 3 000 ₽")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1068")
    @Link(name = "Link to video", value = "checkPriceMenu_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"От 1 500 до 3 000 ₽"})
    public void checkPriceMenu_wine(String type) {
        mainPage.chooseSubMenuItem(type);
        filtersBase.compareFilterPrice("", 1500, 3000);
    }

    @Description("Вино: вина с рейтингом: 95+ баллов")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1069")
    @Link(name = "Link to video", value = "checkRatingMenu_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"95+ баллов"})
    public void checkRatingMenu_wine(String type) {
        mainPage.chooseSubMenuItem(type);
        filtersBase.compareFilterRP(95, 100);
    }
}
