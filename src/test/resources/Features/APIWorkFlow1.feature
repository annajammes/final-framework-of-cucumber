Feature: API
  Background:
    Given JWT bearer token is generated

  @API
  Scenario: creating employee via API
    Given a request is prepared
    When making a post call to create employee
    Then verify the code 201
    And employee created have the key "Message" and the value "Employee Created"
    And employee id "Employee.employee_id" is stored as global variable

  @API
  Scenario: retiveing recently created data
    Given   request is prepared t get employee data
    When  a get call is made to get data
    Then and verify the code 200
    And employee id "employee.employee_id" must matched with  global id variable
And retrived the data from "employee" object and match with the data used to creat  employee with id "employee.employee_id"
    |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
    |flowerlily   |rose        |sunfl          |Male      |1997-07-04  |lolo      |QA           |

  @json
  Scenario: creating employee via json payload
    Given a request is prepared using jsonpayload
    When making a post call to create employee
    Then verify the code 201
    And employee created have the key "Message" and the value "Employee Created"
    And employee id "Employee.employee_id" is stored as global variable

  @dynamic
  Scenario: creating employee using more dynamic way
    Given a request is prepared using more dyamic data "zox","foz","goll","F","1997-09-04","qal","QA"
    When making a post call to create employee
    Then verify the code 201
    And employee created have the key "Message" and the value "Employee Created"
    And employee id "Employee.employee_id" is stored as global variable

  @update
  Scenario: updating the employee
    Given a request for updating the employee
    When a put call is made to update the employee
    Then staus code should be 200