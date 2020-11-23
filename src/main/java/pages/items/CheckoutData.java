package pages.items;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CheckoutData {

    private String clientFirstName;
    private String clientSecondName;
    private String clientPatronymicName;
    private String clientFullName;
    private String clientTelephone;
    private String clientEmail;
    private final String delimiter = " ";
    private String confirmCheckoutH2Text;
    private String confirmCheckoutAllText;
    private String checkoutDate;
    private String checkOutDeliveryType;
    public String checkOutStore;

    public CheckoutData() {
        this.clientFirstName = "Тест";
        this.clientSecondName = "Тестов";
        this.clientPatronymicName = "Тестович";
        this.clientTelephone = "9999999999";
        this.clientEmail = "test_user_email@mail.ru";
        this.clientFullName = clientFirstName + delimiter
                + clientSecondName + delimiter
                + clientSecondName;
        createConfirmText();
        createCheckoutDate();
    }

    public CheckoutData(String clientFullName, String clientTelephone, String clientEmail) {
        this.clientTelephone = clientTelephone;
        this.clientEmail = clientEmail;
        this.clientFullName = clientFullName;
        createConfirmText();
        createCheckoutDate();
    }

    public CheckoutData(List<String> data) {
        this.clientFullName = data.get(0);
        this.clientTelephone = data.get(1);
        this.clientEmail = data.get(2);
    }

    private void createCheckoutDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        Date date = new Date();
        this.checkoutDate = dateFormat.format(date);
    }

    public String getCheckOutStore() {
        return checkOutStore;
    }

    public void setCheckOutStore(String checkOutStore) {
        this.checkOutStore = checkOutStore;
    }

    public String getCheckOutDeliveryType() {
        return checkOutDeliveryType;
    }

    public void setCheckOutDeliveryType(String checkOutDeliveryType) {
        this.checkOutDeliveryType = checkOutDeliveryType;
    }

    private void createConfirmText() {
        this.confirmCheckoutH2Text = clientFullName + ", ваш заказ успешно оформлен";
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }

    public String getClientPatronymicName() {
        return clientPatronymicName;
    }

    public void setClientPatronymicName(String clientPatronymicName) {
        this.clientPatronymicName = clientPatronymicName;
    }

    public String getClientTelephone() {
        return clientTelephone;
    }

    public void setClientTelephone(String clientTelephone) {
        this.clientTelephone = clientTelephone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getConfirmCheckoutH2Text() {
        return confirmCheckoutH2Text;
    }

    public void setConfirmCheckoutH2Text(String confirmCheckoutH2Text) {
        this.confirmCheckoutH2Text = confirmCheckoutH2Text;
    }
}
