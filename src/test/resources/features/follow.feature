Feature: Following
  As a user
  i want to follow account
  so that i can see all activity of the account

  @POSTFollowing
  Scenario Outline: POST - As a user i am able to follow another account
    Given I set an endpoint for following
    When I request POST for following with "<token>" and follow <user_follow>
    Then I validate the status code <status_code>
    And I get the "<result>" with <id_user> and <user_follow> after following

    Examples:
    |token|user_follow|status_code|id_user|result|
    |valid|  103       |  200       |108|success|
    |valid|  103       |200         |108|two times following|
    |valid|  1000    |400        |108|data_not_found |
    |valid|   108         |400         |108  | CAN'T_FOLLOW_YOURSELF|
    |invalid|  6     |400         |12|invalid token  |


    @GETFollowingById
    Scenario Outline: GET - As a user i am able to get following by Id following
      Given I set an endpoint for GET following with <id_following>
      When I request GET for following with <id_following> and "<token>" token
      Then I validate the status code <status_code>
      And I get the "<result>" with id_user <id_user> and id for following user <user_follow>

      Examples:
      |id_following|token|status_code|result|id_user|user_follow|
      |22          |valid|200        |success|  110  |103        |
      |400         |valid|400        |data_not_found|0|0     |
      |1           |invalid|400      |invalid token |0|0     |


      @GetFollowingOnAccount
      Scenario Outline: GET - As a user i am able to get followers of an account
        Given I set an endpoint for GET following for an account
        When I request GET following on an account with token "<token>"
        Then I validate the status code <status_code>
        And I get the "<result>" with id user <id_user> and id user followed <user_follow>

        Examples:
        |token|status_code|result|id_user|user_follow|
        |token with following user|200   |success|108    |103      |
        |token without following user|400|data not found|106|0|