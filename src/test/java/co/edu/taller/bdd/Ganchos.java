package co.edu.taller.bdd;

import org.openqa.selenium.WebDriver;

import co.edu.taller.util.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/** Abre el navegador antes de cada escenario y lo cierra al terminar. */
public class Ganchos {

    // Simple para el taller: un navegador compartido por los pasos del escenario en curso
    static WebDriver driver;

    @Before
    public void abrirNavegador(Scenario escenario) {
        driver = DriverFactory.crear(escenario.getName());
    }

    @After
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
