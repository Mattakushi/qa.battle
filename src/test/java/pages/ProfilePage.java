package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    private By profileButton = By.id("avatar");

    private By userProfilePage = By.id("v-pills-home-tab");
    private By paymentInfoPage = By.id("v-pills-messages-tab");

    private By firstNameInput = By.id("firstNameInput");
    private By lastNameInput = By.id("lastNameInput");
    private By saveUserInfo = By.cssSelector("button[onclick='saveUserInfo()']");
    private By successUserInfoSaveInfo = By.id("successUserInfoSaveInfo");

    private By cardNumber = By.id("cardNumberInput");
    private By paymentSystem = By.id("paymentSystemSelect");
    private By dayOfPayment = By.id("paymentRange");
    private By savePaymentInfo = By.cssSelector("button[onclick='savePaymentInfo()']");


    public void open() {
        find(profileButton).click();
    }

    public ProfilePage openUserProfile() {
        find(userProfilePage).click();
        return this;
    }

    public ProfilePage openPaymentInfo() {
        find(paymentInfoPage).click();
        wait.until(visibilityOfElementLocated(cardNumber));
        return this;
    }

    public ProfilePage fillFirstName(String firstName) {
        wait.until(visibilityOfElementLocated(firstNameInput));
        find(firstNameInput).sendKeys(firstName);
        return this;
    }

    public ProfilePage fillLastName(String lastName) {
        wait.until(visibilityOfElementLocated(lastNameInput));
        find(lastNameInput).sendKeys(lastName);
        return this;
    }

    public ProfilePage checkFirstName(String firstName) {
        String actualFirstName = find(firstNameInput).getAttribute("value");
        assertEquals(firstName, actualFirstName);
        return this;
    }

    public ProfilePage checkLastName(String lastName) {
        String actualLastName = find(lastNameInput).getAttribute("value");
        assertEquals(lastName, actualLastName);
        return this;
    }

    public ProfilePage saveUserInfo() {
        find(saveUserInfo).click();
        assertTrue(find(successUserInfoSaveInfo).isDisplayed());
        return this;
    }

    public ProfilePage fillCardNumber(String number) {
        find(cardNumber).sendKeys(number);
        return this;
    }

    public ProfilePage selectPaymentSystem(String name) {
        Select select = new Select(find(paymentSystem));
        select.selectByValue(name);
        return this;
    }

    public ProfilePage selectDayOfPayment(String day) {
        js.executeScript("arguments[0].value = '" + day + "'", find(dayOfPayment));
        return this;
    }

    public ProfilePage savePaymentInfo() {
        find(savePaymentInfo).click();
        return this;
    }

    public ProfilePage checkCardNumber(String number) {
        String actualCardNumber = find(cardNumber).getAttribute("value");
        assertEquals(number, actualCardNumber);
        return this;
    }

    public ProfilePage checkPaymentSystem(String name) {
        String actualPaymentSystem = find(paymentSystem).getAttribute("value");
        assertEquals(name, actualPaymentSystem);
        return this;
    }

    public ProfilePage checkDayOfPayment(String day) {
        String actualDayOfPayment = find(dayOfPayment).getAttribute("value");
        assertEquals(day, actualDayOfPayment);
        return this;
    }
}
