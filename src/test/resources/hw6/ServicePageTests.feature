Feature: Service Page tests

  Scenario: Service Page Interface Test
    Given I am on "Home Page"
    Then Browser title should be 'Home Page'
    When I login as user 'PITER CHAILOVSKII'
    Then User name should be as for user 'PITER CHAILOVSKII'
    And Home page interface contains all needed elements
    And Service dropdown contains options:
      |Support|
      |Dates|
      |Complex Table|
      |Simple Table|
      |User Table|
      |Table with pages|
      |Different Elements|
      |Performance|
    And Service dropdown in left section contains options:
      |Support|
      |Dates|
      |Complex Table|
      |Simple Table|
      |User Table|
      |Table with pages|
      |Different Elements|
      |Performance|
    Given I am on the Different Elements page
    Then Different elements page interface contains all needed elements
    And There is Right Section
    And There is Left Section
    When I select checkboxes Wind and Water
    Then Wind and Water checkboxes should have individual log row and value is corresponded to the status of checkbox
    When I select ratio Selen
    Then The log should have individual entry with value which is corresponded to the status of the radiobutton Selen
    When I select Yellow in dropdown
    Then The log should have individual entry with value about selected Yellow color
    When I unselect checkboxes Wind and Water
    Then The log should have individual entry with value which is corresponded to the status of the Wind and Water checkboxes




