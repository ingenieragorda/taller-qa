package co.edu.taller.pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import co.edu.taller.pages.InventarioPage;
import co.edu.taller.pages.LoginPage;
import co.edu.taller.util.DriverFactory;

@Tag("ui")
class LoginTest {

    private WebDriver driver;
    private LoginPage login;

    @BeforeEach
    void abrirNavegador() {
        driver = DriverFactory.crear("LoginTest");
        login = new LoginPage(driver).abrir();
    }

    @AfterEach
    void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("CP-01 Inicio de sesión con credenciales válidas")
    void loginValido() {
        login.iniciarSesion("standard_user", "secret_sauce");

        assertEquals("Products", new InventarioPage(driver).titulo());
        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    // CP-02 y CP-03: la misma prueba con distintos datos (pruebas basadas en datos)
    @ParameterizedTest(name = "CP-02/03 {0} / {1} -> debe mostrar: {2}")
    @CsvSource({
        "standard_user,clave_incorrecta,do not match",
        "usuario_inexistente,secret_sauce,do not match",
        "locked_out_user,secret_sauce,locked out"
    })
    void loginInvalido(String usuario, String clave, String fragmentoEsperado) {
        login.iniciarSesion(usuario, clave);

        assertTrue(login.textoDelError().contains(fragmentoEsperado),
                "El mensaje de error no contiene: " + fragmentoEsperado);
    }
}
