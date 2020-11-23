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
@Story("Стекло")
public class GlassFilters_Test extends BaseTest {

    FiltersBase filtersBase;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.GLASS_NAME);
        filtersBase = new FiltersBase();
    }

    @Description("Проверка фильтра \"Товар со скидкой\" в категории Вино")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("861")
    @Link(name = "Link to video", value = "checkDiscount_wine", type = "video")
    //@Test
    public void checkDiscount_glass() {
        //нет сравнения результатов, т.к. не настроены скидки
        filtersBase.setSingleCheckBox(Filters.DISCOUNT_NAME).clickApply();
    }

    @Description("Проверка placeholder'ов фильтра \"Цена\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("909")
    @Link(name = "Link to video", value = "checkPriceInput_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"850, 900"})
    public void checkPriceInput_glass(int p1, int p2) {
        filtersBase.openFilterAndSetInputWrapValue(Filters.PRICE_NAME, p1, p2)
                .clickApply().compareFilterPrice(Menu.WINE_NAME, p1, p2);
    }

    @Description("Проверка checkbox'ов фильтра \"Цена\" в категории Вино ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("910")
    @Link(name = "Link to video", value = "checkPriceCheckBox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"До 700 ₽"})
    public void checkPriceCheckBox_glass(String price) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRICE_NAME, price)
                .clickApply().compareFilterPrice(price);
    }

    @Description("Проверка быстрого поиска фильтра \"Бренд\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("911")
    @Link(name = "Link to video", value = "checkBrandInput_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Sim"})
    public void checkBrandInput_glass(String brand) {
        filtersBase.openFilterAndSetInputValue(Filters.BRAND_NAME, brand).checkFastSearchResult(brand);
    }

    @Description("Проверка checkbox'ов фильтра \"Бренд\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("912")
    @Link(name = "Link to video", value = "checkBrandCheckBox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Simple"})
    public void checkBrandCheckBox_glass(String brand) {
        filtersBase.openFilterAndSetCheckBox(Filters.BRAND_NAME, brand)
                .clickApply().compareFilterProducer(brand);
    }

    @Description("Проверка быстрого поиска фильтра \"Серия\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("913")
    @Link(name = "Link to video", value = "checkSeriesInput_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"аше"})
    public void checkSeriesInput_glass(String brand) {
        filtersBase.openFilterAndSetInputValue(Filters.SERIES_NAME, brand).checkFastSearchResult(brand);
    }

    @Description("Проверка checkbox'ов фильтра \"Серия\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("914")
    @Link(name = "Link to video", value = "checkSeriesCheckBox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Ашер"})
    public void checkSeriesCheckBox_glass(String brand) {
        filtersBase.openFilterAndSetCheckBox(Filters.SERIES_NAME, brand)
                .clickApply().compareFilterType(brand);
    }

    @Description("Проверка быстрого поиска фильтра \"Страна\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("915")
    @Link(name = "Link to video", value = "checkCountryInput_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Ав"})
    public void checkCountryInput_glass(String country) {
        filtersBase.openFilterAndSetInputValue(Filters.COUNTRY_NAME, country).checkFastSearchResult(country);
    }

    @Description("Проверка фильтра \"Материал\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("917")
    @Link(name = "Link to video", value = "checkMaterialCheckBox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Хрусталь"})
    public void checkMaterialCheckBox_glass(String material) {
        filtersBase.openFilterAndSetCheckBox(Filters.MATERIAL_NAME, material)
                .clickApply().compareFilterResultFromDesc(material);
    }

    @Description("Проверка фильтра \"Метод производства\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("918")
    @Link(name = "Link to video", value = "checkMethodCheckBox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Машинная монолитная работа"})
    public void checkMethodCheckBox_glass(String method) {
        filtersBase.openFilterAndSetCheckBox(Filters.PRODUCTION_METHOD_NAME, method)
                .clickApply().compareFilterProducerMethod(method);
    }

    @Description("Проверка быстрого поиска фильтра \"Объем\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("919")
    @Link(name = "Link to video", value = "checkVolumeInput_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"0.7"})
    public void checkVolumeInput_glass(String volumeValue) {
        filtersBase.openFilterAndSetInputValue(Filters.VOLUME_NAME, volumeValue).checkFastSearchResult(volumeValue);
    }

    @Description("Проверка checkbox'ов фильтра \"Объём\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("920")
    @Link(name = "Link to video", value = "checkVolumeCheckBox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"0.75"})
    public void checkVolumeCheckBox_glass(String volume) {
        filtersBase.openFilterAndSetCheckBox(Filters.VOLUME_NAME, volume)
                .clickApply().compareFilterVolume(volume);
    }

    @Description("Проверка быстрого поиска фильтра \"Наличие в винотеке\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("921")
    @Link(name = "Link to video", value = "checkMagazineInput_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"лен"})
    void checkMagazineInput_glass(String input) {
        filtersBase.openFilterAndSetInputValue(Filters.MAGAZINE_NAME, input).checkFastSearchResult(input);
    }

    @Description("Проверка checkbox'ов фильтра \"Гастрономия\" в категории Стекло")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("922")
    @Link(name = "Link to video", value = "checkMagazineCheckbox_glass", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Винотека & ресторан GRAND CRU by Adrian Quetglas"})
    void checkMagazineCheckbox_glass(String input) {
        filtersBase.openFilterAndSetCheckBox(Filters.MAGAZINE_NAME, input)
                .clickApply().compareFilterMagazine(input);
    }
}
