Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation is successful with valid username and password
    Given command new user is selected
    When  username "kalle" and password "ellak123" are entered
    Then  system will respond with "new user registered"

  Scenario: creation fails with already taken username and valid password
    Given command new user is selected
    When  username "pekka" and password "ellak123" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When  username "aa" and password "allim123" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with valid username and too short password
    Given command new user is selected
    When  username "milla" and password "allim" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with valid username and password enough long but consisting of only letters
    Given command new user is selected
    When  username "milla" and password "allimilla" are entered
    Then  system will respond with "new user not registered"

  Scenario: can login with successfully generated account
    Given user "eero" with password "salai123" is created
    And   command login is selected
    When  username "pekka" and password "akkep" are entered
    Then  system will respond with "logged in"