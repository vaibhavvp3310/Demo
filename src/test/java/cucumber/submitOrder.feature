
@tag
Feature: Order product from Ecommerse website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Submit order to application
    Given logged in to application with username <username> and password <password>
    When select product <productname> and add to cart
    And checkout product <productname> 
    And submit order
    Then validate order confirmation message "THANKYOU FOR THE ORDER."
    
    Examples: 
      | username  							| password  | productname  |
      | vaibhavpatel@gmail.com  | Civic476  | ZARA COAT 3  |

 

    
      
