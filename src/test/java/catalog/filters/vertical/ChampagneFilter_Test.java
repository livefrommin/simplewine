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
@Story("Шампанское и игристое")
public class ChampagneFilter_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.CHAMPAGNE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка фильтра \"Товар со скидкой\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("957")
    @Link(name = "Link to video", value = "checkDiscount_wine", type = "video")
    @Test
    public void checkDiscount_champagne() {
        filtersBase.setSingleCheckBox(Filters.DISCOUNT_NAME).clickApply().compareFilterDiscount();
    }

    @Description("Проверка placeholder'ов фильтра \"Цена\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("958")
    @Link(name = "Link to video", value = "checkPriceInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"950, 1000"})
    public void checkPriceInput_champagne(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.PRICE_NAME, p1, p2)
                .clickApply().compareFilterPrice(Menu.CHAMPAGNE_NAME, p1, p2);
    }

    @Description("Проверка checkbox'ов фильтра \"Цена\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("959")
    @Link(name = "Link to video", value = "checkPriceCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"До 700 ₽"})
    public void checkPriceCheckBox_champagne(String price) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .clickApply().compareFilterPrice(price);
    }

    @Description("Проверка фильтра \"Цвет\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("960")
    @Link(name = "Link to video", value = "checkColor_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Белое"})
    public void checkColor_champagne(String color) {
        filtersBase.openFilterAndSetCheckBox(Filters.COLOR_NAME, color)
                .clickApply().compareFilterColor(color);
    }

    @Description("Проверка быстрого поиска фильтра \"Объем\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("")
    @Link(name = "Link to video", value = "checkVolumeInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"0.7"})
    public void checkVolumeInput_champagne(String volumeValue) {
        filtersBase.openFilterAndSetInputValue(Filters.VOLUME_NAME, volumeValue).checkFastSearchResult(volumeValue);
    }

    @Description("Проверка checkbox'ов фильтра \"Объём\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("961")
    @Link(name = "Link to video", value = "checkVolumeCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"6"})
    public void checkVolumeCheckBox_champagne(String volume) {
        filtersBase.openFilterAndSetCheckBox(Filters.VOLUME_NAME, volume)
                .clickApply().compareFilterVolume(volume);
    }

    @Description("Проверка checkbox'ов фильтра \"Содержание сахара\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("962")
    @Link(name = "Link to video", value = "checkSugarCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"сладкое"})
    public void checkSugarCheckBox_champagne(String sugar) {
        filtersBase.openFilterAndSetCheckBox(Filters.SUGAR_NAME, sugar)
                .clickApply().compareFilterSugar(sugar);
    }

    @Description("Проверка быстрого поиска фильтра \"Страна\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("963")
    @Link(name = "Link to video", value = "checkCountryInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"итал"})
    public void checkCountryInput_champagne(String country) {
        filtersBase.openFilterAndSetInputValue(Filters.COUNTRY_NAME, country).checkFastSearchResult(country);
    }

    @Description("Проверка checkbox'ов фильтра \"Страна\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("868")
    @Link(name = "Link to video", value = "checkCountryCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Франция"})
    public void checkCountryCheckBox_champagne(String country) {
        filtersBase.openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .clickApply()
                .compareFilterCountry(country);
    }

    @Description("Проверка быстрого поиска фильтра \"Регион\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("965")
    @Link(name = "Link to video", value = "checkRegionInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"шампа"})
    public void checkRegionInput_champagne(String region) {
        filtersBase.openFilterAndSetInputValue(Filters.REGION_NAME, region).checkFastSearchResult(region);
    }

    @Description("Проверка checkbox'ов фильтра \"Регион\" в категории Шампанское и игристое")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("966")
    @Link(name = "Link to video", value = "checkRegionCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Долина Луары"})
    public void checkRegionCheckBox_champagne(String region) {
        filtersBase.openFilterAndSetCheckBox(Filters.REGION_NAME, region)
                .clickApply().compareFilterResultFromDesc(region);
    }

    @Description("Проверка быстрого поиска фильтра \"Аппелласьон\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("967")
    @Link(name = "Link to video", value = "checkAppInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"pros"})
    public void checkAppInput_champagne(String app) {
        filtersBase.openFilterAndSetInputValue(Filters.APPELASON_NAME, app).checkFastSearchResult(app);
    }

    @Description("Проверка checkbox'ов фильтра \"Аппелласьон\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("968")
    @Link(name = "Link to video", value = "checkAppCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Colli Trevigiani IGT"})
    public void checkAppCheckBox_champagne(String app) {
        filtersBase.openFilterAndSetCheckBox(Filters.APPELASON_NAME, app)
                .clickApply().compareFilterAppelason(app);
    }

    @Description("Проверка быстрого поиска фильтра \"Сорт винограда\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("969")
    @Link(name = "Link to video", value = "checkSortInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"шард"})
    public void checkSortInput_champagne(String sort) {
        filtersBase.openFilterAndSetInputValue(Filters.APPELASON_NAME, sort).checkFastSearchResult(sort);
    }

    @Description("Проверка checkbox'ов фильтра \"Сорт винограда\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("970")
    @Link(name = "Link to video", value = "checkSortCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Ансоника (инзолия)"})
    public void checkSortCheckBox_champagne(String sort) {
        filtersBase.openFilterAndSetCheckBox(Filters.GRAPE_NAME, sort)
                .clickApply().compareFilterResultFromDesc(sort);
    }

    @Description("Проверка быстрого поиска фильтра \"Производитель\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("971")
    @Link(name = "Link to video", value = "checkProducerInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"geoff"})
    void checkProducerInput_champagne(String producer) {
        filtersBase.openFilterAndSetInputValue(Filters.PRODUCER_NAME, producer).checkFastSearchResult(producer);
    }

    @Description("Проверка checkbox'ов фильтра \"Производитель\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("972")
    @Link(name = "Link to video", value = "checkProducerCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Andre Delorme"})
    void checkProducerCheckBox_champagne(String producer) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRODUCER_NAME, producer)
                .clickApply().compareFilterProducer(producer);
    }

    @Description("Проверка фильтра \"Рейтинг RP\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("974")
    @Link(name = "Link to video", value = "checkRatingRP_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"90, 92"})
    void checkRatingRP_champagne(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.RATING_RP_NAME, p1, p2)
                .clickApply().compareFilterRP(p1, p2);
    }

    @Description("Проверка фильтра \"Рейтинг WS\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("975")
    @Link(name = "Link to video", value = "checkRatingWS_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"87, 90"})
    void checkRatingWS_champagne(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.RATING_WS_NAME, p1, p2)
                .clickApply().compareFilterWS(p1, p2);
    }

    @Description("Проверка фильтра \"Год\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("976")
    @Link(name = "Link to video", value = "checkYearInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"2002, 2003"})
    void checkYearInput_champagne(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.YEAR_NAME, p1, p2)
                .clickApply().compareFilterYear(p1, p2);
    }

    @Description("Проверка фильтра \"Выдержка в ёмкости\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("978")
    @Link(name = "Link to video", value = "checkCapacityCheckBox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Нет"})
    void checkCapacityCheckBox_champagne(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.TANK_EXPOSURE_NAME, input)
                .clickApply().compareFilterCapacity(input);
    }

    @Description("Проверка быстрого поиска фильтра \"Гастрономия\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("")
    @Link(name = "Link to video", value = "checkGastronomyInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"говя"})
    void checkGastronomyInput_champagne(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.GASTRONOMY_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Гастрономия\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("979")
    @Link(name = "Link to video", value = "checkGastronomyCheckbox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Курица"})
    void checkGastronomyCheckbox_champagne(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.GASTRONOMY_NAME, input)
                .clickApply().compareFilterGastronomy(input);
    }

    @Description("Проверка быстрого поиска фильтра \"Наличие в винотеке\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("980")
    @Link(name = "Link to video", value = "checkMagazineInput_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"grand"})
    void checkMagazineInput_champagne(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.MAGAZINE_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Гастрономия\" в категории Шампанское")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("981")
    @Link(name = "Link to video", value = "checkMagazineCheckbox_champagne", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Винотека & ресторан SimpleWine&Kitchen на Лесной"})
    void checkMagazineCheckbox_champagne(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.MAGAZINE_NAME, input)
                .clickApply().compareFilterMagazine(input);
    }
}
