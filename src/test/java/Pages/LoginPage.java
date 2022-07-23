package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.FileReader;

public class LoginPage extends CommonMethods {
    @FindBy(id="txtUsername")
   public WebElement userNameField;
    @FindBy(id="txtPassword")
    public WebElement passwordField;
    @FindBy(id="btnLogin")
    public WebElement loginButton;
   public LoginPage(){
        PageFactory.initElements(driver,this);
    }
    //login function
    public  void login(String userName,String password){
       //userNameField.sendKeys(userName);
      // passwordField.sendKeys(password);
        //also write like this taught by teacher
      // sendText(userNameField, FileReader.getPropertyValue("userName"));
        sendText(userNameField,userName);
        sendText(passwordField,password);
       click(loginButton);

    }

}
