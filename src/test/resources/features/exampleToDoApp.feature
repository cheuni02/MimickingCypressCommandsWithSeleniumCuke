Feature: As a modern day clerk
  I need to be able to effectively list all to dos
  And be able to track them

  Background:
    Given I am on the todos page

  @Chrome
  Scenario: Displays two todo items by default
    Then There are 2 items by default
    And One of them is 'Pay electric bill'
    And One of them is 'Walk the dog'

  @Chrome
  Scenario: Can add new todo items
    When I type a new Item 'Feed the cat'
    And Hit Enter key to add
    Then There are now 3 items
    And Last item is 'Feed the cat'

  @Chrome
  Scenario: Can check off an item as completed
    When I add a new item 'Go to dentist'
    And Check it off as completed
    Then Item will appear with a strikethrough

  @Chrome
  Scenario: Can filter for uncompleted tasks
    Given Item 'Pay electric bill' is checked as completed
    When I filter for uncompleted tasks
    Then Only 'Walk the dog' shows