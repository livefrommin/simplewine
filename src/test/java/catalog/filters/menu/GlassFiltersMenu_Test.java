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
@Story("Стекло")
public class GlassFiltersMenu_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.hoverMenuItemLink(Menu.GLASS_NAME_URL);
        filtersBase = new FiltersBase();
    }

    @Description("Бокалы: тип - Декантеры")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1082")
    @Link(name = "Link to video", value = "checkTypeMenu_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Декантеры, Декантер"})
    public void checkTypeMenu_glass(String menuItem, String name) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterEqInName(name);
    }

    @Description("Бокалы: наборы - из 6 бокалов")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1083")
    @Link(name = "Link to video", value = "checkSetMenu_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"из 6 бокалов, 6"})
    public void checkSetMenu_glass(String menuItem, String name) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterEqInName(name);
    }

    @Description("Бокалы: популярные бренды - Zalto")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1084")
    @Link(name = "Link to video", value = "checkBrandMenu_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Zalto"})
    public void checkBrandMenu_glass(String menuItem) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterProducer(menuItem);
    }

    @Description("Бокалы: подходит - Для пива")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1085")
    @Link(name = "Link to video", value = "checkForMenu_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Zalto"})
    public void checkForMenu_glass(String menuItem) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterProducer(menuItem);
    }

}
