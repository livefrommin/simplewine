package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.Checkout;
import constants.DefaultText;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.items.CheckoutData;

import static com.codeborne.selenide.Selenide.*;
import static helpers.SelenideHelper.click;
import static helpers.SelenideHelper.strollToElement;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutPage {

    final static Logger logger = LogManager.getLogger(CheckoutPage.class);

    CheckoutData checkoutData;

    final String checkoutMain = ".checkout-success-page";
    final String userNameSelector = "div.checkout__about-user > div:nth-child(1) > div:nth-child(1) > input";
    final String userTelephoneSelector = "div.checkout__about-user > div:nth-child(1) > div:nth-child(2) > input";
    final String userEmailSelector = "div.checkout__about-user > div:nth-child(1) > div:nth-child(3) > input";
    final String deliveryTypeSelector = "div.checkout__order > div:nth-child(3) > div.checkout__delivery-type > div";
    final String confirmButton = "div.checkout__cart > div.checkout__cart-total > button";
    final String confirmTextH2Selector = "body > div.page-layout > div.page-layout__content > main > h2";

    final String checkoutButton = "div.checkout__cart-total > button";
    final String checkOutInputTextError = ".ui-input-text__error";

    //pickup items
    final ElementsCollection pickUpCheckoutStores = $$(".checkout__stores-item");
    final SelenideElement pickUpFastSearch = $("#checkout-view-list > div.ui-search > span > span > input");
    final String pickUpFastSearchItem = "div.checkout__stores-inner > div > div > div > div.checkout__stores-item-name";
    final SelenideElement pickUpChooseAnotherMagazine = $("#winestore-container > div.checkout__store-grid > button");
    final ElementsCollection pickUpCheckboxesConfirmation = $$("div.checkout__confirmation > div > label > div > div > div.ui-choose__text");

    public CheckoutPage() {
    }

    @Step("Нажать \"Оформить заказ\"")
    public CheckoutPage clickCheckout() {
        $(checkoutButton).click();
        return this;
    }

    public void setDefaultDataAndCompareResult() {
        setClientData();
        confirmCheckout();
        compareCheckoutResult();
    }

    @Step("Установить линые данные пользователя. Имя: {name}, Телефон: {telephone}, Email: {email}")
    public CheckoutPage setClientData(String name, String telephone, String email) {
        logger.info("Set client data. Name: " + name + " telephone: " + telephone + " email: " + email);
        checkoutData = new CheckoutData(name, telephone, email);
        setClientDataOnForm(checkoutData);
        return this;
    }

    @Step("Установить личные данные default")
    public CheckoutPage setClientData() {
        logger.info("Set client data.");
        checkoutData = new CheckoutData();
        setClientDataOnForm(checkoutData);
        return this;
    }

    @Step("Установить способ получения - {type}")
    public CheckoutPage setProductionMethod(String type) {
        logger.info("Set production method " + type + ".");
        SelenideElement method = $$(deliveryTypeSelector).findBy(Condition.id(type));
        checkoutData.setCheckOutDeliveryType(method.getText());
        click(method);
        return this;
    }

    @Step("Ищем {value} в быстром поиске выбора винотек")
    public CheckoutPage setPickupFastSearchMagazine(String value) {
        logger.info("Find in fast search for pickup value - " + value);
        strollToElement(pickUpFastSearch);
        pickUpFastSearch.setValue(value);
        return getCheckoutPage(value);
    }

    @Step("Выбрать другой магазин {value}")
    public CheckoutPage setAnotherMagazine(String value) {
        logger.info("Set another magazine");
        click(pickUpChooseAnotherMagazine);
        return getCheckoutPage(value);
    }

    private CheckoutPage getCheckoutPage(String value) {
        SelenideElement store = pickUpCheckoutStores.filter(Condition.textCaseSensitive(value))
                .first();
        String storeName = store.find(".checkout__stores-item-name").getText();
        checkoutData.setCheckOutStore(storeName);
        assertTrue(storeName.contains(value),
                "Не удалось найти магазин " + value);
        click(store.find(".ui-button"));
        return this;
    }

    @Step("Установить дату получения")
    public CheckoutPage setDateOfReceiving() {
        logger.info("Set receiving date.");
        //TODO нужен ли вообще?:)
        return this;
    }

    @Step("Выбираем способ подтверждения заказа")
    public CheckoutPage setConfirmationMethod(String method) {
        logger.info("Set confirmation method " + method);
        click(pickUpCheckboxesConfirmation.find(Condition.exactText(method)));
        return this;
    }

    @Step("Установить текст комменатрия")
    public CheckoutPage setCommentText(String commentText) {
        logger.info("Set comment text.");
        return this;
    }

    @Step("Установить текст комменатрия")
    public CheckoutPage setCommentText() {
        setCommentText(DefaultText.DEFAULT_COMMENT_TEXT);
        return this;
    }

    @Step("Подтвердить заказ")
    public CheckoutPage confirmCheckout() {
        $(confirmButton).hover().should(Condition.exactText("Оформить заказ"))
                .click();
        return this;
    }

    @Step("Сравнить данные после успешного оформления заказа")
    public void compareCheckoutResult() {
        logger.info("Compare data after successful ordering.");
        $(checkoutMain).should(Condition.visible);
        String confirmH2Text = $(confirmTextH2Selector).getText().trim();
        String confirmDeliveryMethod = getSuccessDataFromPage(Checkout.CHECKOUT_SUCCESS_DELIVERY);
        String confirmAddress = getSuccessDataFromPage(Checkout.CHECKOUT_SUCCESS_ADDRESS);
        assertAll("compare checkout page result",
                () -> assertTrue(checkoutData.getCheckOutDeliveryType().contains(confirmDeliveryMethod),
                        "Не удалось сравнить \"Способ доставки\". Значения " + checkoutData.getCheckOutDeliveryType() + "  " + confirmDeliveryMethod),
                () -> assertEquals(checkoutData.getConfirmCheckoutH2Text(), confirmH2Text, "Не удалось сравнить текст ваш заказ оформлен"),
                () -> {
                    if (confirmAddress != null)
                        assertTrue(checkoutData.getCheckOutStore().contains(confirmAddress));
                }
        );
    }

    @Step("Заказ невозможно оформить. Сообщение для блока {value} появлется.")
    public void compareNegativeResult(String value) {
        //мифический блок, то работает, то нет, требуется лучше его отлавливать
        //assertTrue($(checkOutAlertPath).isDisplayed(), "Не отображается блок \"Заполнить данные\"");
        assertTrue(checkCorrectInputs(value), "Неправильный текст для поля " + value);
    }

    private void setClientDataOnForm(CheckoutData client) {
        $(userNameSelector).setValue(client.getClientFullName());
        $(userTelephoneSelector).setValue(client.getClientTelephone());
        $(userEmailSelector).setValue(client.getClientEmail());
    }

    private boolean checkCorrectInputs(String input) {
        switch (input) {
            case "email":
                 return $(userEmailSelector).parent().find(checkOutInputTextError).getText().equals("Введите корректный Email");
            case "name":
                return $(userNameSelector).parent().find(checkOutInputTextError).getText().equals("Имя должно содержать только буквы");
            case "telephone":
                return $(userTelephoneSelector).parent().find(checkOutInputTextError).getText().equals("Не правильный телефон +7 (123");
            case "all":
                return $$(checkOutInputTextError).filter(Condition.visible).stream().noneMatch(e -> e.getText().isEmpty());
            default:
                logger.info("Unknown value for input search");
                return false;
        }
    }

    private String getSuccessDataFromPage(String value) {
        if($$(".checkout-success-info__title").find(Condition.exactText(value)).exists()) {
            return $$(".checkout-success-info__title")
                    .find(Condition.exactText(value))
                    .parent().find(".checkout-success-info__text")
                    .getText().replaceAll("\\n"," ").trim();
        }
        return null;
    }
}
