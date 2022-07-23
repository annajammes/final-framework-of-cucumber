package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Constants;
import utils.ExcelFileReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static steps.PageInitalizer.addEmployeePage;
import static utils.CommonMethods.*;

public class AddEmployee {
    @When("user clicks on pim option")
    public void user_clicks_on_pim_option() {
        WebElement pim=driver.findElement(By.id("menu_pim_viewPimModule"));
        pim.click();
    }

    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        WebElement addemp=driver.findElement(By.id("menu_pim_addEmployee"));
        addemp.click();

    }
    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        WebElement firstname1=driver.findElement(By.id("firstName"));
        firstname1.sendKeys("lllll");
        WebElement middlename1=driver.findElement(By.id("middleName"));
        middlename1.sendKeys("kkkkkk");
        WebElement lastname1=driver.findElement(By.id("lastName"));
        lastname1.sendKeys("jjjjjj");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement savebutton=driver.findElement(By.id("btnSave"));
        savebutton.click();
//this code was added later
        click(addEmployeePage.editbtn);
        selectDropdown(addEmployeePage.maritalStatusBox, "Other");
        selectDropdown(addEmployeePage.nationalityBox, "British");
        click(addEmployeePage.saveBtn);

    }
    @Then("employee added sucessfully")
    public void employee_added_sucessfully() {
        System.out.println("passed");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstname, String middlename, String lastname) {
        WebElement firstname1=driver.findElement(By.id("firstName"));
        firstname1.sendKeys(firstname);
        WebElement middlename1=driver.findElement(By.id("middleName"));
        middlename1.sendKeys(middlename);
        WebElement lastname1=driver.findElement(By.id("lastName"));
        lastname1.sendKeys(lastname);

    }
    @When("user enters mutiple data and verified the data entered")
    public void user_enters_mutiple_data_and_verified_the_data_entered(DataTable dataTable) throws InterruptedException {
      List<Map<String,String >> employeeNames = dataTable.asMaps();

       for (Map<String,String> emp:employeeNames) {
        String firstnameValue= emp.get("firstname");
            String middlenameValue= emp.get("middlename");
            String lastnameValue= emp.get("lastname");

            WebElement firstname2=driver.findElement(By.id("firstName"));
            firstname2.sendKeys(firstnameValue);
            WebElement middlename2=driver.findElement(By.id("middleName"));
            middlename2.sendKeys( middlenameValue);
            WebElement lastname2=driver.findElement(By.id("lastName"));
            lastname2.sendKeys(lastnameValue);

            WebElement savebutton=driver.findElement(By.id("btnSave"));
            savebutton.click();
            Thread.sleep(5000);
//we use this code becasuse background and hook work only once
            WebElement addemp=driver.findElement(By.id("menu_pim_addEmployee"));
            addemp.click();
            Thread.sleep(3000);
        }
    }
    @When("user enter multiple emplyee form excel file using {string} sheet and verify the data")
    public void user_enter_multiple_emplyee_form_excel_file_using_sheet_and_verify_the_data(String sheetName) throws InterruptedException {
        List<Map<String ,String >> newEmployees =ExcelFileReader.readExcelData(Constants.EXCELDATA_FILEPATH,sheetName);
       Iterator<Map<String ,String >> it =newEmployees.iterator();
       while (it.hasNext()){
        Map<String ,String >  mapNewEmp =it.next();

           WebElement firstname=driver.findElement(By.id("firstName"));
           firstname.sendKeys(mapNewEmp.get("FirstName"));
           WebElement middlename=driver.findElement(By.id("middleName"));
           middlename.sendKeys(mapNewEmp.get("MiddleName"));
           WebElement lastname=driver.findElement(By.id("lastName"));
           lastname.sendKeys(mapNewEmp.get("LastName"));

           WebElement empId=driver.findElement(By.id("employeeId"));
          String empIdValue= empId.getAttribute("value");

           WebElement phto=driver.findElement(By.id("photofile"));
           phto.sendKeys(mapNewEmp.get("Photogaraph"));
           WebElement checkBox=driver.findElement(By.id("chkLogin"));
           if(!checkBox.isSelected()){
           checkBox.click();}

           WebElement userName=driver.findElement(By.id("user_name"));
           userName.sendKeys(mapNewEmp.get("UserName"));
           WebElement pass=driver.findElement(By.id("user_password"));
           pass.sendKeys(mapNewEmp.get("Password"));
           WebElement repass=driver.findElement(By.id("re_password"));
           repass.sendKeys(mapNewEmp.get("Password"));
           WebElement savebutton=driver.findElement(By.xpath("//input[@id='btnSave']"));
           savebutton.click();
           Thread.sleep(5000);


           WebElement employeeList=driver.findElement(By.id("menu_pim_viewEmployeeList"));
           employeeList.click();
           WebElement idBox=driver.findElement(By.id("empsearch_id"));
           idBox.sendKeys(empIdValue);
           WebElement searchButton=driver.findElement(By.id("searchBtn"));
           searchButton.click();
List<WebElement> tablerow=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td"));
           String  expectedData="";
           String textvalue="";
           for (int i = 0; i < tablerow.size(); i++) {
                textvalue +=tablerow.get(i).getText().trim()+" ";

           }
           expectedData=empIdValue+" "+mapNewEmp.get("FirstName")+" "+mapNewEmp.get("MiddleName")+" "+mapNewEmp.get("LastName");
           System.out.println(expectedData);
           System.out.println(textvalue);
           Assert.assertEquals(expectedData,textvalue.trim());


//we use this code becasuse background and hook work only once
           WebElement addemp=driver.findElement(By.id("menu_pim_addEmployee"));
           addemp.click();
           Thread.sleep(3000);

       }
    }
}
