#  Proyecto de Pruebas Automatizadas - OrangeHRM  

Este repositorio contiene pruebas automatizadas para **OrangeHRM**, utilizando **Selenium WebDriver** y el patrón **Page Object Model (POM)** con **TestNG** como framework de pruebas.  

## Tecnologías Utilizadas  
- **Lenguaje:** Java  
- **Framework de pruebas:** TestNG  
- **Automatización Web:** Selenium WebDriver  
- **Patrón de diseño:** Page Object Model (POM)  
- **Gestor de dependencias:** Maven  
- **Manejo de WebDriver:** WebDriverManager  

## Estructura del Proyecto  
```
📚 orangehrm-tests (Proyecto raíz)
├── 📚 src/main/java/com/bdb/automated/testing/pages (Page Objects - POM)
│   ├── BasePage.java (Acciones comunes a todas las páginas)
│   ├── LoginPage.java (Elementos y acciones de login)
│   ├── DashboardPage.java (Validaciones después de login)
│
├── 📚 src/test/java/com/bdb/automated/testing/test (Casos de prueba en TestNG)
│   ├── BaseTest.java (Configuración general de WebDriver)
│   ├── LoginTest.java (Casos de prueba automatizados para login)
│
├── 📚 src/test/resources (Configuraciones externas)
│   ├── config.properties (URLs, credenciales, etc.)
│
├── pom.xml (Dependencias de Maven: Selenium, TestNG, WebDriverManager)
```

##  Instalación y Configuración  

### 1️⃣ Clonar el Repositorio  
```bash
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio
```

### 2️⃣ Instalar Dependencias  
```bash
mvn clean install
```

### 3️⃣ Configurar el Proyecto  
En `src/test/resources/config.properties`, define los valores de configuración, como:  
```
base.url=https://opensource-demo.orangehrmlive.com
browser=chrome
```

### 4️⃣ Ejecutar las Pruebas  
```bash
mvn test
```

## 📊 Reportes  
Para generar reportes con **Allure**, usa:  
```bash
mvn allure:serve
```

## 🛠️ Integración Continua  
Este proyecto está preparado para ejecutarse en **GitHub Actions**, ejecutando pruebas en cada push o pull request.


