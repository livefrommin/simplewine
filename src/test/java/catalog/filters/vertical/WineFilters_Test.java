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
@Story("Вино")
public class WineFilters_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.WINE_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка фильтра \"Товар со скидкой\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("861")
    @Link(name = "Link to video", value = "checkDiscount_wine", type = "video")
    @Test
    public void checkDiscount_wine() {
        filtersBase.setSingleCheckBox(Filters.DISCOUNT_NAME)
                .clickApply().compareFilterDiscount();
    }

    @Description("Проверка фильтра \"Цвет\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("862")
    @Link(name = "Link to video", value = "checkColor_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Белое"})
    public void checkColor_wine(String color) {
        filtersBase.setCheckBox(Filters.COLOR_NAME, color)
                .clickApply().compareFilterColor(color);
    }

    @Description("Проверка placeholder'ов фильтра \"Цена\" в категории Вино ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("864")
    @Link(name = "Link to video", value = "checkPriceInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"1000, 1100"})
    public void checkPriceInput_wine(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.PRICE_NAME, p1, p2)
                .clickApply().compareFilterPrice(Menu.WINE_NAME, p1, p2);
    }

    @Description("Проверка checkbox'ов фильтра \"Цена\" в категории Вино ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("865")
    @Link(name = "Link to video", value = "checkPriceCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"До 700 ₽"})
    public void checkPriceCheckBox_wine(String price) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .clickApply().compareFilterPrice(price);
    }

    @Description("Проверка быстрого поиска фильтра \"Объем\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("897")
    @Link(name = "Link to video", value = "checkVolumeInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"0.7"})
    public void checkVolumeInput_wine(String volumeValue) {
        filtersBase.openFilterAndSetInputValue(Filters.VOLUME_NAME, volumeValue).checkFastSearchResult(volumeValue);
    }

    @Description("Проверка checkbox'ов фильтра \"Объём\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("866")
    @Link(name = "Link to video", value = "checkVolumeCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"0.375, Белое"})
    public void checkVolumeCheckBox_wine(String volume, String color) {
        filtersBase
                .openFilterAndSetCheckBox(Filters.COLOR_NAME, color)
                .openFilterAndSetCheckBox(Filters.VOLUME_NAME, volume)
                .clickApply().compareFilterVolume(volume);
    }

    @Description("Проверка быстрого поиска фильтра \"Страна\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("867")
    @Link(name = "Link to video", value = "checkCountryInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Ав"})
    public void checkCountryInput_wine(String country) {
        filtersBase.openFilterAndSetInputValue(Filters.COUNTRY_NAME, country).checkFastSearchResult(country);
    }

    @Description("Проверка checkbox'ов фильтра \"Страна\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("868")
    @Link(name = "Link to video", value = "checkCountryCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Белое, Австралия"})
    public void checkCountryCheckBox_wine(String color, String country) {
        filtersBase.openFilterAndSetCheckBox(Filters.COLOR_NAME, color)
                .openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .clickApply().compareFilterCountry(country);
    }

    @Description("Проверка быстрого поиска фильтра \"Регион\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("869")
    @Link(name = "Link to video", value = "checkRegionInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Ва"})
    public void checkRegionInput_wine(String region) {
        filtersBase.openFilterAndSetInputValue(Filters.REGION_NAME, region).checkFastSearchResult(region);
    }

    @Description("Проверка checkbox'ов фильтра \"Регион\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("870")
    @Link(name = "Link to video", value = "checkRegionCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Бордо"})
    public void checkRegionCheckBox_wine(String region) {
        filtersBase.openFilterAndSetCheckBox(Filters.REGION_NAME, region)
                .clickApply().compareFilterResultFromDesc(region);
    }

    @Description("Проверка быстрого поиска фильтра \"Аппелласьон\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("871")
    @Link(name = "Link to video", value = "checkAppInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Al"})
    public void checkAppInput_wine(String app) {
        filtersBase.openFilterAndSetInputValue(Filters.APPELASON_NAME, app).checkFastSearchResult(app);
    }

    @Description("Проверка checkbox'ов фильтра \"Аппелласьон\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("872")
    @Link(name = "Link to video", value = "checkAppCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Aconcagua Costa"})
    public void checkAppCheckBox_wine(String app) {
        filtersBase.openFilterAndSetCheckBox(Filters.APPELASON_NAME, app)
                .clickApply().compareFilterAppelason(app);
    }

    @Description("Проверка быстрого поиска фильтра \"Сорт винограда\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("873")
    @Link(name = "Link to video", value = "checkSortInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"аль"})
    public void checkSortInput_wine(String sort) {
        filtersBase.openFilterAndSetInputValue(Filters.GRAPE_NAME, sort).checkFastSearchResult(sort);
    }

    @Description("Проверка checkbox'ов фильтра \"Сорт винограда\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("874")
    @Link(name = "Link to video", value = "checkSortCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Алиберне (одесский черный)"})
    public void checkSortCheckBox_wine(String sort) {
        filtersBase.openFilterAndSetCheckBox(Filters.GRAPE_NAME, sort)
                .clickApply().compareFilterResultFromDesc(sort);
    }

    @Description("Проверка фильтра \"Содержание сахара\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("875")
    @Link(name = "Link to video", value = "checkSugarCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Красное, сладкое"})
    public void checkSugarCheckBox_wine(String color, String sugar) {
        filtersBase.openFilterAndSetCheckBox(Filters.COLOR_NAME, color)
                .openFilterAndSetCheckBox(Filters.SUGAR_NAME, sugar)
                .clickApply().compareFilterSugar(sugar);
    }

    @Description("Проверка фильтра \"Крепость\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("876")
    @Link(name = "Link to video", value = "checkStrengthInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"15, 17"})
    public void checkStrengthInput_wine(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.FORTRESS_NAME, p1, p2)
                .clickApply().compareFilterStrength(p1, p2);
    }

    @Description("Проверка быстрого поиска фильтра \"Производитель\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("877")
    @Link(name = "Link to video", value = "checkProducerInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"canti"})
    void checkProducerInput_wine(String producer) {
        filtersBase.openFilterAndSetInputValue(Filters.PRODUCER_NAME, producer).checkFastSearchResult(producer);
    }

    @Description("Проверка checkbox'ов фильтра \"Производитель\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("878")
    @Link(name = "Link to video", value = "checkProducerCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Canti"})
    void checkProducerCheckBox_wine(String producer) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRODUCER_NAME, producer)
                .clickApply().compareFilterProducer(producer);
    }

    @Description("Проверка фильтра \"Рейтинг RP\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("879")
    @Link(name = "Link to video", value = "checkRatingRP_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"90, 92"})
    void checkRatingRP_wine(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.RATING_RP_NAME, p1, p2)
                .clickApply().compareFilterRP(p1, p2);
    }

    @Description("Проверка фильтра \"Рейтинг WS\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("880")
    @Link(name = "Link to video", value = "checkRatingWS_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"99, 100"})
    void checkRatingWS_wine(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.RATING_WS_NAME, p1, p2)
                .clickApply().compareFilterWS(p1, p2);
    }

    @Description("Проверка фильтра \"Год\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("881")
    @Link(name = "Link to video", value = "checkYearInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"2001, 2003"})
    void checkYearInput_wine(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.YEAR_NAME, p1, p2)
                .clickApply().compareFilterYear(p1, p2);
    }

    @Description("Проверка фильтра \"Выдержка в ёмкости\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("883")
    @Link(name = "Link to video", value = "checkCapacityCheckBox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Нейтральная емкость"})
    void checkCapacityCheckBox_wine(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.TANK_EXPOSURE_NAME, input)
                .clickApply().compareFilterCapacity(input);
    }

    @Description("Проверка быстрого поиска фильтра \"Гастрономия\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("884")
    @Link(name = "Link to video", value = "checkGastronomyInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"ри"})
    void checkGastronomyInput_wine(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.GASTRONOMY_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Гастрономия\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("885")
    @Link(name = "Link to video", value = "checkGastronomyCheckbox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Паста"})
    void checkGastronomyCheckbox_wine(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.GASTRONOMY_NAME, input)
                .clickApply().compareFilterGastronomy(input);
    }

    @Description("Проверка быстрого поиска фильтра \"Наличие в винотеке\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("886")
    @Link(name = "Link to video", value = "checkMagazineInput_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"лени"})
    void checkMagazineInput_wine(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.MAGAZINE_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Гастрономия\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("885")
    @Link(name = "Link to video", value = "checkMagazineCheckbox_wine", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Винотека & ресторан SimpleWine&Kitchen на Лесной"})
    void checkMagazineCheckbox_wine(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.MAGAZINE_NAME, input)
                .clickApply().compareFilterMagazine(input);
    }

    @Description("Применение трех фильтров в каталоге Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("863")
    @Link(name = "Link to video", value = "checkMultiplyFilters_wine", type = "video")
    //@ParameterizedTest(name = "{displayName}")
    //@CsvSource({"0.5, Италия, 1 500–3 000 ₽"})
    void checkMultiplyFilters_wine(String volume, String country, String price) {
        filtersBase
                .openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .openFilterAndSetCheckBox(Filters.VOLUME_NAME, volume)
                .openFilterAndSetCheckBox(Filters.COUNTRY_NAME, country)
                .clickApply().compareMultiplyFilters();
    }
}
