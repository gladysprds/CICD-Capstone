Feature: Save Thread
  As a user
  i want to save thread
  so that i can see the thread again

  @POSTSaveThread
  Scenario Outline: POST - As a user i am able to save a thread
    Given I set an endpoint for save a thread
    When I request POST for saving thread with <id_thread> ID thread
    Then I validate the status code <status_code>
    And I get the "<result>" with <id_user> ID User and ID Thread <id_thread> after saving thread

    Examples:
    |id_thread|status_code|result|id_user|
    |65       |200        |success|107    |
    |65       |400        |YOU_HAVE_BEEN_SAVE_THIS_THREAD|107|
    |1000      |400        |data_not found|0                |