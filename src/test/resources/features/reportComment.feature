Feature: Report Comment
  As a user
  i want to report comment
  so that all user cannot see the comment

  @POSTReportComment
  Scenario Outline: POST - As a user i am able to report Comment
    Given I set an endpoint for report comment
    When I request POST for report with ID Comment <id_comment>
    Then I validate the status code <status_code>
    And I get the report type with "<result>" and "<report_type>" after reporting with <id_comment> ID Comment and user ID <user_id>

    Examples:
      |id_comment|status_code|result|report_type|user_id|
      |47       |200        |success|This_thread_contains_inappropriate_and_Fraud_elements|107|
      |47       |400        |duplicate reporting|This_thread_contains_inappropriate_and_Fraud_elements|0|
      |1000      |400        |unavailable thread|This_thread_contains_inappropriate_and_Fraud_elements|0|


