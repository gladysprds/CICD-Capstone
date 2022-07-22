Feature: Like Thread
  As a user
  i want to give a like for a thread
  so that the thread can get likes from all users

  @POSTLikeAThread
  Scenario Outline: POST - As a user i want to give like on a thread
    Given I set an endpoint for liking on a thread
    When I request like on a thread by ID thread <id_thread>
    Then I validate the status code <status_code>
    And I get the "<result>" result that i like the thread <id_thread>

    Examples:
    |id_thread|status_code|result|
    |65        |200        |success|
    |65        |200        |duplicate|
    |1000      |400        |data not found|

    @GETLikeThread
    Scenario Outline: GET - As a user i want to see Like on a thread
      Given I set an endpoint count like on a thread
      When I request count like on a thread by ID thread <id_thread>
      Then I validate the status code <status_code>
      And I get the "<result>" result that i get number of like on the thread

      Examples:
        |id_thread|status_code|result|
        |65        |200        |success|
        |100      |400        |data not found|


      @GETDislikeThread
      Scenario Outline: GET - As a user i want to get dislike of thread
        Given I set an endpoint for dislike thread
        When I set an endpoint for dislike thread with ID Thread <id_thread>
        Then I validate the status code <status_code>
        And I get the "<result>" result that i get number of dislike on the thread

        Examples:
          |id_thread|status_code|result|
          |66       |200        |success|
          |1000      |400        |data not found|





