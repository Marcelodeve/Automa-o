package elementMapper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageElementMapper {

    public WebElement email;

    public WebElement passwd;

    public WebElement SubmitLogin;

    // Inicio da Aula 1 do ultimo módulo
    @FindBy (className = "login") // Esse já tinha sem ser em aula, o de baixo não
    public WebElement login;

    @FindBy(css = "#block_top_menu .sf-menu li:nth-child(3) a[title=T-shirts]")
    public WebElement menuTshirts;
}
