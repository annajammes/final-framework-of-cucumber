package steps;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.FileReader;

public class LoginSteps extends CommonMethods {

    @Given("user navigated to HRMS application")
    public void user_navigated_to_hrms_application() {
        open();
    }

    @When("user enters valid username in username textbox and  valid password in password textbox")
    public void user_enters_valid_username_in_username_textbox_and_valid_password_in_password_textbox() {
        loginpage.userNameField.sendKeys(FileReader.getPropertyValue("userName"));
loginpage.passwordField.sendKeys(FileReader.getPropertyValue("password"));

//without pagefactory we have to write like the following way
// LoginPage login=new LoginPage();
        /*WebElement usernName=driver.findElement(By.id("txtUsername"));
        usernName.sendKeys(FileReader.getPropertyValue("userName"));
        WebElement password=driver.findElement(By.id("txtPassword"));
        password.sendKeys(FileReader.getPropertyValue("password"));*/
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginpage.loginButton.click();

       /* WebElement loginButton=driver.findElement(By.id("btnLogin"));
        loginButton.click();*/
    }

    @Then("admin should be log in")
    public void admin_should_be_log_in() {
        System.out.println("test passed");
    }

    @Given("user enter valid credentials and logged in")
    public void user_enter_valid_credentials_and_logged_in() {
     loginpage.login(FileReader.getPropertyValue("userName"),FileReader.getPropertyValue("password"));
    }

}
