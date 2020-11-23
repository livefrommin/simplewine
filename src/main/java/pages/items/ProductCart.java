package pages.items;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Карточка торвара из корзины
 */
public class ProductCart {

    private final String contentPathTop = ".cart-page__products-content > div.cart-page__products-content-top";
    private final String contentPathBottom = ".cart-page__products-content > div.cart-page__products-content-bottom";
    private final String controlsPath = ".cart-page__products-controls";
    private final String descriptionPath = ".cart-page__products-content-top > .cart-page__products-desc > div";

    private String productName;
    private String productDesc;
    private String productArticle;
    private int productPrice;
    private String productAmount;
    private SelenideElement deleteButton;
    private SelenideElement addAmount;
    private SelenideElement deleteAmount;

    public ProductCart() {
    }

    public ProductCart(SelenideElement element) {
        this.productName = element.find(contentPathTop + " > a").getText();
        this.productPrice = Integer.parseInt(element.find(contentPathBottom).find(".cart-page__products-price").getText().replace("₽", "").replaceAll("\\s+", ""));
        this.productAmount = element.find(".cart-item__counter").find(".ui-counter__input").getText();
        this.deleteButton = element.find(controlsPath).find(".cart-page__products-controls-button-close");
        createDescriptionAndArticle();
    }

    private void createDescriptionAndArticle() {
        ElementsCollection list = $$(descriptionPath);
        if (list.size() == 1) {
            this.productArticle = list.first().getText().replaceAll("\\D+", "");
            this.productDesc = "";
            return;
        }
        this.productDesc = list.first().getText().replaceAll("(.*),\\s[0-9]{4}.*", "$1");
        this.productArticle = list.last().getText().replaceAll("\\D+", "");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductArticle() {
        return productArticle;
    }

    public void setProductArticle(String productArticle) {
        this.productArticle = productArticle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public SelenideElement getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(SelenideElement deleteButton) {
        this.deleteButton = deleteButton;
    }

    public SelenideElement getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(SelenideElement addAmount) {
        this.addAmount = addAmount;
    }

    public SelenideElement getDeleteAmount() {
        return deleteAmount;
    }

    public void setDeleteAmount(SelenideElement deleteAmount) {
        this.deleteAmount = deleteAmount;
    }

    @Override
    public String toString() {
        return "ProductCart: " +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productArticle='" + productArticle + '\'' +
                ", productPrice=" + productPrice +
                ", productAmount='" + productAmount + '\'' +
                '}' + "\n";
    }
}
