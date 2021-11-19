import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLOutput;

public class LoginForm extends PageObject{


    private final String USERNAME_INVALIDO = "Wikardo";
    private final String PASSWORD_INVALIDO = "123456";

    private final String USERNAME_VALIDO = "standard_user";
    private final String PASSWORD_VALIDO = "secret_sauce";

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login_button;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement error_button;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void ingresarUsernameValido(){
        this.username.sendKeys(USERNAME_VALIDO);
    }

    public void ingresarPasswordValido(){
        this.password.sendKeys(PASSWORD_VALIDO);
    }

    public void pressLoginButton(){
        this.login_button.click();
    }

    public void ingresarUsernameInvalido(){
        this.username.sendKeys(USERNAME_INVALIDO);
    }

    public void ingresarPasswordInvalido(){
        this.password.sendKeys(PASSWORD_INVALIDO);
    }

    public String getErrorMessage(){
        return this.error_button.getText();
    }

    public String getLoginButtonMessage(){
        return this.login_button.getAttribute("value");
    }
}
