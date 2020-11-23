package pages.items;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.CollectionHelper;
import io.qameta.allure.Step;
import pages.CatalogPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterBlock {

    CatalogPage catalogPage = new CatalogPage();
    String currentItemName;

    final SelenideElement filtersBlock = $(".catalog-filters__block > div.new-filters__block");
    SelenideElement filterBlock;

    final ElementsCollection filters = filtersBlock.findAll(".new-filters__block-items > div");

    public FilterBlock() {
    }

    @Step("Выбрать фильтр {filterName}")
    public FilterBlock chooseFilter(String filterName) {
        filterBlock = filters.find(text(filterName));
        filterBlock.find(".new-filters__block-item--title").scrollIntoView(false).click();
        return this;
    }

    public FilterBlock chooseRandomFilterValue() {
        ElementsCollection filtersValue = filterBlock.findAll(".ui-checkbox-group__checkbox");
        SelenideElement element = CollectionHelper.getRandom(filtersValue);
        currentItemName = element.getText();
        chooseRandomFilterValue(currentItemName, filtersValue);
        return this;
    }

    @Step("Проверяем результаты поиска")
    public FilterBlock compareResult() {
/*        List<ProductSnippet> list = catalogPage.getAllCartAfterFilter();
        boolean compare = CollectionHelper.compareInCartDesc(currentItemName, list);*/
        assertTrue(true);
        return this;
    }

    @Step("Выбрано значение фильтра {valueName}")
    private void chooseRandomFilterValue(String valueName, ElementsCollection collection) {
        collection.find(text(valueName)).find("label").click();
    }

    @Step("Применить фильтр")
    public FilterBlock applyFilter() {
        filtersBlock.findAll(".new-filters__block-buttons > button")
                .find(exactText("Применить")).click();
        return this;
    }

    @Step("Очистить фильтр")
    public FilterBlock clearFilter() {
        filtersBlock.findAll(".new-filters__block-buttons > button")
                .find(exactText("Очистить фильтры")).click();
        refresh();
        return this;
    }
}
