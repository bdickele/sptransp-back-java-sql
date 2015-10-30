Feature: visas granted or denied for a request

  We check different scenarios where visas are granted or denied for a request

  Background:
    Given a rule to send food on earth
    And required visas are:
      | department          | seniority |
      | law compliance      | 50        |
      | shuttle compliance  | 60        |
    And a new request is created for that goods and destination


  Scenario: basic schema validation
    When employee 'law compliance' 60 grants his visa


