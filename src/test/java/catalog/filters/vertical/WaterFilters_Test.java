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
@Story("Вода и соки")
public class WaterFilters_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.WATER_AND_JUICE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка placeholder'ов фильтра \"Цена\" в категории Вода и соки ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("893")
    @Link(name = "Link to video", value = "checkPriceInput_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"850, 3250"})
    public void checkPriceInput_water(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.PRICE_NAME, p1, p2)
                .clickApply().compareFilterPrice(Menu.WINE_NAME, p1, p2);
    }

    @Description("Проверка checkbox'ов фильтра \"Цена\" в категории Вода и соки ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("894")
    @Link(name = "Link to video", value = "checkPriceCheckBox_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"До 700 ₽"})
    public void checkPriceCheckBox_water(String price) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .clickApply().compareFilterPrice(price);
    }

    @Description("Проверка фильтра \"Тип напитка\" в категории Вода и соки")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("895")
    @Link(name = "Link to video", value = "checkTypeCheckBox_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Газированная"})
    public void checkTypeCheckBox_water(String type) {
        filtersBase.openFilterAndSetCheckBox(Filters.WATER_TYPE_NAME, type)
                .clickApply().compareFilterType(type);
    }

    @Description("Проверка быстрого поиска фильтра \"Объем\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("896")
    @Link(name = "Link to video", value = "checkVolumeInput_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"0.2"})
    public void checkVolumeInput_water(String volumeValue) {
        filtersBase.openFilterAndSetInputValue(Filters.VOLUME_NAME, volumeValue).checkFastSearchResult(volumeValue);
    }

    @Description("Проверка checkbox'ов фильтра \"Объем\" в категории Вода и соки")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("898")
    @Link(name = "Link to video", value = "checkVolumeCheckBox_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"0.5"})
    public void checkVolumeCheckBox_water(String volume) {
        filtersBase.openFilterAndSetCheckBox(Filters.VOLUME_NAME, volume)
                .clickApply().compareFilterVolume(volume);
    }

    @Description("Проверка быстрого поиска фильтра \"Страна\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("899")
    @Link(name = "Link to video", value = "checkCountryInput_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Но"})
    public void checkCountryInput_water(String country) {
        filtersBase.openFilterAndSetInputValue(Filters.COUNTRY_NAME, country).checkFastSearchResult(country);
    }

    @Description("Проверка checkbox'ов фильтра \"Страна\" в категории Вода и соки")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("900")
    @Link(name = "Link to video", value = "checkCountryCheckBox_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Италия"})
    public void checkCountryCheckBox_water(String country) {
        filtersBase.openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .clickApply().compareFilterCountry(country);
    }

    @Description("Проверка быстрого поиска фильтра \"Бренд\" в категории Вода и соки")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("901")
    @Link(name = "Link to video", value = "checkBrandInput_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"sa"})
    public void checkBrandInput_water(String brand) {
        filtersBase.openFilterAndSetInputValue(Filters.BRAND_NAME, brand).checkFastSearchResult(brand);
    }

    @Description("Проверка checkbox'ов фильтра \"Бренд\" в категории Вода и соки")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("902")
    @Link(name = "Link to video", value = "checkBrandCheckBox_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Aqua Russa"})
    public void checkBrandCheckBox_water(String brand) {
        filtersBase.openFilterAndSetCheckBox(Filters.BRAND_NAME, brand)
                .clickApply().compareFilterProducer(brand);
    }

    @Description("Проверка фильтра \"Наличие в винотеке\" в категории Вода и соки")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("885")
    @Link(name = "Link to video", value = "checkMagazineCheckbox_water", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Винотека & ресторан GRAND CRU by Adrian Quetglas"})
    void checkMagazineCheckbox_water(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.MAGAZINE_NAME, input)
                .clickApply().compareFilterMagazine(input);
    }
}
