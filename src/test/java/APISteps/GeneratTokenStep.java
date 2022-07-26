package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GeneratTokenStep {
    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;
    @Given("JWT bearer token is generated")
    public void jwt_bearer_token_is_generated() {

        RequestSpecification generateTokenRequest=given().header("content-Type","application/json").
                body("{\n" +
                "  \"email\": \"lkji@gmail.com\",\n" +
                "  \"password\": \"123\"\n" +
                "}");
        Response response=generateTokenRequest.when().post("/generateToken.php");
       token="Bearer "+response.jsonPath().getString("token");
        System.out.println(token);

    }
}
