package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Articles to read
    private By advertisers = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(1)");
    private By advertisersSection = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(1) > button");
    private By advertisersSectionArticles = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(1) > .sub-tree button");

    private By publishers = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(2)");
    private By publishersSection = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(2) > button");
    private By publishersSectionArticles = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(2) > .sub-tree button");

    private By topLevelClients = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(3)");
    private By topLevelClientsSection = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(3) > button");
    private By topLevelClientsSectionArticles = By.cssSelector("div[class='card-body'] .tree-main-node:nth-child(3) > .sub-tree button");

    //Saved articles
    private By firstSavedSection = By.cssSelector(".card-body.text-right > .tree-main-node:nth-child(1) > button");
    private By firstSavedArticle = By.cssSelector(".tree-main-node:nth-child(1) .right-sub-tree button");

    private By articleSelector = By.cssSelector("#dataCard .card-title");
    private By data = By.cssSelector("#dataCard textarea");
    private By image = By.id("heroImage");
    private By imageSlider = By.cssSelector(".ui-slider > span");

    private By downloadInfoFile = By.cssSelector("#dataCard .card-body > *:nth-child(5) button");
    private By moveToSaved = By.cssSelector("#dataCard .card-body > *:nth-child(9) button:nth-child(1)");
    private By removedFromSaved = By.cssSelector("#dataCard .card-body > *:nth-child(9) button:nth-child(2)");


    public MainPage openAdvertisers() {
        find(advertisersSection).click();
        return this;
    }

    public MainPage openPublishers() {
        find(publishersSection).click();
        return this;
    }

    public MainPage openTopLevelClients() {
        find(topLevelClientsSection).click();
        return this;
    }

    public MainPage checkAdvertisersArticles(Integer count) {
        assertEquals(count, findAll(advertisersSectionArticles).size());
        return this;
    }

    public MainPage checkPublishersArticles(Integer count) {
        assertEquals(count, findAll(publishersSectionArticles).size());
        return this;
    }

    public MainPage checkTopLevelClientsArticles(Integer count) {
        assertEquals(count, findAll(topLevelClientsSectionArticles).size());
        return this;
    }

    public MainPage selectAdvertisersArticle(Integer num) {
        find(advertisers).findElement(By.cssSelector(".sub-tree > .sub-tree-element:nth-child(" + num + ") > button")).click();
        wait.until(visibilityOfElementLocated(articleSelector));
        return this;
    }

    public MainPage selectPublishersArticle(Integer num) {
        find(publishers).findElement(By.cssSelector(".sub-tree > .sub-tree-element:nth-child(" + num + ") > button")).click();
        wait.until(visibilityOfElementLocated(articleSelector));
        return this;
    }

    public MainPage selectTopLevelClientsArticle(Integer num) {
        find(topLevelClients).findElement(By.cssSelector(".sub-tree > .sub-tree-element:nth-child(" + num + ") > button")).click();
        wait.until(visibilityOfElementLocated(articleSelector));
        return this;
    }

    public MainPage scrollDownTextarea() {
        js.executeScript("var height = $('#dataCard > div > div > div:nth-child(5) > textarea')[0].scrollHeight\n"
                + "$('#dataCard > div > div > div:nth-child(5) > textarea')[0].scrollTop = height");
        return this;
    }

    public MainPage moveToSaved() {
        find(moveToSaved).click();
        return this;
    }

    public MainPage removedFromSaved() {
        find(removedFromSaved).click();
        return this;
    }

    public MainPage openFirstSavedArticle() {
        find(firstSavedSection).click();
        find(firstSavedArticle).click();
        wait.until(visibilityOfElementLocated(articleSelector));
        return this;
    }

    public MainPage checkFirstSavedArticleIsExist() {
        assertTrue(find(firstSavedSection).isDisplayed());
        return this;
    }

    public MainPage downloadInfo() {
        find(downloadInfoFile).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void verifySavedFile() {
        String expectedData = find(data).getAttribute("textContent");

        String fileName = System.getProperty("user.home") + "\\Downloads\\saved_reports\\data.txt";
        Path path = Paths.get(fileName);
        String savedData = null;
        try {
            savedData = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(expectedData, savedData);
        } finally {
            new File(fileName).delete();
        }
    }

    public MainPage moveSlider(Integer percent) {
        double xOffset = percent * 5.98;
        action.dragAndDropBy(find(imageSlider), (int) xOffset, 0).perform();
        return this;
    }

    public void verifyImageSize(Integer width) {
        Integer actualWidth = Integer.valueOf(find(image).getAttribute("width"));
        assertEquals(width, actualWidth);
    }
}
