import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends PageObject {

    @FindBy(xpath = "//span[contains(text(), 'Products')]")
    private WebElement products_label;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCart_SauceBackpack;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement cart_badge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burger_menu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public String getTitle(){
        return this.products_label.getText();
    }

    public void addToCartBackpack(){
        this.addToCart_SauceBackpack.click();
    }

    public String getCardBadge(){
        return this.cart_badge.getText();
    }

    public void pressBurgerMenu(){
        this.burger_menu.click();
    }

    public void pressLogout(){
        this.logout.click();
    }

}
