package com.telran;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class SearchForProductTest {
    final String CHROME_DRIVER_PATH = "C:\\Users\\leSpike\\Downloads\\chromedriver_win32\\chromedriver.exe";
    final String AMAZON_URl = "https://www.amazon.de/";
    final double MIN_PRICE = 10;

    @Test
    public void  amazonSearchAndFindByPrice() throws ParseException {
        System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(AMAZON_URl);

        WebElement webElement = driver.findElement(By.id("twotabsearchtextbox"));
        webElement.sendKeys("watch men");
        webElement.submit();

        List<WebElement> elementsList = driver.findElements(By.cssSelector(".s-price"));

        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);

        List<Double> pricesList = new ArrayList<Double>();

        for(WebElement e : elementsList) {
            String currentPrice = e.getText().substring(4);

            Number number = format.parse(currentPrice);
            double dPrice = number.doubleValue();
            if(dPrice <= MIN_PRICE) {
                pricesList.add(dPrice);
            }
        }
        Assert.assertTrue(pricesList.size()>0);
        driver.quit();
        for(int i=0; i<pricesList.size(); i++) {
            System.out.println(pricesList.get(i));
        }
    }
}
