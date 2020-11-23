package cart;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BrandsPage;
import pages.CartPage;
import pages.CatalogPage;
import webdriver.BaseTest;


@Epic("Корзина")
@Feature("Промокоды в корзине")
public class PromotionalCode_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;
    BrandsPage brandsPage;
    private final String EXISTING_PROMOTIONAL_CODE = "NRD2019";
    private final String NON_EXISTING_PROMOTIONAL_CODE = "qwer123";

    @BeforeEach
    public void before() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        brandsPage = new BrandsPage();
    }

    @Description("Добавление существующего промокода")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("999")
    @Link(name = "Link to video", value = "activateExistPromotionalCode", type = "video")
    @Test
    public void activateExistPromotionalCode() {
        catalogPage.addRandomCart(Menu.WINE_NAME, 5).openCart();
        cartPage.setPromotionalCode(EXISTING_PROMOTIONAL_CODE).checkIfPromotionalCodeDisplayed(EXISTING_PROMOTIONAL_CODE)
                .checkPromotionalCodeApply();
    }

    @Description("Удаление промокода")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1000")
    @Link(name = "Link to video", value = "deletePromotionalCode", type = "video")
    @Test
    public void deletePromotionalCode() {
        catalogPage.addRandomCart(Menu.WINE_NAME, 5).openCart();
        cartPage.setPromotionalCode(EXISTING_PROMOTIONAL_CODE).checkIfPromotionalCodeDisplayed(EXISTING_PROMOTIONAL_CODE)
                .deletePromotionalCode();
    }

    @Description("Удаление промокода")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1001")
    @Link(name = "Link to video", value = "activateNonExistPromotionalCode", type = "video")
    @Test
    public void activateNonExistPromotionalCode() {
        catalogPage.addRandomCart(Menu.WINE_NAME, 5).openCart();
        cartPage.setPromotionalCode(NON_EXISTING_PROMOTIONAL_CODE);
        //не видит текст всплывающего окна, починить
        cartPage.checkAlertMessageText();
    }
}
