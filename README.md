# Taller de QA con herramientas gratuitas

Proyecto Java (Selenium + JUnit 5 + Cucumber) que prueba la tienda de práctica
[SauceDemo](https://www.saucedemo.com) y revisa la seguridad de
[testphp.vulnweb.com](http://testphp.vulnweb.com), un sitio hecho a propósito para practicar.

**No necesitas instalar nada.** Todo se edita y se ejecuta desde el navegador con GitHub.

## Cómo se usa

1. Pulsa **Use this template → Create a new repository** y crea tu copia como repositorio **público**.
2. Abre la pestaña **Actions** y, si no hay una ejecución, entra a `pruebas-qa` y pulsa **Run workflow**.
3. Para cambiar un archivo: ábrelo en GitHub, pulsa el lápiz, edita y pulsa **Commit changes**.
   Cada cambio guardado dispara las pruebas automáticamente.

## Qué hay dentro

| Carpeta | Contenido |
|---|---|
| `src/test/java/.../pages` | Page Objects (un archivo por página del sitio) |
| `src/test/java/.../pruebas` | Pruebas JUnit: `LoginTest`, `CompraTest` |
| `src/test/resources/features` | Escenarios Cucumber en español |
| `src/test/java/.../seguridad` | Pruebas de cabeceras de seguridad |
| `.github/workflows/pruebas.yml` | Flujo de integración continua (3 trabajos) |

## Casos de prueba

CP-01 login válido · CP-02/03 login inválido o bloqueado · CP-04 agregar al carrito · CP-05 compra completa ·
SEG-01..04 cabeceras de seguridad.
