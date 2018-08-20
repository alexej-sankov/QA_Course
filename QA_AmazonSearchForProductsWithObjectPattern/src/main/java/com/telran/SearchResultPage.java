package com.telran;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {

    private WebElement nextButton;

    public static final String NEXT_BUTTON_ID = "pagnNextString";

    WebDriver webDriver;

    public SearchResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void init() {
        this.nextButton = this.webDriver.findElement(By.id(NEXT_BUTTON_ID));
    }

    public SearchResultPage clickNextButton() {
        this.nextButton.click();
        return new SearchResultPage(this.webDriver);
    }
}
