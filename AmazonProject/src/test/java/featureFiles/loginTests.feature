#
#Feature: User Login Functionality
  #
  #Background: User visit the application-amazon.in
    #Given User is on the application landing page
#
  #@login
  #Scenario Outline: Validate user login with valid credetials
    #Given User goes to signinpage of application
    #When User enters <number> and <password> and click signin
    #Then User should be on homePage of application that is <url>
#
    #Examples: 
      #| number     | password 	| url  																	 |
      #| 7972089662 | Amazon@123 | https://www.amazon.in/?ref_=nav_signin |
      #| 7972089662 | Amazon@123 | https://www.amazon.in/?ref_=nav_signin |
#
      #
      #
  #@login
  #Scenario Outline: Validate user login with invalid mobileNumber
    #Given User goes to signinpage of application
    #When User enters invalidNumber <number> 
    #Then User should get error message <message>
#
    #Examples: 
      #| number      | message  		           |
      #| 8765432199  | incorrect phone number |
      #| 6754317899  | incorrect phone number |
      #
  #@login
  #Scenario Outline: Validate user login with empty credentials
    #Given User goes to signinpage of application
    #When User enters emptyNumber <number> 
    #Then User should stay on same signin-page with <url>
#
    #Examples: 
      #| number       | url  				   		      |
      #|              | www.amazon.in/ap/signin? |
      #|              | www.amazon.in/ap/signin? |
      #
      #
      #
    #@login
    #Scenario Outline: Validate user should be signin-page once logged-out
    #Given User goes to signinpage of application
    #When User enters <number> and <password> and click signin
    #And User clicks on signOut
    #Then User should redirected to signin-page with <url>
#
    #Examples: 
      #| number       | password   | url  				   		       |
      #| 7972089662   | Amazon@123 | www.amazon.in/ap/signin? |
      #| 7972089662   | Amazon@123 | www.amazon.in/ap/signin? |
      #
      