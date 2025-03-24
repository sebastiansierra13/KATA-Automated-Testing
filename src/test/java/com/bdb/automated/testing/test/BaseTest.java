package com.bdb.automated.testing.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * BaseTest: Configura WebDriver antes de cada prueba.
 * Permite seleccionar navegador y modo de ejecución (headless o normal).
 */
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        // Cargar configuración desde config.properties
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("src/test/resources/config.properties");
        properties.load(file);
        String navegador = properties.getProperty("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless"));

        // Inicializar WebDriver según el navegador y modo de ejecución
        driver = getDriver(navegador, headless);

        // Maximizar ventana y abrir la URL de prueba
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
    }

    /**
     * Método Factory para inicializar WebDriver según el navegador y modo de ejecución.
     * @param navegador Nombre del navegador (chrome, firefox, edge).
     * @param headless Indica si debe ejecutarse en modo headless.
     * @return WebDriver configurado.
     */
    private WebDriver getDriver(String navegador, boolean headless) {
        switch (navegador) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException("Navegador no soportado: " + navegador);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
