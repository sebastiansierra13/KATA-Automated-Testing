package com.bdb.automated.testing.test;

import com.bdb.automated.testing.pages.DashboardPage;
import com.bdb.automated.testing.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * LoginTest: Contiene los casos de prueba para la autenticación en OrangeHRM.
 * Utiliza TestNG para la ejecución y LoginPage como Page Object.
 */
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    /**
     * Configura la instancia de LoginPage antes de cada prueba.
     */
    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(driver); // Inicializa la página de login
    }

    /**
     * Prueba de inicio de sesión exitoso con credenciales válidas.
     */
    @Test
    public void testLoginExitoso() {
        loginPage.loginWithCredentials("Admin", "admin123");

        // Instanciamos DashboardPage después del login
        DashboardPage dashboardPage = new DashboardPage(driver);

        // Validamos que el usuario esté en el dashboard
        Assert.assertTrue(dashboardPage.isInDashboard(), "El usuario no llegó al Dashboard.");
    }


    /**
     * Prueba de inicio de sesión con contraseña incorrecta.
     */
    @Test
    public void testLoginWithPasswordIncorrect() {
        loginPage.loginWithCredentials("Admin", "passwordIncorrecto");

        // Validar mensaje de error
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "El mensaje de error no es correcto.");
    }

    /**
     * Prueba de inicio de sesión con campos vacíos.
     * Usa DataProvider para probar usuario vacío, contraseña vacía o ambos vacíos.
     */
    @Test(dataProvider = "dataLoginInvalid")
    public void testLoginWithEmptyFields(String user, String pass, String messageExpected) {
        loginPage.loginWithCredentials(user, pass);

        // Validar mensaje de error esperado
        Assert.assertEquals(loginPage.getErrorMessage(), messageExpected, "El mensaje de error no es correcto.");
    }

    /**
     * Proveedor de datos para login con campos vacíos.
     * @return Datos de prueba con usuario, contraseña y mensaje esperado.
     */
    @DataProvider(name = "dataLoginInvalid")
    public Object[][] dataLoginInvalid() {
        return new Object[][]{
                {"", "admin123", "Required"}, // Usuario vacío
                {"Admin", "", "Required"}, // Contraseña vacía
                {"", "", "Required"} // Ambos vacíos
        };
    }
}
