Feature: Add employee

  Background:
    * user enter valid credentials and logged in

   # * user enters valid username in username textbox and  valid password in password textbox
  # * user clicks on login button
  # * admin should be log in
    When user clicks on pim option
    And user clicks on add employee button

  @dropdwn
  Scenario:test
    And user enters firstname middlename and lastname
    And user clicks on save button
    Then employee added sucessfully

  @example
  Scenario Outline: adding emplyee using scenerio outline
    And user enters "<firstname>" "<middlename>" and "<lastname>"
    And user clicks on save button
    Then employee added sucessfully
    Examples:
      |firstname|middlename|lastname|
      |testqwe  |rest      |jkill   |
      |asded    |vxcty     |vtyu    |


  @datatable
  Scenario:test
    When user enters mutiple data and verified the data entered
      |firstname|middlename|lastname|
      |testerst |rest123   |jkill   |
      |asded23  |vxcty45   |vtyu56  |
  @exceldata
  Scenario:test 1
    When user enter multiple emplyee form excel file using "EmployeeData" sheet and verify the data


@db
Scenario:test
  And user enters firstname middlename and lastname
  And user clicks on save button
  Then employee added sucessfully
    * get data from front end
    * get data from database
    * verify data form both side
