package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.SelenideHelper;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.items.ProductSnippet;
import pages.items.cards.ProductCard;
import utils.IntegersUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static helpers.SelenideHelper.click;
import static helpers.SelenideHelper.compareUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltersBase {

    final static Logger logger = LogManager.getLogger(FiltersBase.class);
    public static String value;

    final String mainBlockFilter = ".new-filters__block-items";
    final String singleItem = "div.catalog-filters__block.js-catalog-filters > div > div.new-filters__block-items > div:nth-child({}) > div > label";
    final String blockItem = "div.catalog-filters__block.js-catalog-filters > div > div.new-filters__block-items > div.new-filters__block-item > div.new-filters__block-item--title";

    String checkBoxBlock = " > div.ui-tag-checkbox__content > div > div > div > div.ui-checkbox-group__container";
    final String checkBoxItem = ".ui-checkbox-group__checkbox";
    final String applyButton = "div.new-filters__block-buttons > button:nth-child(1)";
    final String clearButton = "div.new-filters__block-buttons > button:nth-child(2)";

    final String inputWrapper = ".ui-checkbox-group__inputs-wrapper > input";
    final String inputFastSearch = ".ui-input__box > input";

    String filtersOverlay = ".new-filters__overlay";
    final String filterMinBlock = "div.catalog-info__filters-items";
    final String filtersMinItems = "div.catalog-info__filters-items > div.catalog-info__filters-item";
    final String deleteMinButtonFilter = ".js-clear-filter";

    final HashMap<String, String> containerOfAddedFilters = new HashMap<>();
    final CatalogPage catalogPage = new CatalogPage();
    SelenideElement currentFilter;

    public FiltersBase() {
    }

    /***
     * Открыть фильтр, вставить значения. Фильтр: два инпута
     * @param filter - название фильтра
     */
    public FiltersBase openFilterAndSetInputWrapValue(String filter, int p1, int p2) {
        logger.info("Open filter " + filter + " and set input wrap values: " + p1 + " " + p2);
        SelenideElement mainPath = openFilterBlock(filter);
        setInputWrapperValue(mainPath, String.valueOf(p1), String.valueOf(p2));
        return this;
    }

    /**
     * Открыть фильтр, проставить значение.
     *
     * @param filter - название фильтра
     * @param value  - значение фильтра
     */
    @Step("Открыть фильтр {filter} и установить значение {value}")
    public FiltersBase openFilterAndSetCheckBox(String filter, String value) {
        logger.info("Open filter " + filter + " and set checkbox value: " + value);
        containerOfAddedFilters.put(filter, value);
        SelenideElement mainPath = openFilterBlock(filter);
        setCheckBoxInFilter(mainPath, value);
        return this;
    }

    @Step("Установить значение активного фильтра {value}")
    public FiltersBase setCheckBox(String filter, String value) {
        logger.info("Set checkbox value: " + value);
        containerOfAddedFilters.put(filter, value);
        SelenideElement mainPath = openActiveFilterBlock(filter);
        setCheckBoxInFilter(mainPath, value);
        return this;
    }

    /**
     * Открывать фильтр, найти быстрый поиск, установить значение.
     *
     * @param filter - название фильтра
     * @param value  - значение
     */
    @Step("Открыть фильтр {filter} и установить значение {value}")
    public FiltersBase openFilterAndSetInputValue(String filter, String value) {
        logger.info("Open filter " + filter + " and set fast search value: " + value);
        containerOfAddedFilters.put(filter, value);
        SelenideElement mainPath = openFilterBlock(filter);
        currentFilter = mainPath;
        setInputFastSearchValue(mainPath, value);
        return this;
    }

    @Step("Проверка фильтра товары со скидкой")
    public void compareFilterDiscount() {
        logger.info("Compare filter discount.");
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream()
                .filter(ps -> ps.getProductPriceDiscount() != null).count() == list.size();
        assertTrue(result, "Не все товары имеют скидку или признак скидки");
    }

    @Step("Проверяем вхождение товара в диапозон цен {firstParameter} - {secondParameter}")
    public void compareFilterPrice(String name, int firstParameter, int secondParameter) {
        logger.info("Compare price filter with parameters: " + firstParameter + " " + secondParameter);
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream().filter(ps -> ps.getProductPrice() >= firstParameter && ps.getProductPrice() <= secondParameter).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем вхождение товара в диапозон цен (checkbox) {value}")
    public void compareFilterPrice(String value) {
        logger.info("Compare price filter with parameters:" + value);
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        List<Integer> interval = IntegersUtils.getPriceInterval(value);
        boolean result = list.stream().filter(ps -> ps.getProductPrice() >= interval.get(0) && ps.getProductPrice() <= interval.get(1)).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем результат для значения {value}")
    public void compareFilterValue(String value) {
          List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            logger.info("Карточка товара: \n" + card.toString());
            assertTrue(card.findInProduct(value));
            back();
        });
    }

    @Step("Проверяем результат для цвета {color}")
    public void compareFilterColor(String color) {
        logger.info("Compare color filter with value: " + color);
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream().filter(ps -> ps.getProductDesc().contains(color.toLowerCase())).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем результат для объем {volume}")
    public void compareFilterVolume(String volume) {
        logger.info("Compare volume filter with value: " + volume);
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream().filter(ps -> ps.getProductDesc().contains(volume)).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем результат для страны {country}")
    public void compareFilterCountry(String country) {
        logger.info("Compare country filter with value: " + country);
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream().filter(ps -> ps.getProductDesc().contains(country)).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем вхождение значения {value}")
    public void compareFilterResultFromDesc(String value) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            logger.info("Карточка товара: \n" + card.toString());
            assertTrue(card.findInProductDesc(value));
            back();
        });
    }

    @Step("Проверяем результат для региона {country}")
    public void compareFilterAppelason(String appelason) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertEquals(card.getCardAppelason(), appelason);
            back();
        });
    }

    @Step("Проверяем результат для фильтра содержание сахара {sugar}")
    public void compareFilterSugar(String sugar) {
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream().filter(ps -> ps.getProductDesc().contains(sugar.toLowerCase())).count() == list.size();
        assertTrue(result);
    }

    @Step("Проверяем вхождение товара в диапозон крепости {firstParameter} - {secondParameter}")
    public void compareFilterStrength(int firstParameter, int secondParameter) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardStrength() >= firstParameter && card.getCardStrength() <= secondParameter);
            back();
        });
    }

    @Step("Проверяем вхождение товара в диапозон рейтингPR {firstParameter} - {secondParameter}")
    public void compareFilterRP(int firstParameter, int secondParameter) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardRatingRP() >= firstParameter && card.getCardRatingRP() <= secondParameter);
            back();
        });
    }

    @Step("Проверяем вхождение товара в диапозон рейтингWS {firstParameter} - {secondParameter}")
    public void compareFilterWS(int firstParameter, int secondParameter) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardRatingWS() >= firstParameter && card.getCardRatingWS() <= secondParameter);
            back();
        });
    }

    @Step("Проверяем вхождение товара в диапозон крепости {firstParameter} - {secondParameter}")
    public void compareFilterYear(int firstParameter, int secondParameter) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            System.out.println("getCardYear(): " + card.getCardYear() + "  input: " + firstParameter + " // " + secondParameter);
            assertTrue(card.getCardYear() >= firstParameter && card.getCardYear() <= secondParameter);
            back();
        });
    }

    @Step("Проверяем результат для выдержка в емкости {producer}")
    public void compareFilterProducer(String producer) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardProducer().contains(producer));
            back();
        });
    }

    @Step("Проверяем результат для производитель {capacity}")
    public void compareFilterCapacity(String capacity) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardCapacity().contains(capacity));
            back();
        });
    }

    @Step("Проверяем результат для гастрономии {serving}")
    public void compareFilterGastronomy(String serving) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardServing().stream().anyMatch(s -> s.contains(serving)));
            back();
        });
    }

    @Step("Проверяем результат для наличие в винотеке {magazine}")
    public void compareFilterMagazine(String magazine) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            logger.info("Input item to search: " + magazine);
            logger.info("Created card: " + "\n" + card.toString());
            assertTrue(card.getCardMagazines().stream().anyMatch(s -> s.contains(magazine)));
            back();
        });
    }

    @Step("Проверяем результат фильтра {type}")
    public void compareFilterType(String type) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            logger.info("Input item to search: " + type);
            logger.info("Created card: " + "\n" + card.toString());
            assertTrue(card.getCardCode().toLowerCase().contains(type.toLowerCase()));
            back();
        });
    }

    @Step("Проверяем результат для метод производства {method}")
    public void compareFilterProducerMethod(String method) {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            assertTrue(card.getCardProductionMethod().contains(method));
            back();
        });
    }

    @Step("Проверяем результат вхождения {value}")
    public void compareFilterEqInTitle(String value) {
        logger.info("Compare filter with value: " + value);
        List<ProductSnippet> list = catalogPage.getAllSnippets();
        boolean result = list.stream().filter(ps -> ps.getProductName().toLowerCase().contains(value.toLowerCase())).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем результат вхождения {value}")
    public void compareFilterEqInName(String value) {
        logger.info("Compare filter with value: " + value);
        List<ProductSnippet> list = catalogPage.getAllSnippets();

        System.out.println("Value:" + value);
        list.stream().filter(ps -> ps.getProductName().toLowerCase()
                .contains(value.toLowerCase())).forEach(e -> System.out.println(e.getProductName()));

        boolean result = list.stream().filter(ps -> ps.getProductName().toLowerCase().contains(value.toLowerCase())).count() == list.size();
        logger.info("Compare result: " + result);
        assertTrue(result);
    }

    @Step("Проверяем результат по добавленным ранее фильтрам")
    public void compareMultiplyFilters() {
        List<SelenideElement> elements = catalogPage.getFirstAndLastSnippet();
        elements.forEach(e -> {
            click(e);
            ProductCard card = new ProductCard();
            back();
        });
    }

    /**
     * Справниваем результаты быстрого поиска.
     *
     * @param value - значение фильтра
     */
    public void checkFastSearchResult(String value) {
        ElementsCollection checkboxes = currentFilter.findAll(checkBoxItem);
        StringBuilder resultForLog = new StringBuilder();
        resultForLog.append("Founded checkboxes: ");
        checkboxes.forEach(e -> resultForLog.append(e.getText()).append(" | "));
        logger.info(resultForLog);
        boolean result = checkboxes.stream().allMatch(e -> e.getText().toLowerCase().contains(value.toLowerCase()));
        logger.info("Fast search result: " + result);
        assertTrue(result);
    }

    /**
     * Установить значение чекбокс в одиночном фильтре.
     *
     * @param filterName - нащвание фильтра
     */
    public FiltersBase setSingleCheckBox(String filterName) {
        SelenideElement element = $$(".ui-tag-checkbox__content")
                .find(Condition.exactTextCaseSensitive(filterName));
        click(element);
        return this;
    }

    @Step("Выбираем значение фильтра {value}")
    public void setCheckBoxInFilter(SelenideElement inputPath, String value) {
        logger.info("Set checkbox value: " + value);
        SelenideElement element = $$(inputPath.findAll(checkBoxItem)).find(Condition.exactText(value));
        click(element);
    }

    @Step("Устанавливаем значения в input {first}, {second}")
    public void setInputWrapperValue(SelenideElement inputPath, String first, String second) {
        logger.info("Set input wrapper value: " + first + " " + second);
        ElementsCollection inputs = inputPath.findAll(inputWrapper);
        inputs.first().setValue(first);
        inputs.last().setValue(second).pressEnter().isEnabled();
    }

    public void setInputFastSearchValue(SelenideElement inputPath, String value) {
        inputPath.find(inputFastSearch).shouldBe(Condition.exist.because("Отсутствует блок быстрого поиска.")).setValue(value);
    }

    public SelenideElement openFilterBlock(String name) {
        SelenideElement element = $$(blockItem).filter(Condition.exactTextCaseSensitive(name)).first().parent();
       // boolean active = element.is(Condition.not(Condition.cssClass("active")));
        //if(active)
            click(element);
        return element;
    }

    public SelenideElement openActiveFilterBlock(String name) {
        SelenideElement element = $$(blockItem).filter(Condition.exactTextCaseSensitive(name)).first().parent();
         boolean active = element.is(Condition.not(Condition.cssClass("active")));
        if(active)
        click(element);
        return element;
    }

    @Step("Сравнение выбранных и отображаемых фильтров")
    public FiltersBase compareSelectedMinFilters() {
        logger.info("Compare selected filter and filter in top catalog");
        List<String> minFiltersList = $$(filtersMinItems).shouldHave(CollectionCondition.sizeGreaterThan(0))
                .stream().map(e -> e.getText().toLowerCase()).collect(Collectors.toList());
        List<String> listFromBuf = new ArrayList<>(containerOfAddedFilters.values())
                .stream().map(String::toLowerCase).collect(Collectors.toList());
        logger.info("Found filters: " + minFiltersList);
        Collections.sort(minFiltersList);
        Collections.sort(listFromBuf);
        assertEquals(minFiltersList, listFromBuf, "Выбранные и отображаемые фильтры не совпали.");
        return this;
    }

    @Step("Удаление отображаемых фильтров")
    public void deleteAllMinFilters() {
        $$(filtersMinItems).forEach(e -> click(e.find(deleteMinButtonFilter)));
        assertTrue($(filterMinBlock).getText().isEmpty());
    }

    @Step("Нажать кнопку Применить в блоке фильтра")
    public FiltersBase clickApply() {
        logger.info("Click apply in filters.");
        click($(applyButton));
        compareUrl(url());
        return this;
    }

    @Step("Проверяем отсутствие активных фильтров")
    public void checkMinFilterIsClear() {
        logger.info("Check all filter is clear.");
        ElementsCollection checked = $(mainBlockFilter).findAll(".ui-checkbox__control").filter(Condition.checked);
        assertEquals(checked.size(), 0, "После очистки фильтров, остались активные чекбоксы.");
    }

    @Step("Нажать кнопку Очистить фильтры в блоке фильтра")
    public FiltersBase clickClear() {
        logger.info("Click clear in filters.");
        click(clearButton);
        compareUrl(url());
        return this;
    }
}
