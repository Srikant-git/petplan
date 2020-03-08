# new feature
@SpiGeneric
Feature:Spi Generic test cases

@BasicWorkFlow
  Scenario Outline: Login-<App>
    # Login Page
    Given I login to the 'SPI' application

    Examples:
      | App         |
      | SPI Generic |

  Scenario Outline: Scenario - <testcase>
    When I click on 'New Customer & Quote' under Quote/Policy
    And I fill all the required fields in customer information



  Examples:
          | testcase |
          | SPI001_CreateANewCustomer    |
