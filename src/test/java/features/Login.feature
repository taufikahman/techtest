Feature: Login
  As a registered user
  I want to log in to the application
  So that I can access my account

  Scenario: User logs in successfully
    Given user navigates to the login page
    When  user enters a valid username and password
    And  user taps the Login button
    Then  user should be logged in successfully
