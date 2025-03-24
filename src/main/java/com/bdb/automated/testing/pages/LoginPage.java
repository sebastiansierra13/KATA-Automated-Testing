package com.bdb.automated.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * LoginPage: Representa la página de inicio de sesión de OrangeHRM.
 * Utiliza el patrón Page Object Model (POM) para encapsular los elementos de la UI y sus interacciones.
 */
public class LoginPage extends BasePage {

    // Localizadores de elementos en la página
    private By usernameInput = By.name("username"); // Campo de usuario
    private By passwordInput = By.name("password"); // Campo de contraseña
    private By loginButton = By.xpath("//button[@type='submit']"); // Botón de inicio de sesión
    private By invalidCredentialsMessage = By.cssSelector(".oxd-alert-content-text"); // Mensaje de error por credenciales inválidas
    private By requiredMessage = By.cssSelector(".oxd-input-field-error-message"); // Mensaje de "Required" si falta algún campo

    /**
     * Constructor de LoginPage.
     * @param driver WebDriver utilizado para interactuar con la página.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Realiza el proceso de login ingresando credenciales y haciendo clic en el botón de inicio de sesión.
     * @param user Nombre de usuario a ingresar.
     * @param pass Contraseña a ingresar.
     */
    public void loginWithCredentials(String user, String pass) {
        writeText(usernameInput, user);
        writeText(passwordInput, pass);
        click(loginButton);
    }

    /**
     * Obtiene el mensaje de error mostrado tras un intento fallido de inicio de sesión.
     * Prioriza los mensajes "Required" si existen, de lo contrario, devuelve "Invalid credentials".
     * @return Texto del mensaje de error o "No se encontró ningún mensaje de error."
     */
    public String getErrorMessage() {
        List<WebElement> requiredMessages = getElements(requiredMessage);
        if (!requiredMessages.isEmpty()) {
            return requiredMessages.get(0).getText(); // Devuelve "Required"
        }

        try {
            return waitElementVisible(invalidCredentialsMessage).getText(); // Espera y devuelve "Invalid credentials"
        } catch (TimeoutException e) {
            return "No se encontró ningún mensaje de error.";
        }
    }

}
