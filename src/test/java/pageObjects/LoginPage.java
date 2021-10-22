package pageObjects;

import elementMapper.LoginPageElementMapper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utils.Browser;

public class LoginPage extends LoginPageElementMapper {

    public LoginPage(){
        PageFactory.initElements(Browser.getCurrentDriver(), this);
    }

    @Step("Preencheu o email")
    public void fillEmail(){
        email.sendKeys("aresetmarcelo@gmail.com");
    }
    @Step("Preencheu a senha")
    public void fillPasswd(){
        passwd.sendKeys("12345");
    }
    @Step("Clicou em Sign In")
    public void clickBtnSubmitLogin(){
        SubmitLogin.click();
    }

}
