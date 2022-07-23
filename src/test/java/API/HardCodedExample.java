package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExample {
    String baseURI=RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTgyODYzMDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1ODMyOTUwMSwidXNlcklkIjoiNDI0OCJ9.rM9gEIkGkvPnKLqpv0WaGLc3cZpv3zK_g9oYgsQA8gs";
   public  static  String empFromeCreate_id;
    //if we make emp id above static its work
    @Test
    public void bgetEmployee(){

        RequestSpecification prepareRequest=given().header("content-Type","application/json").
                header("Authorization",token).
                queryParam("employee_id",empFromeCreate_id);
        Response response= prepareRequest.when().get("/getOneEmployee.php");
        //System.out.println(response.asString());
        response.prettyPrint();//to print in
        response.then().assertThat().statusCode(200);
       String empFromgetEmployee_id=response.jsonPath().getString("employee.employee_id");
       boolean comparingId=empFromgetEmployee_id.contentEquals(empFromeCreate_id);
        Assert.assertTrue(comparingId);
        String lastname=response.jsonPath().getString("employee.emp_lastname");
        Assert.assertTrue(lastname.contentEquals("cat"));

    }
    @Test
    public void acreatEmployee(){
        RequestSpecification prepareRequest=given().header("content-Type","application/json").
                header("Authorization",token).body("{\n" +
                "  \"emp_firstname\": \"dogi\",\n" +
                "  \"emp_lastname\": \"cat\",\n" +
                "  \"emp_middle_name\": \"rati\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1999-07-04\",\n" +
                "  \"emp_status\": \"permanent\",\n" +
                "  \"emp_job_title\": \"fee\"\n" +
                "}");
        Response response= prepareRequest.when().post("/createEmployee.php");
        response.prettyPrint();
       empFromeCreate_id= response.jsonPath().getString("Employee.employee_id");//to get specific info we use json path
         System.out.println(empFromeCreate_id);
        //when all these asertion passed nothin will be printed in console from assertion part
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_middle_name",equalTo("rati"));
        response.then().assertThat().body("Message",equalTo("Employee Created"));
        response.then().assertThat().header("Server",equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));
    }
    @Test
    public void cupdateEmployee(){
        RequestSpecification prepareRequest=given().header("content-Type","application/json").
                header("Authorization",token).body("{\n" +
                "  \"employee_id\": \""+ empFromeCreate_id +"\",\n" +
                "  \"emp_firstname\": \"fff\",\n" +
                "  \"emp_lastname\": \"go\",\n" +
                "  \"emp_middle_name\": \"sss\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2001-11-04\",\n" +
                "  \"emp_status\": \"QA\",\n" +
                "  \"emp_job_title\": \"terc\"\n" +
                "}");
        Response response=prepareRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().body("Message",equalTo("Employee record Updated"));
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void dgetUpdatedEmployee(){

        RequestSpecification prepareRequest=given().header("content-Type","application/json").
                header("Authorization",token).
                queryParam("employee_id",empFromeCreate_id);
        Response response= prepareRequest.when().get("/getOneEmployee.php");
        //System.out.println(response.asString());
        response.prettyPrint();//to print all the response
        response.then().assertThat().statusCode(200);

        String lastname=response.jsonPath().getString("employee.emp_lastname");
        Assert.assertTrue(lastname.contentEquals("go"));

    }
    @Test
    public void egetAllEmployees(){
        RequestSpecification prepareRequest=given().header("content-Type","application/json").
                header("Authorization",token);
        Response response= prepareRequest.when().get("/getAllEmployees.php");
      String allEmployees=  response.prettyPrint();
        JsonPath js=new JsonPath(allEmployees);
      int count=  js.getInt("Employees.size()");
        System.out.println(count); //to print specific thing not all
        for (int i = 0; i < count; i++) {
            String employeeIDs=js.getString("Employees["+ i +"].employee_id");
            System.out.println(employeeIDs);

        }

    }

}
