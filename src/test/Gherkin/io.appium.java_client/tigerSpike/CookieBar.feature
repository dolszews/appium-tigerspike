Feature: CookieBar

  Scenario: Dismiss Cookie Bar
    Given I open tigerspike webpage
    Then I verify Cookie Bar is displayed
    When I dismiss Cookie Bar
    Then Cookie Bar is not displayed