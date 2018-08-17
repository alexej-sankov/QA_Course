package com.telran;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWaitTest {
    final String CHROME_DRIVER_PATH = "C:\\Users\\leSpike\\Downloads\\chromedriver_win32\\chromedriver.exe";
    final String WEBSITE_URL = "https://school-samples-qa-modal.azurewebsites.net";
    final int WAITING_TIME = 10;
    WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
        webDriver = new ChromeDriver();
    }

    @Test
    public void waitForTestTest() {
        webDriver.get(WEBSITE_URL);
        WebElement webElement = webDriver.findElement(By.id("open-modal-button"));
        webElement.click();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, WAITING_TIME);
        WebElement webElement2 = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-body")));
        String text = webElement2.getText();
        System.out.println(text);
        Assert.assertTrue(text!=null);
        webDriver.quit();
        
    }
}
