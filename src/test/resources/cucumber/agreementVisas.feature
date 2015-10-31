Feature: visas granted or denied for a request

  We check different scenarios where visas are granted or denied for a request

  Background:
    Given a rule to send food on earth
    And required visas are:
      | department          | seniority |
      | law compliance      | 50        |
      | shuttle compliance  | 60        |
      | goods inspection    | 70        |
      | journey supervision | 40        |
    When a new request is created for that goods and destination
    Then agreement status of request is pending
    And rank of next expected agreement visa is 0
    And next expected agreement visa is 'law compliance' 50


  Scenario: all required visas are granted

    When employee 'law compliance' 50 grants his visa
    Then agreement status of request is pending
    And rank of next expected agreement visa is 1
    And next expected agreement visa is 'shuttle compliance' 60

    When employee 'shuttle compliance' 60 grants his visa
    Then agreement status of request is pending
    And rank of next expected agreement visa is 2
    And next expected agreement visa is 'goods inspection' 70

    When employee 'goods inspection' 70 grants his visa
    Then agreement status of request is pending
    And rank of next expected agreement visa is 3
    And next expected agreement visa is 'journey supervision' 40

    When employee 'journey supervision' 40 grants his visa
    Then agreement status of request is granted
    And rank of next expected agreement visa is -1
    And we don't expect any agreement visa

  Scenario: we grant all visas but with a seniority greater than expected. It's OK, it shoud pass.

    When employee 'law compliance' 60 grants his visa
    And employee 'shuttle compliance' 70 grants his visa
    And employee 'goods inspection' 80 grants his visa
    And employee 'journey supervision' 50 grants his visa
    Then agreement status of request is granted
    And rank of next expected agreement visa is -1
    And we don't expect any agreement visa

  Scenario: first expected visa is denied. Request is therefore refused.

    When employee 'law compliance' 50 denies his visa
    Then agreement status of request is refused
    And rank of next expected agreement visa is -1
    And we don't expect any agreement visa

  Scenario: employee wants to grant his visa but his seniority is not sufficient.
  An error should be raised and nothing is changed regarding status of request

    When employee 'law compliance' 40 grants his visa
    Then employee should not be allowed
    And agreement status of request is pending
    And rank of next expected agreement visa is 0
    And next expected agreement visa is 'law compliance' 50

  Scenario: employee wants to deny his visa but his seniority is not sufficient.
  An error should be raised and nothing is changed regarding status of request

    When employee 'law compliance' 40 denies his visa
    Then employee should not be allowed
    And agreement status of request is pending
    And rank of next expected agreement visa is 0
    And next expected agreement visa is 'law compliance' 50

  Scenario: employee wants to grant his visa but his department is not the good one.
  An error should be raised and nothing is changed regarding status of request

    When employee 'shuttle compliance' 80 grants his visa
    Then employee should not be allowed
    And agreement status of request is pending
    And rank of next expected agreement visa is 0
    And next expected agreement visa is 'law compliance' 50

  Scenario: employee wants to deny his visa but his department is not the good one.
  An error should be raised and nothing is changed regarding status of request

    When employee 'shuttle compliance' 80 denies his visa
    Then employee should not be allowed
    And agreement status of request is pending
    And rank of next expected agreement visa is 0
    And next expected agreement visa is 'law compliance' 50


