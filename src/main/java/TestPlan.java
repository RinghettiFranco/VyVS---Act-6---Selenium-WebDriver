import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Login exitoso")
    public static void loginExitoso(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarUsernameValido();
        loginForm.ingresarPasswordValido();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(), "PRODUCTS");
    }

    @Test(testName = "Agregar item al carrito")
    public static void verificarItemAgregado(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarUsernameValido();
        loginForm.ingresarPasswordValido();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCartBackpack();
        Assert.assertEquals(productsPage.getCardBadge(), "1");
    }

    @Test(testName = "Credenciales incorrectas - Verificar login no válido (TC 1)")
    public static void loginInvalido(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarUsernameInvalido();
        loginForm.ingresarPasswordInvalido();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(loginForm.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "Desloguearse correctamente (TC 2)")
    public static void logoutExitoso(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarUsernameValido();
        loginForm.ingresarPasswordValido();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.pressBurgerMenu();
        productsPage.pressLogout();

        Assert.assertEquals(loginForm.getLoginButtonMessage(), "Login");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
