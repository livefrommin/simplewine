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
@Story("Аксессуары")
public class AccessoriesFilters_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.ACCESSORY_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка placeholder'ов фильтра \"Цена\" в категории Аксессуары")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("924")
    @Link(name = "Link to video", value = "checkPriceInput_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"850, 1000"})
    public void checkPriceInput_accessory(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.PRICE_NAME, p1, p2)
                .clickApply().compareFilterPrice(Menu.WINE_NAME, p1, p2);
    }

    @Description("Проверка checkbox'ов фильтра \"Цена\" в категории Аксессуары")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("925")
    @Link(name = "Link to video", value = "checkPriceCheckBox_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"700–1 500 ₽"})
    public void checkPriceCheckBox_accessory(String price) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .clickApply().compareFilterPrice(price);
    }

    @Description("Проверка быстрого поиска фильтра \"Бренд\" в категории Аксессуары")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("926")
    @Link(name = "Link to video", value = "checkBrandInput_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Barmi"})
    void checkBrandInput_accessory(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.BRAND_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Бренд\" в категории Аксессуары")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("927")
    @Link(name = "Link to video", value = "checkBrandCheckBox_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Arir"})
    void checkBrandCheckBox_accessory(String producer) {
        filtersBase.openFilterAndSetCheckBox(Filters.BRAND_NAME, producer)
                .clickApply().compareFilterProducer(producer);
    }

    @Description("Проверка быстрого поиска фильтра \"Страна\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("928")
    @Link(name = "Link to video", value = "checkCountryInput_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Итал"})
    public void checkCountryInput_accessory(String country) {
        filtersBase.openFilterAndSetInputValue(Filters.COUNTRY_NAME, country).checkFastSearchResult(country);
    }

    @Description("Проверка checkbox'ов фильтра \"Страна\" в категории Аксессуары")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("929")
    @Link(name = "Link to video", value = "checkCountryCheckBox_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Германия"})
    public void checkCountryCheckBox_accessory(String country) {
        filtersBase.openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .clickApply().compareFilterCountry(country);
    }

    @Description("Проверка быстрого поиска фильтра \"Наличие в винотеке\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("930")
    @Link(name = "Link to video", value = "checkMagazineInput_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"лен"})
    void checkMagazineInput_accessory(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.MAGAZINE_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Наличие в винотеке\" в категории Аксессуары")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("931")
    @Link(name = "Link to video", value = "checkMagazineCheckbox_accessory", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Винотека & ресторан GRAND CRU by Adrian Quetglas"})
    void checkMagazineCheckbox_accessory(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.MAGAZINE_NAME, input)
                .clickApply().compareFilterMagazine(input);
    }
}
