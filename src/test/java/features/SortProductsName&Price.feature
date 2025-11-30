Feature: Sort products

  Scenario: Sort products by name in descending order
    Given user is on the products page
    When user selects sort option Name Z to A
    Then products should be displayed in descending alphabetical order

  Scenario: Sort products by price in ascending order
    Given user is on the products page
    When user selects sort option Price Low to High
    Then products should be displayed from the lowest price to the highest price
