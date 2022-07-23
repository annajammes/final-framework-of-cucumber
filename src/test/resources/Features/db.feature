Feature: db
  Background:
    * user enter valid credentials and logged in
    * user clicks on pim option
    * user clicks on add employee button
  @db
  Scenario Outline:db testing
    * user enters "<firstname>" "<middlename>" and "<lastname>"
    * user clicks on save button
    * employee added sucessfully
    Examples:
      |firstname|middlename|lastname|
      |testqwe  |rest      |jkill   |
