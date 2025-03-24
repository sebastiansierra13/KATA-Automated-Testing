package com.bdb.automated.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * BasePage: Clase base que encapsula la interacción con Selenium.
 * Proporciona métodos reutilizables para interactuar con la UI.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor de la clase BasePage.
     * @param driver WebDriver usado para la automatización.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Espera explícita de 15s
    }

    /**
     * Espera hasta que un elemento sea visible y lo devuelve.
     * @param locator By selector del elemento.
     * @return WebElement visible.
     */
    protected WebElement waitElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Hace clic en un elemento después de esperar su visibilidad.
     * @param locator By selector del elemento.
     */
    protected void click(By locator) {
        waitElementVisible(locator).click();
    }

    /**
     * Escribe texto en un campo después de esperar su visibilidad.
     * @param locator By selector del campo.
     * @param text Texto a escribir.
     */
    protected void writeText(By locator, String text) {
        WebElement element = waitElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Obtiene el texto de un elemento después de esperar su visibilidad.
     * @param locator By selector del elemento.
     * @return Texto del elemento.
     */
    protected String getText(By locator) {
        return waitElementVisible(locator).getText();
    }

    /**
     * Obtiene una lista de elementos coincidentes con el selector dado.
     * @param locator By selector del elemento.
     * @return Lista de WebElements encontrados (puede estar vacía si no hay coincidencias).
     */
    protected List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.isEmpty() ? List.of() : elements;
    }
}
