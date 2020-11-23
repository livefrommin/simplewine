package pages.items;

import com.codeborne.selenide.SelenideElement;

public class DigiSnippet {

    private String digiArticle;
    private String digiName;
    private String digiInfo;

    public DigiSnippet(SelenideElement element) {
        this.digiArticle = element.find(".digi-product-vendor-code").getText();
        this.digiName = element.find(".digi-product-label").getText();
        this.digiInfo = element.find(".digi-product-info").getText();
    }

    public String getDigiArticle() {
        return digiArticle;
    }

    public void setDigiArticle(String digiArticle) {
        this.digiArticle = digiArticle;
    }

    public String getDigiName() {
        return digiName;
    }

    public void setDigiName(String digiName) {
        this.digiName = digiName;
    }

    public String getDigiInfo() {
        return digiInfo;
    }

    public void setDigiInfo(String digiInfo) {
        this.digiInfo = digiInfo;
    }

    @Override
    public String toString() {
        return "DigiSnippet{" +
                "digiArticle='" + digiArticle + '\'' +
                ", digiName='" + digiName + '\'' +
                ", digiInfo='" + digiInfo + '\'' +
                '}';
    }
}