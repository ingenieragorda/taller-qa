package co.edu.taller.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page Object: representa la pantalla de login de SauceDemo. */
public class LoginPage {

    private final WebDriver driver;

    private final By campoUsuario = By.id("user-name");
    private final By campoClave = By.id("password");
    private final By botonLogin = By.id("login-button");
    private final By mensajeError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage abrir() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    public void iniciarSesion(String usuario, String clave) {
        driver.findElement(campoUsuario).sendKeys(usuario);
        driver.findElement(campoClave).sendKeys(clave);
        driver.findElement(botonLogin).click();
    }

    public String textoDelError() {
        return driver.findElement(mensajeError).getText();
    }
}
