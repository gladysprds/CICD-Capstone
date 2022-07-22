Feature: Comment
  As a user
  i want to comment on a thread
  so that i can give my opinion on the thread

  @POSTComment
  Scenario Outline: POST - As a user i want to give a comments on a thread
    Given I set an endpoint for make a thread on a comment
    When I request comment on a thread by ID thread <id_thread>
    Then I validate the status code <status_code>
    And I get the "<result>" comment

    Examples:
   |id_thread|status_code|result|
    |61        | 200       |success|
    |1000      | 400       |failed |

    @GetCommentSByIdthread
    Scenario Outline: Get Comments By id thread
      Given I set an endpoint for GET comment by ID thread <id_thread>
      When I request GET comment by ID thread <id_thread>
      Then I validate the status code <status_code>
      And I get the "<result>" based on id thread <id_thread>

      Examples:
      |id_thread|status_code|result|
      |63|200|success|
      |2|400|data not found|


      @DeleteCommentsById
      Scenario Outline: Delete comments By Id comments
        Given I set an endpoint for delete comments by ID Comment <id_comment>
        When I request DELETE Comment by ID Comment <id_comment>
        Then I validate the status code <status_code>
        And I get the "<result>" for comment

        Examples:
          |id_comment|status_code|result|
          |46|200|success              |
          |2|400|data not found        |

