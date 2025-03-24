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
    protected WebElement esperarElementoVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Hace clic en un elemento después de esperar su visibilidad.
     * @param locator By selector del elemento.
     */
    protected void click(By locator) {
        esperarElementoVisible(locator).click();
    }

    /**
     * Escribe texto en un campo después de esperar su visibilidad.
     * @param locator By selector del campo.
     * @param texto Texto a escribir.
     */
    protected void escribirTexto(By locator, String texto) {
        WebElement elemento = esperarElementoVisible(locator);
        elemento.clear();
        elemento.sendKeys(texto);
    }

    /**
     * Obtiene el texto de un elemento después de esperar su visibilidad.
     * @param locator By selector del elemento.
     * @return Texto del elemento.
     */
    protected String obtenerTexto(By locator) {
        return esperarElementoVisible(locator).getText();
    }

    /**
     * Obtiene una lista de elementos coincidentes con el selector dado.
     * @param locator By selector del elemento.
     * @return Lista de WebElements encontrados (puede estar vacía si no hay coincidencias).
     */
    protected List<WebElement> obtenerElementos(By locator) {
        List<WebElement> elementos = driver.findElements(locator);
        return elementos.isEmpty() ? List.of() : elementos;
    }

    /**
     * Verifica si un elemento está presente en la página sin lanzar excepción.
     * @param locator By selector del elemento.
     * @return true si el elemento existe, false si no.
     */
    protected boolean existeElemento(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
