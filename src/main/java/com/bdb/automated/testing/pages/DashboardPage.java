package com.bdb.automated.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * DashboardPage: Representa la página principal de OrangeHRM tras un inicio de sesión exitoso.
 * Se utiliza para validar que el usuario ha accedido correctamente.
 */
public class DashboardPage extends BasePage {

    // Localizador del encabezado del dashboard (prueba de que el login fue exitoso)
    private By dashboardHeader = By.tagName("h6");

    /**
     * Constructor de DashboardPage.
     * @param driver WebDriver utilizado para interactuar con la página.
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si el usuario se encuentra en el Dashboard.
     * @return true si el usuario ha iniciado sesión correctamente, false si no.
     */
    public boolean estaEnDashboard() {
        return obtenerTexto(dashboardHeader).equals("Dashboard");
    }
}
