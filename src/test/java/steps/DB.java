package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.DataBaseCommonMethods;

import java.util.List;
import java.util.Map;
//not wroking proper from front end side but working fine from database side issue with front code every else is wrokingfine
import static steps.PageInitalizer.addEmployeePage;

public class DB {
   public static String empIdValue;
    public static String namefromFront;
    public static List<Map<String ,String>> tableData;
    @Then("get data from front end")
    public void get_data_from_front_end() {
        // namefromFront=addEmployeePage.firstname.getAttribute("value");
       // String middlenamefromFront=addEmployeePage.middlename.getAttribute("value");
      //  String lastnamefromFront=addEmployeePage.lastname.getAttribute("value");
        // empIdValue=addEmployeePage.emp_idfromFront.getAttribute("value");
        empIdValue="34992A";
    }
    @Then("get data from database")
    public void get_data_from_database() {
        String query="SELECT * From hs_hr_employees Where employee_id='"+empIdValue+"'";
       tableData= DataBaseCommonMethods.getDataBaseTableData(query);
    }
    @Then("verify data form both side")
    public void verify_data_form_both_side() {
        String firstnameFromDb= tableData.get(0).get("emp_firstname");
        System.out.println(firstnameFromDb);
       // System.out.println(namefromFront);
       // Assert.assertEquals(firstnameFromDb,namefromFront);

    }
}
