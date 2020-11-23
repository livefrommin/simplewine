package catalog.filters.vertical;

import constants.Filters;
import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FiltersBase;
import webdriver.BaseTest;

@Epic("Каталог")
@Feature("Фильтры вертикальные")
@Story("Крепкие напитки")
public class KSNFilters_Test extends BaseTest {

    FiltersBase filtersBase;
    private static final String categoryName = "Крепкие напитки";

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.KSN_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка фильтра \"Товар со скидкой\" в категории" + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("932")
    @Link(name = "Link to video", value = "checkDiscount_KSN", type = "video")
    @Test
    public void checkDiscount_KSN() {
        filtersBase.setSingleCheckBox(Filters.DISCOUNT_NAME).clickApply().compareFilterDiscount();
    }

    @Description("Проверка placeholder'ов фильтра \"Цена\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("933")
    @Link(name = "Link to video", value = "checkPriceInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"850, 1000"})
    public void checkPriceInput_KSN(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.PRICE_NAME, p1, p2)
                .clickApply().compareFilterPrice(Menu.WINE_NAME, p1, p2);
    }

    @Description("Проверка checkbox'ов фильтра \"Цена\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("934")
    @Link(name = "Link to video", value = "checkPriceCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"700–1 500 ₽"})
    public void checkPriceCheckBox_KSN(String price) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .clickApply().compareFilterPrice(price);
    }

    @Description("Проверка быстрого поиска фильтра \"Тип напитка\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("935")
    @Link(name = "Link to video", value = "checkTypeInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Лик"})
    public void checkTypeInput_KSN(String volumeValue) {
        filtersBase.openFilterAndSetInputValue(Filters.KSN_TYPE_NAME, volumeValue).checkFastSearchResult(volumeValue);
    }

    @Description("Проверка checkbox'ов фильтра \"Тип напитка\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("936")
    @Link(name = "Link to video", value = "checkTypeCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Кашаса"})
    public void checkTypeCheckBox_KSN(String type) {
        filtersBase.openFilterAndSetCheckBox(Filters.KSN_TYPE_NAME, type)
                .clickApply().compareFilterEqInTitle(type);
    }

    @Description("Проверка быстрого поиска фильтра \"Категория напитка\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("937")
    @Link(name = "Link to video", value = "checkCategoryInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"reser"})
    public void checkCategoryInput_KSN(String value) {
        filtersBase.openFilterAndSetInputValue(Filters.CATEGORY_TYPE_NAME, value).checkFastSearchResult(value);
    }

    @Description("Проверка checkbox'ов фильтра \"Категория напитка\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("938")
    @Link(name = "Link to video", value = "checkCategoryCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Napoleon"})
    public void checkCategoryCheckBox_KSN(String value) {
        filtersBase.openFilterAndSetCheckBox(Filters.CATEGORY_TYPE_NAME, value)
                .clickApply().compareFilterEqInTitle(value);
    }

    @Description("Проверка быстрого поиска фильтра \"Страна\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("939")
    @Link(name = "Link to video", value = "checkCountryInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Арме"})
    public void checkCountryInput_KSN(String country) {
        filtersBase.openFilterAndSetInputValue(Filters.COUNTRY_NAME, country).checkFastSearchResult(country);
    }

    @Description("Проверка checkbox'ов фильтра \"Страна\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("940")
    @Link(name = "Link to video", value = "checkCountryCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Испания"})
    public void checkCountryCheckBox_KSN(String country) {
        filtersBase.openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .clickApply().compareFilterCountry(country);
    }

    @Description("Проверка быстрого поиска фильтра \"Регион\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("941")
    @Link(name = "Link to video", value = "checkRegionInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Аки"})
    public void checkRegionInput_KSN(String region) {
        filtersBase.openFilterAndSetInputValue(Filters.REGION_NAME, region).checkFastSearchResult(region);
    }

    @Description("Проверка checkbox'ов фильтра \"Регион\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("942")
    @Link(name = "Link to video", value = "checkRegionCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Акита"})
    public void checkRegionCheckBox_KSN(String region) {
        filtersBase.openFilterAndSetCheckBox(Filters.REGION_NAME, region)
                .clickApply().compareFilterResultFromDesc(region);
    }

    @Description("Проверка быстрого поиска фильтра \"Аппелласьон\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("943")
    @Link(name = "Link to video", value = "checkAppInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Cogn"})
    public void checkAppInput_KSN(String app) {
        filtersBase.openFilterAndSetInputValue(Filters.APPELASON_NAME, app).checkFastSearchResult(app);
    }

    @Description("Проверка checkbox'ов фильтра \"Аппелласьон\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("944")
    @Link(name = "Link to video", value = "checkAppCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Fine Champagne AOC"})
    public void checkAppCheckBox_KSN(String app) {
        filtersBase.openFilterAndSetCheckBox(Filters.APPELASON_NAME, app)
                .clickApply().compareFilterAppelason(app);
    }

    @Description("Проверка быстрого поиска фильтра \"Бренд\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("945")
    @Link(name = "Link to video", value = "checkBrandInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Black"})
    void checkBrandInput_KSN(String producer) {
        filtersBase.openFilterAndSetInputValue(Filters.BRAND_NAME, producer).checkFastSearchResult(producer);
    }

    @Description("Проверка checkbox'ов фильтра \"Бренд\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("946")
    @Link(name = "Link to video", value = "checkBrandCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Black Bottle"})
    void checkBrandCheckBox_KSN(String producer) {
        filtersBase.openFilterAndSetCheckBox(Filters.BRAND_NAME, producer)
                .clickApply().compareFilterProducer(producer);
    }

    @Description("Проверка быстрого поиска фильтра \"Объем\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("947")
    @Link(name = "Link to video", value = "checkVolumeInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"0.2"})
    public void checkVolumeInput_KSN(String volumeValue) {
        filtersBase.openFilterAndSetInputValue(Filters.VOLUME_NAME, volumeValue).checkFastSearchResult(volumeValue);
    }

    @Description("Проверка checkbox'ов фильтра \"Объём\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("948")
    @Link(name = "Link to video", value = "checkVolumeCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"1"})
    public void checkVolumeCheckBox_KSN(String volume) {
        filtersBase.openFilterAndSetCheckBox(Filters.VOLUME_NAME, volume)
                .clickApply().compareFilterVolume(volume);
    }

    @Description("Проверка фильтра \"Выдержка в ёмкости\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("949")
    @Link(name = "Link to video", value = "checkCapacityCheckBox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Нейтральная емкость"})
    void checkCapacityCheckBox_KSN(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.TANK_EXPOSURE_NAME, input)
                .clickApply().compareFilterCapacity(input);
    }

    @Description("Проверка фильтра \"Крепость\" в категории " + categoryName)
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("950")
    @Link(name = "Link to video", value = "checkStrengthInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"10, 12"})
    public void checkStrengthInput_KSN(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.FORTRESS_NAME, p1, p2)
                .clickApply().compareFilterStrength(p1, p2);
    }

    @Description("Проверка быстрого поиска фильтра \"Наличие в винотеке\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("")
    @Link(name = "Link to video", value = "checkMagazineInput_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"лени"})
    void checkMagazineInput_KSN(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.MAGAZINE_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Наличие в винотеки\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("954")
    @Link(name = "Link to video", value = "checkMagazineCheckbox_KSN", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Винотека & ресторан SimpleWine&Kitchen на Лесной"})
    void checkMagazineCheckbox_KSN(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.MAGAZINE_NAME, input)
                .clickApply().compareFilterMagazine(input);
    }
}
