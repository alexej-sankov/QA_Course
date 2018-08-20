package com.telran;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebElement searchBar;

    public static final String SEARCH_BAR_ID = "twotabsearchtextbox";
    final String AMAZON_URl = "https://www.amazon.de/";


    WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPage go2Page() {
        this.webDriver.get(AMAZON_URl);
        return this;
    }

    public void init() {
        this.searchBar = this.webDriver.findElement(By.id(SEARCH_BAR_ID));
    }

    public SearchResultPage performSearchWithEnterHit(String keywords) {
        searchBar.sendKeys(keywords);
        searchBar.submit();
        return new SearchResultPage(this.webDriver);
    }
}
