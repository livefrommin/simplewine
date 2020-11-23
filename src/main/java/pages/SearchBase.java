package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.items.DigiSnippet;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchBase {

    final static Logger logger = LogManager.getLogger(FiltersBase.class);

    final String searchButton = ".page-header__button_search";
    final String digiSearchInput = ".digi-search-input";
    final String digiAutoComplete = "search__field digi-autocomplete jc-ignore";
    final String digiSearchApplyButton = ".digi-search-button";

    final String digiSnippetBlock = ".digi-product-item";

    @Step("Сравнение результатов поиска по Артиклу - {article}")
    public void compareSearchArticle(String article) {
        logger.info("Compare search result for article " + article);
        List<DigiSnippet> allDigiSnippets = getAllDigiSnippets();
        boolean result = allDigiSnippets.stream().allMatch(digi -> digi.getDigiArticle().contains(article));
        assertTrue(result);
    }

    @Step("Сравнение результатов для - {value}")
    public void compareSearchEqInName(String value) {
        logger.info("Compare search result for article " + value);
        List<DigiSnippet> allDigiSnippets = getAllDigiSnippets();
        boolean result = allDigiSnippets.stream().allMatch(digi -> digi.getDigiName().toLowerCase().contains(value.toLowerCase()));
        assertTrue(result);
    }

    @Step("Сравнение результатов для - {value}")
    public void compareSearchEqInInfo(String value) {
        logger.info("Compare search result for article " + value);
        List<DigiSnippet> allDigiSnippets = getAllDigiSnippets();
        boolean result = allDigiSnippets.stream().allMatch(digi -> digi.getDigiInfo().toLowerCase().contains(value.toLowerCase()));
        assertTrue(result);
    }

    @Step("Получаем все результаты поиска")
    public List<DigiSnippet> getAllDigiSnippets() {
        List<DigiSnippet> products = $$(digiSnippetBlock).shouldHave(CollectionCondition.sizeGreaterThan(0))
                .stream().map(DigiSnippet::new).collect(Collectors.toList());
        final String resultForLog = products.stream().map(DigiSnippet::toString).collect(Collectors.joining("\n"));
        logger.info("Founded digi snippets: \n" + resultForLog);
        return products;
    }

    @Step("Открыть окно поиска и установить значение {value}")
    public void openSearchAndSetValue(String value) {
        $(searchButton).shouldBe(Condition.visible).click();
        if ($(digiSearchInput).is(Condition.exist)){
        $(digiSearchInput).setValue(value);}
        else{$(digiAutoComplete).setValue(value);}
       // $(digiSearchInput).setValue(value);
        $(digiSearchApplyButton).pressEnter();
    }

}
