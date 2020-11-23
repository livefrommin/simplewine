package cart;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.CartPage;
import pages.CatalogPage;
import pages.CheckoutPage;
import webdriver.BaseTest;

@Epic("Корзина")
@Feature("Проверка минимальной стоимости заказа 2000 ")
public class CartMinPrice_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.WINE_NAME);
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @Description("Невозможно оформить заказ при сумме менее 2000 рублей \n" +
            "Если данный тест упал, требуется проверить корректно установленную минимальную сумму заказа на стенде, должно быть аналогично проду 2000 рублей" )
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("853" )
    @Link(name = "Link to video", value = "addProductToCart_less2000_and_Checkout", type = "video" )
    @ParameterizedTest(name = "{displayName}" )
    @CsvSource({"2000, -1"})
    public void addProductToCart_less2000_and_Checkout(int price, int sign) {
        catalogPage.addRandomCartWithPrice(price, sign).openCart();
        cartPage.checkoutIsDisabled();
    }

    @Description("Оформление заказа сумма которого больше 2000, способ получения согласовать с менеджером \n" +
            "Если данный тест упал, требуется проверить корректно установленную минимальную сумму заказа на стенде, должно быть аналогично проду 2000 рублей" )
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("854" )
    @Link(name = "Link to video", value = "addProductToCart_than2000_and_Checkout", type = "video" )
    @ParameterizedTest(name = "{displayName}" )
    @CsvSource({"2000, 2"})
    public void addProductToCart_than2000_and_Checkout(int price, int sign) {
        catalogPage.addRandomCartWithPrice(price, sign).openCart();
        cartPage.clickCheckout();
        checkoutPage.setDefaultDataAndCompareResult();
    }
}
