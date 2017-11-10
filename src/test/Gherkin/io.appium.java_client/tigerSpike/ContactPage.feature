Feature: Contact Page

  Scenario: Offices List Validation
    Given I open tigerspike webpage
    When I dismiss Cookie Bar
    And I click footer 'Office' link
    Then I verify Contacts page is opened
    And I validate number of Tigerspike offices around the world is '9'
