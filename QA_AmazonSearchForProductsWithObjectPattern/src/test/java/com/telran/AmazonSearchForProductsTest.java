package com.telran;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class AmazonSearchForProductsTest {

    final String CHROME_DRIVER_PATH = "C:\\Users\\leSpike\\Downloads\\chromedriver_win32\\chromedriver.exe";
    final String AMAZON_URl = "https://www.amazon.de/";
    final NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);

    final double PRICE_LIMIT = 10;
    final String KEYWORDS = "java";

    WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
        webDriver = new ChromeDriver();
    }

    @Test
    public void amazonSearchForProductsByPriceTest() throws ParseException {
        go2Page(AMAZON_URl);
        searchForKeyWords(KEYWORDS);
        List<WebElement> results = getSearchResults();
        List<Product> products = parseResultsToProductsAndFilterByPrice(results, PRICE_LIMIT);

        Assert.assertTrue(products.size()>0);

        products.stream().forEach(x -> System.out.println(x.toString()));
    }

    public void go2Page(String url) {
        webDriver.get(url);
    }

    public void searchForKeyWords(String keywords) {
        WebElement webElement = webDriver.findElement(By.id("twotabsearchtextbox"));
        webElement.sendKeys(keywords);
        webElement.submit();
    }

    public List<WebElement> getSearchResults() {
        List<WebElement> results = webDriver.findElements(By.cssSelector(".s-result-item"));
        return results;
    }

    public List<Product> parseResultsToProductsAndFilterByPrice(List<WebElement> results, double priceLimit) throws ParseException {
        List<Product> products = new ArrayList<>();
        for(WebElement element : results) {
            try {
                WebElement priceElement = element.findElement(By.cssSelector(".s-price"));
                WebElement titleElement = element.findElement(By.cssSelector(".s-access-title"));
                WebElement linkElement = element.findElement(By.tagName("a"));

                String priceAsString = priceElement.getText();

                String title = titleElement.getText();
                String link = linkElement.getAttribute("href");

                Number number = format.parse(priceAsString.substring(4));
                double currentPrice = number.doubleValue();

                if(currentPrice <= priceLimit) {
                    Product product = new Product(title, link, currentPrice);
                    products.add(product);
                }
            }catch (NoSuchElementException ignored){
                ignored.printStackTrace();
            }finally {
                continue;
            }
        }
        return products;
    }

    @After
    public void after() {
        webDriver.quit();
    }

}
