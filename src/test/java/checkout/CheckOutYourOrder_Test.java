package checkout;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.CartPage;
import pages.CatalogPage;
import pages.CheckoutPage;
import webdriver.BaseTest;

@Epic("Оформление заказа")
public class CheckOutYourOrder_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeEach
    public void before() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @Description("Корректность количества товаров")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1008")
    @Link(name = "Link to video", value = "checkProductCount_Checkout", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"5"})
    public void checkProductCount_Checkout(int count) {
        catalogPage.addToCartAndOpenCart(count);
    }
}
