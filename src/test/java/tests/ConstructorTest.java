package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;
import driver.Configuration;

public class ConstructorTest extends Configuration {

    @Test
    @DisplayName("Переход к разделу Булки")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        mainPage.clickBunsTab();

        assert mainPage.isBunsTabActive() : "Раздел Булки не активен";
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        assert mainPage.isSaucesTabActive() : "Раздел Соусы не активен";
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsTab();

        assert mainPage.isFillingsTabActive() : "Раздел Начинки не активен";
    }
}
