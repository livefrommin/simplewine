package pages.items.cards;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideTargetLocator;
import org.openqa.selenium.By;
import pages.FiltersBase;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static utils.IntegersUtils.parseInteger;

public class ProductCard {

    final String pathSpecification = "#product-characteristics > div.product-characteristics > div.product-characteristics__column.product-characteristics__column_params > div > dl > div > dt";
    final String pathProductFacts = "div.product__top.container > div.product__facts > ul > li > div.product-facts__value";
    final String pathProductInfoMeta = "div.product-info__meta > div";
    final String pathServing = "div.product-characteristics > div.product-characteristics__column.product-characteristics__column_taste > div.serving > ul > li.serving__suitable-item";
    String pathProductInfoMeta_item = "div.product-info__meta-item";
    String pathProductSubDesc = ".product-sub-desc__text";
    final String pathToName = ".product-info__title";
    final String pathProductPage = ".product__top";
    final String pathTabsItem = ".tabs__list > .tabs__item";
    final String pathToMagazines = ".wine-cellar.wine-cellars__cellar";
    final String pathButtonAddToCart = ".product-controls__controls > .ui-button";
    final String pathToArticle = ".product-info > .product-info__head > .product-info__article";
    final String pathToCode = ".product-info > .product-info__head > .product-info__code";
    final String pathToPrice = ".product-price__current";
    final String pathProductBlock = "div.product";

    private String cardArticle;
    private String cardCode;
    private String cardName;
    private int cardPrice;
    private List<String> cardDesc;
    private List<String> cardText;
    private String cardAppelason;
    private String cardProducer;
    private List<String> cardServing;
    private int cardYear;
    private int cardStrength;
    private int cardRatingRP;
    private int cardRatingWS;
    private String cardProductionMethod;
    private String cardCapacity;
    private List<String> cardMagazines;
    private SelenideElement addToCartButton;
    private SelenideElement productValue;

    public ProductCard() {
        $(pathProductPage).shouldBe(Condition.exist);
        this.cardText = getProductText();
        this.cardRatingRP = getRatingValue("RP");
        this.cardRatingWS = getRatingValue("WS");
        this.cardYear = parseInteger(getSpecificationValue("ГОД"));
        this.cardStrength = parseInteger(getSpecificationValue("КРЕПОСТЬ"));
        this.cardCapacity = getSpecificationValue("ВЫДЕРЖКА В ЕМКОСТИ");
        this.cardAppelason = getSpecificationValue("АППЕЛЛАСЬОН");
        this.cardProducer = getSpecificationValue("ПРОИЗВОДИТЕЛЬ");
        this.cardProductionMethod = getSpecificationValue("МЕТОД ПРОИЗВОДСТВА");
        this.cardServing = getAllServingItem();
        this.cardDesc = getProductFacts();
        this.cardMagazines = getAllMagazines();
        this.addToCartButton = $(pathButtonAddToCart).should(Condition.exist);
        this.cardArticle = findElementAndGetText(pathToArticle).replace("Артикул: ", "");
        this.cardCode = findElementAndGetText(pathToCode);
        this.cardName = findElementAndGetText(pathToName);
        this.cardPrice = Integer.parseInt(findElementAndGetText(pathToPrice).replace("₽", "").replaceAll("\\s+", ""));
    }

    private String findElementAndGetText(String element) {
        return $(element).should(Condition.exist).getText();
    }


    private int getRatingValue(String ratingName) {
        SelenideElement element = $$(pathProductInfoMeta).find(Condition.text(ratingName));
        if (element.exists()) {
            return Integer.parseInt(element.getText().replace(ratingName + "\n", ""));
        }
        return 0;
    }

    private String getSpecificationValue(String specName) {
        SelenideElement element = $$(pathSpecification).find(Condition.text(specName));
        if (element.exists())
            return element.parent().find("dd").getText().replace("%", "");
        return null;
    }

    private List<String> getAllMagazines() {
        SelenideElement element = $$(pathTabsItem).find(Condition.text("Наличие в винотеках"));
        if(element.exists()) {
            element.hover().click();
            return $$(pathToMagazines).stream().map(e -> e.find(".wine-cellar__title").getText()).collect(Collectors.toList());
        }
        return null;
    }

    public String getCardProductionMethod() {
        return cardProductionMethod;
    }

    public void setCardProductionMethod(String cardProductionMethod) {
        this.cardProductionMethod = cardProductionMethod;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public int getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
    }

    public String getCardArticle() {
        return cardArticle;
    }

    public void setCardArticle(String cardArticle) {
        this.cardArticle = cardArticle;
    }

    public SelenideElement getAddToCartButton() {
        return addToCartButton;
    }

    public void setAddToCartButton(SelenideElement addToCartButton) {
        this.addToCartButton = addToCartButton;
    }

    private List<String> getProductFacts() {
        return $$(pathProductFacts).stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    private List<String> getProductText() {
        return $$(pathProductBlock).stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public boolean findInProductDesc(String value) {
        return cardDesc.stream().anyMatch(v -> v.toLowerCase().contains(value.toLowerCase()));
    }

    public boolean findInProduct(String value) {
        return cardText.stream().anyMatch(v -> v.toLowerCase().contains(value.toLowerCase()));
    }

    public List<String> getCardMagazines() {
        return cardMagazines;
    }

    public void setCardMagazines(List<String> cardMagazines) {
        this.cardMagazines = cardMagazines;
    }

    public List<String> getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(List<String> cardDesc) {
        this.cardDesc = cardDesc;
    }

    public List<String> getCardServing() {
        return cardServing;
    }

    public void setCardServing(List<String> cardServing) {
        this.cardServing = cardServing;
    }

    private List<String> getAllServingItem() {
        return $$(pathServing).stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public String getCardCapacity() {
        return cardCapacity;
    }

    public void setCardCapacity(String cardCapacity) {
        this.cardCapacity = cardCapacity;
    }

    public int getCardYear() {
        return cardYear;
    }

    public void setCardYear(int cardYear) {
        this.cardYear = cardYear;
    }

    public int getCardRatingWS() {
        return cardRatingWS;
    }

    public void setCardRatingWS(int cardRatingWS) {
        this.cardRatingWS = cardRatingWS;
    }

    public int getCardRatingRP() {
        return cardRatingRP;
    }

    public void setCardRatingRP(int cardRatingRP) {
        this.cardRatingRP = cardRatingRP;
    }

    public String getCardProducer() {
        return cardProducer;
    }

    public void setCardProducer(String cardProducer) {
        this.cardProducer = cardProducer;
    }

    public int getCardStrength() {
        return cardStrength;
    }

    public void setCardStrength(int cardStrength) {
        this.cardStrength = cardStrength;
    }

    public String getCardAppelason() {
        return cardAppelason;
    }

    public void setCardAppelason(String cardAppelason) {
        this.cardAppelason = cardAppelason;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "WineCard {" +
                " cardName='" + cardName + '\'' +
                ", cardArticle=" + cardArticle +
                ", cardCode=" + cardCode +
                ", cardDesc=" + cardDesc +
                ", cardAppelason='" + cardAppelason + '\'' +
                ", cardProducer='" + cardProducer + '\'' +
                ", cardServing=" + cardServing +
                ", cardYear=" + cardYear +
                ", cardStrength=" + cardStrength +
                ", cardRatingRP=" + cardRatingRP +
                ", cardRatingWS=" + cardRatingWS +
                ", cardCapacity='" + cardCapacity + '\'' +
                ", cardMagazines='" + cardMagazines + '\'' +
                '}';
    }
}
