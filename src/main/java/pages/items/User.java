package pages.items;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class User {

    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private boolean registrationFlagMailing = true;
    private boolean registrationFlagAgreeTerm = true;
    private FakeValuesService fakeValuesService;

    public User() {
        fakeValuesService = new FakeValuesService(
                new Locale("en-En"), new RandomService());
        this.name = fakeValuesService.bothify("autotest user-#######");
        this.password = fakeValuesService.bothify("??????????#####");
        this.phoneNumber = fakeValuesService.numerify("999#######");
        this.email = fakeValuesService.bothify("?????????##@mail.ru");
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRegistrationFlagMailing() {
        return registrationFlagMailing;
    }

    public boolean isRegistrationFlagAgreeTerm() {
        return registrationFlagAgreeTerm;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
