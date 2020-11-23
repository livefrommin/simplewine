package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import constants.Menu;
import helpers.CollectionHelper;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.items.ProductCart;
import pages.items.ProductSnippet;
import pages.items.cards.ProductCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static helpers.SelenideHelper.click;
import static helpers.SelenideHelper.strollToElement;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogPage extends MainPage {

    final static Logger logger = LogManager.getLogger(FiltersBase.class);

    public static final List<ProductSnippet> productSnippetList = new ArrayList<>();

    final String flocktoryPath = ".flocktory-widget-overlay";
    final String flocktoryButtonNoPath = ".Notification-buttons > button";
    final String catalogPath = "div.catalog-items__block.js-catalog-items > div.catalog-grid > div.catalog-grid__item a";
    final String cartLinkPath = ".page-header__button";
    final String cartDropDownPath = ".cart-dropdown";
    final String clearAllButtonPath = ".cart-dropdown__remove-all";

    public CatalogPage() {
    }

    public static List<ProductSnippet> getProductSnippetList() {
        return productSnippetList;
    }

    @Step("Добавить произвольный товар в корзину ")
    public ProductSnippet addRandomCart() {
        SelenideElement element = CollectionHelper.getRandom($$(".catalog-grid > div.catalog-grid__item"));
        ProductSnippet selectedCart = new ProductSnippet(element);
        selectedCart.getAddButton().hover().click();
        return selectedCart;
    }

    /**
     * Метод ищет карточку товара с указанными параметрами
     *
     * @param price - мин\макс цена товара
     * @param sign  - знак сравнения. меньше (<) - -1, больше (>) - 2, равно (=) - 0
     * @return
     */
    @Step("Добавление товара с минимальной ценой {price} и знаком {sign}")
    public CatalogPage addRandomCartWithPrice(int price, int sign) {
        List<ProductSnippet> snippetList = getAllSnippets();
        ProductSnippet product = snippetList.stream()
                .filter(ps -> Integer.compare(ps.getProductPrice(), price) == sign)
                .findFirst().orElse(null);
        assertNotNull(product, "Невозможно получить снипет товара");
        logger.info("Cart added with price: " + product.getProductPrice() + "\n" + product.toString());
        strollToElement(product.getAddButton());
        click(product.getAddButton());
        return this;
    }

    @Step("Добавление карточек товаров в корзину")
    public CatalogPage addRandomCart(String catalogItem, int count) {
        chooseMenuItem(catalogItem);
        List<ProductSnippet> snippetList = getAllSnippets();
        IntStream.rangeClosed(1, count).forEach(snippet -> {
            ProductSnippet productSnippet = CollectionHelper.getRandom(snippetList);
          strollToElement(productSnippet.getAddButton());
           click(productSnippet.getAddButton());
            addItemToCart(productSnippet.getProductTitleName(), productSnippet.getArticle());
            productSnippetList.add(productSnippet);
        });
        logger.info("Product snippet list size: " + productSnippetList.size());
        return this;
    }

    @Step("Добавляем {cartName} в корзину")
    private void addItemToCart(String cartName, String article) {
        logger.info("Add " + cartName + " (" + article + ")" + " to cart.");
    }

    @Step("Выбрать пункт меню {itemName}")
    public CatalogPage chooseMenuItem(String itemName) {
        logger.info("Choose menu item " + itemName);
        if (itemName.equals(Menu.SIMPLE_COLLECTION_NAME)) {
            open(Menu.SIMPLE_COLLECTION_NAME_URL);
            return this;
        }
        $$("nav > div.navigation__row > ul > li > a").find(text(itemName)).click();
        return this;
    }

    /**
     * Отвечает за закрытие флоктори, данный механизм нужен только для отладки, на препроде данную функцию требуется отключить. Мы ее не тестирует.
     */
    public void closeFloctoryWidget() {
        try {
            $(flocktoryPath).shouldBe(visible);
            switchTo().frame($(".flocktory-widget"));
            $(flocktoryButtonNoPath).click();
            switchTo().defaultContent();
            logger.info("Flocktory  button exist. Click NO");
        } catch (ElementNotFound ex) {
            logger.info("Flocktory element not found.");
        }
    }

    @Step("Полный шаг добавления товара в корзину из каталога")
    public void fullCheckOutStep(String catalogName, CartPage cartPage) {
        logger.info("Add card to cart and click checkout");
        addRandomCart(catalogName, 5);
        openCart();
        cartPage.clickCheckout();
    }

    public List<SelenideElement> getFirstAndLastSnippet() {
        List<SelenideElement> filtered = new ArrayList<>();
        ElementsCollection collections = $$(catalogPath);
        filtered.add(collections.first());
        filtered.add(collections.last());
        return filtered;
    }

    @Step("Получение всех отображаемых сниппетов")
    public List<ProductSnippet> getAllSnippets() {
        logger.info("Get all snippets from page.");
        List<ProductSnippet> allSnippets = $$("div.catalog-items__block.js-catalog-items > div.catalog-grid > div.catalog-grid__item").shouldHave(CollectionCondition.sizeGreaterThan(0))
                .stream().map(ProductSnippet::new).collect(Collectors.toList());
        final String resultForLog = allSnippets.stream().map(ProductSnippet::toString).collect(Collectors.joining("\n"));
        logger.info("Founded snippets: \n" + resultForLog);
        return allSnippets;
    }

    @Step("Добавление товара из карточки товара")
    public void addToCartAndCompareResult(CartPage cartPage) {
        SelenideElement element = CollectionHelper.getRandom($$(catalogPath).shouldHave(CollectionCondition.sizeGreaterThan(0)));
        click(element);
        ProductCard card = new ProductCard();
        click(card.getAddToCartButton());
        openCart();
        ProductCart productCart = cartPage.getAllProductSection().get(0);
        logger.info("Card: " + card.toString());
        logger.info("productCard: " + productCart.toString());
        assertAll("product",
                () -> assertEquals(card.getCardArticle(), productCart.getProductArticle()),
                () -> assertTrue(card.getCardName().contains(productCart.getProductName())),
                () -> assertEquals(card.getCardPrice(), productCart.getProductPrice())
        );
    }

    @Step("Добавляем товаров из каталога и открываем корзину.")
    public CatalogPage addToCartAndOpenCart(int count) {
        addRandomCart(Menu.WINE_NAME, count);
        openCart();
        return this;
    }
}
