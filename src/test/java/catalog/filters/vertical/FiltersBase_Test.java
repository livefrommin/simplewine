package catalog.filters.vertical;

import constants.Filters;
import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FiltersBase;
import webdriver.BaseTest;

@Epic("Каталог")
@Feature("Фильтры вертикальные")
public class FiltersBase_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.WINE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка очистки предвыбранных фильтров")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("890")
    @Link(name = "Link to video", value = "checkFilterInTop", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Италия, Белое, сладкое"})
    public void checkFilterInTop(String country, String color, String sugar) {
        filtersBase.openFilterAndSetCheckBox(Filters.COLOR_NAME, color)
                .openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .openFilterAndSetCheckBox(Filters.SUGAR_NAME, sugar)
                .compareSelectedMinFilters()
                .deleteAllMinFilters();
    }

    @Description("Проверка очистки всех фильтров")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("891")
    @Link(name = "Link to video", value = "clearAllFilters", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Грузия, Красное"})
    public void clearAllFilters(String country, String color) {
        filtersBase.openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .openFilterAndSetCheckBox(Filters.COLOR_NAME, color)
                .clickApply()
                .clickClear()
                .checkMinFilterIsClear();
    }

    @Description("Проверка деактивации выбранного чекбокса")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("892")
    @Link(name = "Link to video", value = "checkedAndUnchecked", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Австралия"})
    public void checkedAndUnchecked(String country) {
        filtersBase.openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .compareSelectedMinFilters()
                //кликаем еще раз, убираем чекбокс
                .setCheckBox(Filters.COUNTRY_NAME, country)
                .checkMinFilterIsClear();
    }

}
