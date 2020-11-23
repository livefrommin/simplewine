package pages.items;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.FiltersBase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Класс отвечает за сниппет товара из каталога.
 */
public class ProductSnippet {
    private SelenideElement productValue;
    private String productArticle;
    private String productType;
    private String productName;
    private String productTitleName;
    private String productDesc;
    private int productPrice;
    private String productPriceDiscount;
    private SelenideElement addButton;
    private SelenideElement root;

    //description
    private String productCountry;
    private String productColor;
    private String productSugar;
    private String productVolume;
    private String productMaterial;

    //raitings
    private int productRatingPR;

    private final List<String> typeList = Arrays.asList("Вино", "Игристое вино", "Шампанское", "Шипучее вино",
            "Аквавит", "Арманьяк", "Биттер", "Бренди", "Виски", "Водка", "Граппа", "Джин", "Кальвадос", "Кашаса",
            "Коньяк", "Ликер", "Мескаль", "Настойка", "Ром", "Сетю", "Текила", "Саке");

    public ProductSnippet() {
    }

    public ProductSnippet(SelenideElement element) {
        this.root = element;
        this.productValue = element.find(By.xpath(".//*[text()=" + FiltersBase.value + "]/.."));
        this.productArticle = element.find(".product-snippet__id").getText().replace("Артикул:\n", "");
        this.productName = element.find(".product-snippet__name").getText();
        this.productTitleName = element.find(".product-card__image").attr("title");
        this.productDesc = element.find(".product-snippet__desc").getText();
        this.productPrice = Integer.parseInt(element.find(".product-snippet__price").getText()
                .replace("₽", "").replaceAll("\\s+", ""));
        this.productPriceDiscount = getProductPriceDiscount(element);
        this.addButton = element.find(".product-snippet__buy > div > button.ui-button");
    }

    private String getProductPriceDiscount(SelenideElement element) {
        if(element.find(".product-snippet__discount").exists())
            return element.find(".product-snippet__discount").getText();
        return null;
    }

    public String getProductPriceDiscount() {
        return productPriceDiscount;
    }

    public void setProductPriceDiscount(String productPriceDiscount) {
        this.productPriceDiscount = productPriceDiscount;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductValue() {
        return String.valueOf(productValue);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SelenideElement getRoot() {
        return root;
    }

    public void setRoot(SelenideElement root) {
        this.root = root;
    }

    public int getProductRatingPR() {
        return productRatingPR;
    }

    public void setProductRatingPR(int productRatingPR) {
        this.productRatingPR = productRatingPR;
    }

    public String getArticle() {
        return productArticle;
    }

    public void setArticle(String article) {
        this.productArticle = article;
    }

    public String getProductTitleName() {
        return productTitleName;
    }

    public void setProductTitleName(String productTitleName) {
        this.productTitleName = productTitleName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public SelenideElement getAddButton() {
        return addButton;
    }

    public void setAddButton(SelenideElement addButton) {
        this.addButton = addButton;
    }

    public String getProductArticle() {
        return productArticle;
    }

    public void setProductArticle(String productArticle) {
        this.productArticle = productArticle;
    }

    public String getProductCountry() {
        return productCountry;
    }

    public void setProductCountry(String productCountry) {
        this.productCountry = productCountry;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSugar() {
        return productSugar;
    }

    public void setProductSugar(String productSugar) {
        this.productSugar = productSugar;
    }

    public String getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(String productVolume) {
        this.productVolume = productVolume;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductSnippet{ " +
                "productArticle='" + productArticle + '\'' +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", productTitleName='" + productTitleName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productPrice=" + productPrice +
                ", productCountry='" + productCountry + '\'' +
                ", productColor='" + productColor + '\'' +
                ", productSugar='" + productSugar + '\'' +
                ", productVolume='" + productVolume + '\'' +
                ", productMaterial='" + productMaterial + '\'' +
                ", productRatingPR=" + productRatingPR +
                ", typeList=" + typeList +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSnippet that = (ProductSnippet) o;
        return Objects.equals(productArticle, that.productArticle) &&
                Objects.equals(productTitleName, that.productTitleName) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(productCountry, that.productCountry) &&
                Objects.equals(productColor, that.productColor) &&
                Objects.equals(productSugar, that.productSugar) &&
                Objects.equals(productMaterial, that.productMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productArticle, productTitleName, productDesc, productPrice, productCountry, productColor, productSugar, productVolume, productMaterial);
    }
}
