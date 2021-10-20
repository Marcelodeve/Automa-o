package pageObjects;

import elementMapper.LoginPageElementMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utils.Browser;

public class LoginPage extends LoginPageElementMapper {

    public LoginPage(){
        PageFactory.initElements(Browser.getCurrentDriver(), this);
    }

    public void fillEmail(){
        email.sendKeys("nodariesports@gmail.com");
    }
    public void fillPasswd(){
        passwd.sendKeys("12345");
    }
    public void clickBtnSubmitLogin(){
        SubmitLogin.click();
    }

}
