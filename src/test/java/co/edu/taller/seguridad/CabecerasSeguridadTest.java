package co.edu.taller.seguridad;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Prueba de seguridad "pasiva": hace UNA petición GET y revisa las cabeceras de la respuesta.
 * Se ejecuta con:  mvn test -Pseguridad              (sitio por defecto: testphp.vulnweb.com)
 *                  mvn test -Pseguridad -Durl=https://www.saucedemo.com
 * Si una prueba falla, NO es un error del código: es un hallazgo de seguridad.
 */
@Tag("seguridad")
class CabecerasSeguridadTest {

    private static HttpHeaders cabeceras;

    @BeforeAll
    static void pedirLaPagina() throws Exception {
        String url = System.getProperty("url", "http://testphp.vulnweb.com");
        HttpClient cliente = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest peticion = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
        cabeceras = respuesta.headers();
    }

    @Test
    @DisplayName("SEG-01 Protege contra clickjacking (X-Frame-Options)")
    void protegeContraClickjacking() {
        assertTrue(cabeceras.firstValue("X-Frame-Options").isPresent(),
                "Falta la cabecera X-Frame-Options");
    }

    @Test
    @DisplayName("SEG-02 Evita el sniffing de contenido (X-Content-Type-Options)")
    void evitaSniffing() {
        assertTrue(cabeceras.firstValue("X-Content-Type-Options").orElse("").equalsIgnoreCase("nosniff"),
                "Falta X-Content-Type-Options: nosniff");
    }

    @Test
    @DisplayName("SEG-03 Define una política de contenido (Content-Security-Policy)")
    void defineCSP() {
        assertTrue(cabeceras.firstValue("Content-Security-Policy").isPresent(),
                "Falta la cabecera Content-Security-Policy");
    }

    @Test
    @DisplayName("SEG-04 No revela la tecnología del servidor (X-Powered-By)")
    void noRevelaTecnologia() {
        assertTrue(cabeceras.firstValue("X-Powered-By").isEmpty(),
                "El servidor revela su tecnología con X-Powered-By");
    }
}
