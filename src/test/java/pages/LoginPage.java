package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final String URI = "http://localhost:8080/";

    private By activeLogin = By.cssSelector("div[onclick='startInputLogin()']");
    private By loginField = By.id("loginInput");
    private By activePassword = By.cssSelector("div[onclick='startInputPassword()']");
    private By passwordField = By.id("passwordInput");

    private By loginHoverButton = By.cssSelector(".card-footer.bg-transparent > button:nth-child(2)");
    private By loginButton = By.cssSelector("img[src='sign.png']");

    private By homePage = By.id("mainContainer");

    public LoginPage open() {
        driver.get(URI);
        return this;
    }

    public LoginPage enterLogin(String login) {
        find(activeLogin).click();
        find(loginField).sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        find(activePassword).click();
        find(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage login() {
        action.moveToElement(find(loginHoverButton)).perform();
        wait.until(visibilityOfElementLocated(loginButton));
        find(loginButton).click();
        return this;
    }

    public void positiveCheck() {
        wait.until(visibilityOfElementLocated(homePage));
        assertEquals(URI + "main.html", driver.getCurrentUrl());
    }

    public void negativeCheck() {
        assertEquals(URI + "loginError.html", driver.getCurrentUrl());
    }

    public void logout() {
        driver.manage().deleteAllCookies();
        refreshPage();
    }

    public void cookieLogin() {
        driver.manage().addCookie(new Cookie("secret", "IAmSuperSeleniumMaster"));
        driver.navigate().refresh();
        assertEquals(URI + "main.html", driver.getCurrentUrl());
    }

}
