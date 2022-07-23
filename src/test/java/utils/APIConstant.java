package utils;

import io.restassured.RestAssured;

public class APIConstant {
    public static final String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String CREATE_EMPLOYEE_URI=baseURI +"/createEmployee.php";
    public static final String GET_ONEEMPLOYEE_URI=baseURI +"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI=baseURI +"/updateEmployee.php";
    public static final String GET_ALLEMPLOYEE_URI=baseURI +"/getAllEmployees.php";
    public static final String DELET_EMPLOYEE_URI=baseURI +"/deleteEmployee.php";
    public static final String GENERATE_TOKEN_URI=baseURI +"/generateToken.php";

    public static final String HEADER_CONTENT_TYPE="Content_Type";
    public static final String CONTENT_TYPE_VALUE="application/json";
    public static final String HEADER_AUTHORIZATION="Authorization";

}
