package co.edu.taller.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page Object: carrito y proceso de pago de SauceDemo. */
public class CheckoutPage {

    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void completarCompra(String nombre, String apellido, String codigoPostal) {
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(nombre);
        driver.findElement(By.id("last-name")).sendKeys(apellido);
        driver.findElement(By.id("postal-code")).sendKeys(codigoPostal);
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
    }

    public String mensajeDeConfirmacion() {
        return driver.findElement(By.className("complete-header")).getText();
    }
}
