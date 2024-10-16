
Feature: Validate Cart Functionality

  
    
    Background: User is on amazon.in
    Given User is already loggedIn to application with  valid credentials "useYourAccount" and "Amazon@123"

  @cart
  Scenario Outline: Validate that user is able to add a product of choice to cart for purchase
    Given User search for the product <productName> willing to purchase
    When User add the product to the cart <productName>
    Then Added product <productName> should be available/visible in users-cart

    Examples: 
      | productName  |   
      | Xiaomi Smart TV A 80 cm (32) HD Ready Smart Google LED TV L32MA-AIN (Black) |   
      
      
   @cart
  Scenario Outline: Validate that user is able to remove a product of choice from cart
    Given User goes to cart page of application
    When User check for the <productName> he want to remove and remove it from cart
    Then product should not be available in cart

    Examples: 
      | productName  | 
      | Xiaomi Smart TV A 80 cm (32) HD Ready Smart Google LED TV L32MA-AIN (Black) |   
