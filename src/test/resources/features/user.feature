Feature: user
  As an admin
  i want to get all user
  so that i can see all user

  @getAllUser
  Scenario: GET - As a user i have to be able to get all user
  Given I set an endpoint for get all user
    When I request GET all user
    Then I validate the status code 200
    And I get a success message

    @getAllUserNoToken
      Scenario: GET - As a user i cannot get all user without token
      Given I set an endpoint for get all user
      When I request GET all user without Token
      Then I validate the status code 400
      And I get error message

    @getUserById
    Scenario Outline: GET - AS a user i want to get a user detail
      Given I set an endpoint for get detail user with "<id_endpoint>"
      When I request GET Detail User with "<id_request>"
      Then I validate the status code <status_code>
      And I get the "<result>"

      Examples:
        |id_endpoint|id_request|status_code|result|
        |99|99|200        |authorized             |
        |100|100|400       |not authorized        |
        |user01|user01|400    |bad request     |

      @getUserByIdWithNoToken
      Scenario: GET - As a user i cannot to get a user detail without token
        Given I set an endpoint for get detail a user
        When I request GET Detail user
        Then I validate the status code 400
        And I get error message

  @getUserByIdWithPostRequest
        Scenario: POST - As a user i cannot use post method to get detail user
          Given I set an endpoint for get detail a user
          When I request POST detail user
          Then I validate the status code 405
          And I get the method not allowed error

    @putUser
      Scenario Outline: PUT - As a user i can update my profile
        Given I set an endpoint for update user with "<id_user>"
        When I request UPDATE user with "<data>" and "<id_user>" and "<token>"
        Then I validate the status code <status_code>
        And I get the "<result>" update user same with "<data>"

      Examples:
      |id_user|data|token|status_code|result|
      |    105 |cahyo kumolo|valid|200   |success|
      |     2 |kombat    |invalid|400 |invalid token|








