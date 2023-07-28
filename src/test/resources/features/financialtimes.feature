

  Feature: Financial Times web site testing

  Scenario: Search feature on Financial Times web site
    Given I visit the Financial Times web site
    And I click on the search button
    When I search the keyword "crypto" in the Financial Times search box
    And I should see the result


