package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By townYes = By.cssSelector("button[data-marker='location/tooltip-agree']");
    private By search = By.cssSelector("#search");
    private By withPhoto = By.xpath("//*[contains(text(), 'только с фото')]");
    private By btnFind = By.cssSelector("[data-marker='search-form/submit-button']");
    private By btnFin = By.xpath("(//select)[2]");

    public MainPage openPage() {
        driver.get("https://www.avito.ru");
        return new MainPage(driver);
    }

    public MainPage clickTownYes() {
        driver.findElement(townYes).click();
        return new MainPage(driver);
    }

    public MainPage fillFieldSearch() {
        driver.findElement(search).sendKeys("iPhone XS 256gb");
        return new MainPage(driver);
    }

    public MainPage clickWithPhoto() {
        driver.findElement(withPhoto).click();
        return new MainPage(driver);
    }

    public MainPage clickBtnFind() {
        driver.findElement(btnFind).click();
        return new MainPage(driver);
    }

    public MainPage selectSortByDate() {
        Select select = new Select(driver.findElement(btnFin));
        select.selectByValue("104");
        return new MainPage(driver);
    }

    public void takeDataAndWriteFile() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 1; i <= 11; i++) {
            map.put(driver.findElement(By.xpath("(//*[@itemprop='name'])" + "[" + i + "]")).getText(),
                    Integer.parseInt(driver.findElement(By.xpath("(//*[@itemprop='price'])[" + i + "]")).getAttribute("content")));
        }
        try {
            Files.write(Paths.get("src/test/java/Files/file.txt"),
                    map.entrySet().stream()
                            .sorted(Map.Entry.<String, Integer>comparingByValue())
                            .map(k -> k.getKey() + " Цена: " + k.getValue()).collect(Collectors.toList()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
