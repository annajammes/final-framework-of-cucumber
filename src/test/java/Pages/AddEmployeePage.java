package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.CommonMethods.driver;

public class AddEmployeePage {

    @FindBy(id="firstName")
   public WebElement firstname;

  @FindBy(id="middleName")
  public WebElement middlename;

  @FindBy(id="lastName")
  public WebElement lastname;

  @FindBy(id="employeeId")
  public WebElement emp_idfromFront;

  @FindBy(xpath="//input[@id='btnSave']")
  public WebElement saveButton;


    @FindBy(id="btnSave")
  public   WebElement editbtn;

    @FindBy(id="personal_cmbMarital")
    public   WebElement maritalStatusBox;

    @FindBy(id="personal_cmbNation")
    public   WebElement nationalityBox;

  @FindBy(id="btnSave")
  public   WebElement saveBtn;
  public AddEmployeePage(){
    PageFactory.initElements(driver,this);
  }

}
