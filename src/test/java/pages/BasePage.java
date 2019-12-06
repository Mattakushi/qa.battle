package pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {

    WebDriver driver;

    Actions action;
    WebDriverWait wait;

    JavascriptExecutor js;

    BasePage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 15);

        js = (JavascriptExecutor) driver;
    }


    public void acceptAlert() {
        wait.until(alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        wait.until(alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    WebElement find(By locator) {
        return driver.findElement(locator);
    }

    List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }
}
