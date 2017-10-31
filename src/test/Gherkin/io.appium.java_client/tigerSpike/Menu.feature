Feature: Menu

  Scenario: Validate Global Menu content
    Given I open tigerspike webpage
    When I dismiss Cookie Bar
    And I open Global Menu
    Then I verify Global Menu is opened
    And I validate Global Menu content
