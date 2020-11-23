package pages.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginAndRegisterModal {

    SelenideElement loginAndRegistrationModal;
    SelenideElement loginByEmail;
    SelenideElement loginEmail;
    SelenideElement loginPassword;
    SelenideElement loginButton;
    SelenideElement registerButton;

    public LoginAndRegisterModal() {
        this.loginAndRegistrationModal = $(".ui-modal__body.auth").shouldBe(Condition.visible);
        this.loginByEmail = $("div.auth__toggler-item:nth-child(2)");
        this.loginEmail = $("div.ui-input-text._ui-input-text_no-hide-error.ui-input-text_required:nth-child(1) > input:nth-child(1)");
        this.loginPassword = $("div.ui-input-text._ui-input-text_no-hide-error.ui-input-text_required:nth-child(2) > input:nth-child(1)");
        this.loginButton = $("div.ui-modal__inner > div > div > div.auth-tab.js-auth-tabs-content > div.auth-tab__email > form > div.auth-tab__form-footer > button");
        this.registerButton = $("//div[@class='auth__body-wrapper auth__byEmail']//button[1]");
    }

    public SelenideElement getLoginByEmail() { return loginByEmail; }

    public SelenideElement getLoginEmail() {
        return loginEmail;
    }

    public SelenideElement getLoginPassword() {
        return loginPassword;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public SelenideElement getRegisterButton() {
        return registerButton;
    }
}
