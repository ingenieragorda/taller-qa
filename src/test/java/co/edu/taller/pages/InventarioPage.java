package co.edu.taller.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Page Object: pantalla de productos (inventario) de SauceDemo. */
public class InventarioPage {

    private final WebDriver driver;

    public InventarioPage(WebDriver driver) {
        this.driver = driver;
    }

    public String titulo() {
        return driver.findElement(By.className("title")).getText();
    }

    public void agregarMochila() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    public int productosEnElCarrito() {
        List<WebElement> insignia = driver.findElements(By.className("shopping_cart_badge"));
        return insignia.isEmpty() ? 0 : Integer.parseInt(insignia.get(0).getText());
    }

    public void irAlCarrito() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
