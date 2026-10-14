package co.edu.taller.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import co.edu.taller.pages.InventarioPage;
import co.edu.taller.pages.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class PasosLogin {

    private LoginPage login;

    @Dado("que estoy en la página de login")
    public void abrirLogin() {
        login = new LoginPage(Ganchos.driver).abrir();
    }

    @Cuando("inicio sesión con usuario {string} y clave {string}")
    public void iniciarSesion(String usuario, String clave) {
        login.iniciarSesion(usuario, clave);
    }

    @Entonces("veo la página de productos")
    public void veoProductos() {
        assertEquals("Products", new InventarioPage(Ganchos.driver).titulo());
    }

    @Entonces("veo un mensaje de error que contiene {string}")
    public void veoError(String fragmento) {
        assertTrue(login.textoDelError().contains(fragmento));
    }
}
