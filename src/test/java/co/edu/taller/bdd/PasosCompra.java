package co.edu.taller.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import co.edu.taller.pages.CheckoutPage;
import co.edu.taller.pages.InventarioPage;
import co.edu.taller.pages.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class PasosCompra {

    private InventarioPage inventario;
    private CheckoutPage checkout;

    @Dado("que soy un cliente con sesión iniciada")
    public void clienteConSesion() {
        new LoginPage(Ganchos.driver).abrir().iniciarSesion("standard_user", "secret_sauce");
        inventario = new InventarioPage(Ganchos.driver);
        checkout = new CheckoutPage(Ganchos.driver);
    }

    @Cuando("agrego la mochila al carrito")
    public void agregarMochila() {
        inventario.agregarMochila();
    }

    @Entonces("el carrito muestra {int} producto(s)")
    public void carritoMuestra(int cantidad) {
        assertEquals(cantidad, inventario.productosEnElCarrito());
    }

    @Cuando("completo la compra con nombre {string}, apellido {string} y código postal {string}")
    public void completarCompra(String nombre, String apellido, String codigoPostal) {
        inventario.irAlCarrito();
        checkout.completarCompra(nombre, apellido, codigoPostal);
    }

    @Entonces("veo la confirmación {string}")
    public void veoConfirmacion(String texto) {
        assertTrue(checkout.mensajeDeConfirmacion().contains(texto));
    }
}
