package co.edu.taller.pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import co.edu.taller.pages.CheckoutPage;
import co.edu.taller.pages.InventarioPage;
import co.edu.taller.pages.LoginPage;
import co.edu.taller.util.DriverFactory;

@Tag("ui")
class CompraTest {

    private WebDriver driver;

    @BeforeEach
    void abrirNavegador() {
        driver = DriverFactory.crear("CompraTest");
        new LoginPage(driver).abrir().iniciarSesion("standard_user", "secret_sauce");
    }

    @AfterEach
    void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("CP-04 Agregar un producto al carrito")
    void agregarAlCarrito() {
        InventarioPage inventario = new InventarioPage(driver);
        inventario.agregarMochila();

        assertEquals(1, inventario.productosEnElCarrito());
    }

    @Test
    @DisplayName("CP-05 Completar una compra de principio a fin")
    void compraCompleta() {
        InventarioPage inventario = new InventarioPage(driver);
        inventario.agregarMochila();
        inventario.irAlCarrito();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.completarCompra("Ana", "Pérez", "110111");

        assertTrue(checkout.mensajeDeConfirmacion().contains("Thank you"));
    }
}
