package com.telran;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    @Test
    public void testGoogleSearchWorks() {
        open("https://www.google.com");
        SelenideElement q = $(By.name("q"));
        q.setValue("java");
        q.pressEnter();

        $$("#ires .g").shouldBe(CollectionCondition.sizeGreaterThan(1));

    }
}
