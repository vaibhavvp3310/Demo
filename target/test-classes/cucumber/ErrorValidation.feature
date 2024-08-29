
@tag
Feature: Validate login error
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: login with wrong credentials should give error
    Given I landed on Ecommerce page
    When logged in to application with username <username> and password <password>
    Then error message "Incorrect email or password." should be displayed 

    Examples: 
      | username  									 | password |
      | vaibhasashiaspatel@gmail.com | Civic476 |
     
