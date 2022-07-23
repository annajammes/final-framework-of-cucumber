package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstant;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class APIWorkFlowSteps {
public static String employee_id;
  public  RequestSpecification request;
  public  Response response;
    @Given("a request is prepared")
    public void a_request_is_prepared() {
        request=given().header(APIConstant.HEADER_CONTENT_TYPE,APIConstant.CONTENT_TYPE_VALUE).
                header(APIConstant.HEADER_AUTHORIZATION,GeneratTokenStep.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @Given("a request is prepared using jsonpayload")
    public void a_request_is_prepared_using_jsonpayload() {
        request=given().header(APIConstant.HEADER_CONTENT_TYPE,APIConstant.CONTENT_TYPE_VALUE).
                header(APIConstant.HEADER_AUTHORIZATION,GeneratTokenStep.token).
                body(APIPayloadConstants.creatBodyWithJson());
    }
    @Given("a request is prepared using more dyamic data {string},{string},{string},{string},{string},{string},{string}")
    public void a_request_is_prepared_using_more_dyamic_data(String emp_firstname, String emp_lastname,
                                                             String emp_middle_name,
                                                             String emp_gender,
                                                             String emp_birthday, String emp_status,
                                                             String emp_job_title) {
        request=given().header(APIConstant.HEADER_CONTENT_TYPE,APIConstant.CONTENT_TYPE_VALUE).
                header(APIConstant.HEADER_AUTHORIZATION,GeneratTokenStep.token).
                body(APIPayloadConstants.payLoadMoreDynamic(emp_firstname,emp_lastname,emp_middle_name,emp_gender,emp_birthday,emp_status,emp_job_title));
        System.out.println(request);
    }
    @When("making a post call to create employee")
    public void making_a_post_call_to_create_employee() {
        response=request.when().post(APIConstant.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }
    @Then("verify the code {int}")
    public void verify_the_code(int statuscode) {

        response.then().assertThat().statusCode(statuscode);
    }
    @Then("employee created have the key {string} and the value {string}")
    public void employee_created_have_the_key_and_the_value(String message, String messageCreated) {
        response.then().assertThat().body(message,equalTo(messageCreated));
    }

    @Then("employee id {string} is stored as global variable")
    public void employee_id_is_stored_as_global_variable(String empID) {
        employee_id=response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }
    //-------------------------------
    @Given("request is prepared t get employee data")
    public void request_is_prepared_t_get_employee_data() {
        request=given().header(APIConstant.HEADER_CONTENT_TYPE,APIConstant.CONTENT_TYPE_VALUE).
                header(APIConstant.HEADER_AUTHORIZATION,GeneratTokenStep.token).queryParam(" employee_id", employee_id);
    }
    @When("a get call is made to get data")
    public void a_get_call_is_made_to_get_data() {
response=request.when().get(APIConstant.GET_ONEEMPLOYEE_URI);
    }
    @Then("and verify the code {int}")
    public void and_verify_the_code(int statuscode) {
        response.then().assertThat().statusCode(statuscode);
    }
    @Then("employee id {string} must matched with  global id variable")
    public void employee_id_must_matched_with_global_id_variable(String empID) {
      String temproryEmpId=response.jsonPath().getString(empID);
        Assert.assertEquals(temproryEmpId,employee_id);
    }
    @Then("retrived the data from {string} object and match with the data used to creat  employee with id {string}")
    public void retrived_the_data_from_object_and_match_with_the_data_used_to_creat_employee_with_id
            (String empObject, String responseEmpId, DataTable dataTable) {
//string.class is the default format we use in datatable
        //this data coming from feature file
        List<Map<String ,String >> expecteddata=dataTable.asMaps(String.class,String.class);
        //folowing data coming from database
       Map<String ,String > actualData =response.body().jsonPath().get(empObject);

        for (Map<String,String> map:expecteddata) {
            Set<String> keys=map.keySet();
            for (String key:keys) {
                String expectedValue=map.get(key);
                String actualValue=actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);

            }

        }
        String empid=response.body().jsonPath().getString(responseEmpId);
        Assert.assertEquals(empid,employee_id);

    }
//-------------------------------------------------------------------
    @Given("a request for updating the employee")
    public void a_request_for_updating_the_employee() {
        request=given().header(APIConstant.HEADER_CONTENT_TYPE,APIConstant.CONTENT_TYPE_VALUE).
                header(APIConstant.HEADER_AUTHORIZATION,GeneratTokenStep.token).
                body(APIPayloadConstants.updateempBody());
    }
    @When("a put call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
response=request.when().put(APIConstant.UPDATE_EMPLOYEE_URI);
    }
    @Then("staus code should be {int}")
    public void staus_code_should_be(int statuscode) {
response.then().assertThat().statusCode(statuscode);
    }



}
