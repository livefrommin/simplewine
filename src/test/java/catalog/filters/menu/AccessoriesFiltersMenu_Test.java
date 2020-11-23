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
@Story("Аксессуары")
public class AccessoriesFiltersMenu_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {

        mainPage.hoverMenuItem(Menu.ACCESSORY_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Аксессуары: для вина - Декантирование")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issues(value = {
    @Issue("1086"),
    @Issue("1087"),
    @Issue("1088"),
    @Issue("1089"),
    @Issue("1090")})
    @Link(name = "Link to video", value = "checkAccessoriesMenu", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Декантирование, Декантер","Пакеты, Пакет","Чехлы, Чехол","Масло и бальзамики, Olio","Иглы, Игл"})
    public void checkAccessoriesMenu(String menuItem, String value) {
        mainPage.chooseSubMenuItem(menuItem);
        filtersBase.compareFilterValue(value);
    }

}
