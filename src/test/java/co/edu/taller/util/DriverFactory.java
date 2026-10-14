package co.edu.taller.util;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Crea el navegador. El mismo código de prueba corre en tu equipo o en la nube:
 *   mvn test                  -> Chrome local
 *   mvn test -Dheadless=true  -> Chrome local sin ventana (CI)
 *   mvn test -Dmodo=nube      -> Chrome en la nube (necesita LT_USERNAME y LT_ACCESS_KEY)
 */
public class DriverFactory {

    public static WebDriver crear(String nombrePrueba) {
        WebDriver driver = "nube".equals(System.getProperty("modo", "local"))
                ? crearNube(nombrePrueba)
                : crearLocal();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private static WebDriver crearLocal() {
        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--incognito");
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            opciones.addArguments("--headless=new", "--window-size=1366,768");
        }
        // Evita las ventanas emergentes de contraseñas de Chrome, que estorban los clics
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        opciones.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(opciones);
    }

    private static WebDriver crearNube(String nombrePrueba) {
        String usuario = System.getenv("LT_USERNAME");
        String clave = System.getenv("LT_ACCESS_KEY");
        if (usuario == null || clave == null) {
            throw new IllegalStateException("Define las variables de entorno LT_USERNAME y LT_ACCESS_KEY");
        }
        // La URL del hub aparece en el panel de la plataforma; puedes cambiarla con LT_HUB
        String hub = System.getenv().getOrDefault("LT_HUB", "https://hub.lambdatest.com/wd/hub");

        ChromeOptions opciones = new ChromeOptions();
        opciones.setPlatformName("Windows 11");
        opciones.setBrowserVersion("latest");

        Map<String, Object> lt = new HashMap<>();
        lt.put("username", usuario);
        lt.put("accessKey", clave);
        lt.put("build", "Taller-QA");
        lt.put("name", nombrePrueba);
        opciones.setCapability("LT:Options", lt);

        try {
            return new RemoteWebDriver(URI.create(hub).toURL(), opciones);
        } catch (java.net.MalformedURLException e) {
            throw new IllegalStateException("URL del hub inválida: " + hub, e);
        }
    }
}
