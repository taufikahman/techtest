Feature: Add product to cart
  Validates a user can add a product to the cart with selected options.

  Scenario: Add 2 Blue Sauce Lab Back Packs to cart
    Given user is on the product page for Sauce Lab Back Packs
    When user selects color Blue
    And user selects quantity 2
    And user adds the product to the cart
    Then the product should be added with quantity 2 and color "Blue"

