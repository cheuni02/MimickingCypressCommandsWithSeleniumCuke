Feature: As a modern day clerk
  I need to be able to effectively list all to dos
  And be able to track them

  @Chrome
  Scenario: Displays two todo items by default
    Given I am on the todos page
    Then There are 2 items by default
    And One of them is 'Pay electric bill'
    And One of them is 'Walk the dog'