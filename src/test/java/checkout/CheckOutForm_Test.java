package checkout;

import constants.Checkout;
import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.CartPage;
import pages.CatalogPage;
import pages.CheckoutPage;
import webdriver.BaseTest;

@Epic("Оформление заказа")
public class CheckOutForm_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeEach
    public void before() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @Description("Согласование с менеджером")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1021")
    @Link(name = "Link to video", value = "checkOutConfirm_methodManager", type = "video")
    @Test
    public void checkOutConfirm_methodManager() {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.setClientData()
                .setProductionMethod(Checkout.DELIVERY_MANAGER)
                .setCommentText()
                .clickCheckout()
                .compareCheckoutResult();
    }

    @Description("Самовывоз. Подтверждение заказа - звонок")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1022")
    @Link(name = "Link to video", value = "checkOutConfirm_methodPickupCall", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Валовая, Винотека SimpleWine Пр.Мира"})
    public void checkOutConfirm_methodPickupCall(String magazineName, String anotherMagazineName) {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.setClientData()
                .setProductionMethod(Checkout.DELIVERY_PICKUP)
                .setPickupFastSearchMagazine(magazineName)
                .setAnotherMagazine(anotherMagazineName)
                .setConfirmationMethod(Checkout.CHECKOUT_CONFIRMATION_CALL)
                .setCommentText()
                .clickCheckout()
                .compareCheckoutResult();
    }

    @Description("Самовывоз. Подтверждение заказа - СМС")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1046")
    @Link(name = "Link to video", value = "checkOutConfirm_methodPickupSMS", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"SimpleWine Метрополис"})
    public void checkOutConfirm_methodPickupSMS(String magazineName) {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.setClientData()
                .setProductionMethod(Checkout.DELIVERY_PICKUP)
                .setPickupFastSearchMagazine(magazineName)
                .setConfirmationMethod(Checkout.CHECKOUT_CONFIRMATION_SMS)
                .setCommentText()
                .clickCheckout()
                .compareCheckoutResult();
    }

    @Feature("Блок \"Личные данные\" ")
    @Description("Оформление заказа с некорректным e-mail \"Негативный\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("857")
    @Link(name = "Link to video", value = "checkOutConfirm_negativeEmail", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Тест Тестов Тестович, 9999786543, 123"})
    public void checkOutConfirm_negativeEmail(String p1, String p2, String p3) {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.setClientData(p1, p2, p3).clickCheckout().compareNegativeResult("email");
    }

    @Feature("Блок \"Личные данные\" ")
    @Description("Оформление заказа с некорректным именем \"Негативный\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("856")
    @Link(name = "Link to video", value = "checkOutConfirm_negativeName", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"123, 9999786543, 123@mail.ru"})
    public void checkOutConfirm_negativeName(String p1, String p2, String p3) {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.setClientData(p1, p2, p3).clickCheckout().compareNegativeResult("name");
    }

    @Feature("Блок \"Личные данные\" ")
    @Description("Оформление заказа с некорректным номером телефона \"Негативный\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("858")
    @Link(name = "Link to video", value = "checkOutConfirm_negativeTelephone", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Тест Тестов Тестович, 123, 123@mail.ru"})
    public void checkOutConfirm_negativeTelephone(String p1, String p2, String p3) {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.setClientData(p1, p2, p3).clickCheckout().compareNegativeResult("telephone");
    }

    @Feature("Блок \"Личные данные\" ")
    @Description("Оформление заказа с незаполненными данными \"Негативный\"")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("855")
    @Link(name = "Link to video", value = "checkOutConfirm_negativeAll", type = "video")
    @Test
    public void checkOutConfirm_negativeAll() {
        catalogPage.fullCheckOutStep(Menu.WINE_NAME, cartPage);
        checkoutPage.clickCheckout().compareNegativeResult("all");
    }
}
