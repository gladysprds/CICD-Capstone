Feature: Topic
  As a user
  i want to get topic
  so that i can categorize the thread

  @PostTopic
  Scenario Outline: POST - As a user i am able to make a topic of thread
    Given I set an endpoint for make a topic
    When I request "<data>" for POST topic with "<token>"
    Then I validate the status code <status_code>
    And I validate the "<topic_detail>"

    Examples:
    |data|token|status_code|topic_detail|
    |Manga|valid|200       |success     |
    |Health|valid|500       |duplicate  |
    |      |valid|500       |data_not_found|
    |Manga|invalid|400       |invalid token     |

  @getAllTopic
  Scenario: GET - As a user i have to be able to get all topic
    Given I set an endpoint for get all topic
    When I request GET all topic
    Then I validate the status code 200
    And I get a success message for get all user

    @getAllTopicWithInvalidToken
    Scenario: GET - As a user i cannot get list topic with invalid token
      Given I set an endpoint for get all topic
      When I request GET all topic with invalid token
      Then I validate the status code 400
      And I get a error message for get all user

      @getTopicById
      Scenario Outline: GET - As a user i have to be able to get topic by id
        Given I set an endpoint get topic by "<id_topic>"
        When I request GET topic with "<id_topic>" and "<token>"
        Then I validate the status code <status_code>
        And I get the "<result>" for GET topic

        Examples:
        |id_topic|token|status_code|result|
        |   1    |valid|   200     |   success   |
        |  100   |valid|  400      |  data_not_found |
        |e-sport |valid|    400    |    bad_request  |
        |   1    |invalid|   400     |   invalid token   |


        @PutTopic
        Scenario Outline: PUT - As a user i want to update topic name for thread
          Given I set an endpoint for update topic with <id_topic>
          When I request data "<data>" with PUT and <id_topic> for topic name
          Then I validate the status code <status_code>
          And I validate that the topic has been changed into "<data>" and validate the "<response>"

          Examples:
         |id_topic|data|status_code|response|
         |1|Drama Korea|200|success|
         |1000|Drama Turki|400|data not found|



