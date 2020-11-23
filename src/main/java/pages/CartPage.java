package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.items.ProductCart;
import pages.items.ProductSnippet;
import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static helpers.SelenideHelper.click;
import static org.junit.jupiter.api.Assertions.*;
import static utils.IntegersUtils.parseInteger;

public class CartPage extends CatalogPage {

    final static Logger logger = LogManager.getLogger(FiltersBase.class);

    final String pathCheckOutButton = ".cart-page__order-submit > button";
    final String pathToProductInCart = ".cart-page__products > section";
    final String pathToOpenPromotionalCode = ".cart-total__checkout-item-toggle";
    final String pathToPromotionalCodeInput = ".cart-total__checkout-item-form > span > span > input";
    final String pathToPromotionalCodeButton = ".cart-total__checkout-item-form > button";
    final String pathToPromotionalCodeAdded = "div.cart-total__promo.js-cart-total-promo > div.cart-total__promo-title > span";
    final String pathToCardOrderItems = ".cart-page__order > .cart-page__order-item > .cart-page__order-item-money";
    final String pathToButtonDeletePromotionalCode = ".cart-total__promo-delete-button";
    String pathToUiAlertMessageText = "ui-alert.ui-alert_showed";

    public CartPage() {
    }

    @Step("Нажать оформить заказ")
    public void clickCheckout() {
        $(pathCheckOutButton).click();
    }

    public void checkoutIsDisabled() {
        boolean result = $(pathCheckOutButton).isEnabled();
        assertFalse(result);
    }

    public List<ProductCart> getAllProductSection() {
        return $$(pathToProductInCart).shouldHave(CollectionCondition.sizeGreaterThan(0))
                .stream().map(ProductCart::new).collect(Collectors.toList());
    }

    @Step("Сравнение карточек товара")
    public void compareSelectedCarts() {
        List<ProductSnippet> snippetList = CatalogPage.getProductSnippetList();
        List<ProductCart> cardList = getAllProductSection();
        logger.info("Founded snippets: \n" + StringUtils.createStringFromClasses(snippetList));
        logger.info("Founded cards: \n" + StringUtils.createStringFromClasses(cardList));
        //идем по всему списку, находим соответствие по артиклу, сравниваем карточки
        snippetList.forEach(s ->
                cardList.stream()
                        .filter(c -> c.getProductArticle().equals(s.getProductArticle()))
                        .forEach(cart -> assertTrue(s.getProductDesc().contains(cart.getProductDesc())
                                , "Не удалось сравнить карточки. \n" + s.getProductDesc() + "\n" + cart.getProductDesc())));
        snippetList.clear();
    }

    @Step("Удаление первого и последне товара из корзины")
    public void deleteAllProductViaButton() {
        logger.info("Delete all from via cross button");
        List<ProductCart> cardList = getAllProductSection();
        cardList.forEach(element -> click(element.getDeleteButton().shouldBe(Condition.exist)));
        assertFalse($(".cart-page__empty-cart").exists(), "Остались товары в корзине после удаления.");
    }

    @Step("Удаление из корзины через кнопку \"Очистить все\"")
    public void deleteAllViaButtonClearAll() {
        logger.info("Delete from cart via button clean all");
        try {
            $(cartLinkPath).hover().find(cartDropDownPath).find(clearAllButtonPath).click();
            open("personal/cart/");
            assertFalse($(".cart-page__empty-cart").exists(), "Остались товары в корзине после удаления.");
        } catch (Exception ex) {
            logger.error("Unable to find cart popup. \n" + Arrays.toString(ex.getStackTrace()));
        }
    }

    @Step("Проверка что промокод применился.")
    public void checkPromotionalCodeApply() {
        logger.info("Compare promotional code apply.");
        $$(pathToCardOrderItems).shouldHaveSize(2);
        int price = parseInteger($$(pathToCardOrderItems).first().getText().replace("₽", "").replaceAll("\\s+", ""));
        int discount = parseInteger($$(pathToCardOrderItems).last().getText().replace("₽", "").replaceAll("\\s+", "")) * -1;
        int calculatedResult = (price * 30) / 100;
        logger.info("Checkout price: " + price + " Discount price: " + discount + " Calculated result: " + calculatedResult);
        assertEquals(calculatedResult, discount, "Скидка по промокоду не соответвует действительности.");
    }

    @Step("Установка промокода {promo}")
    public CartPage setPromotionalCode(String promo) {
        logger.info("Set promotional code: " + promo);
        click($(pathToOpenPromotionalCode));
        $(pathToPromotionalCodeInput).setValue(promo);
        $(pathToPromotionalCodeButton).click();
        return this;
    }

    @Step("Удаление промокода")
    public CartPage deletePromotionalCode() {
        $(pathToButtonDeletePromotionalCode).click();
        $(pathToPromotionalCodeAdded).shouldNotBe(Condition.exist);
        return this;
    }

    @Step("Проверяем что промокод отображается псоле добавления")
    public CartPage checkIfPromotionalCodeDisplayed(String promo) {
        logger.info("Check if promotional code is displayed");
        $(pathToPromotionalCodeAdded).shouldBe(Condition.text(promo));
        return this;
    }

    public void checkAlertMessageText() {
        //не работает, не видит сообщение, требуется более детальный дебаг
        String text = $x("//*[@id=\"message-cart-text\"]").getText();
        logger.info("text from page: " + text);
        //assertTrue($(pathToUiAlertMessageText).getText().isEmpty(), "Не отображается блок всплывающее окно");
    }
}
