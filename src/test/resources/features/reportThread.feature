Feature: Report Thread
  As a user
  i want to report thread
  so that all user cannot see the thread

  @POSTReportThread
  Scenario Outline: POST - As a user i am able to report thread
    Given I set an endpoint for report
    When I request POST for report with <id_thread>
    Then I validate the status code <status_code>
    And I get the report type with "<result>" and "<report_type>" after reporting with <id_thread> ID Thread and user ID <user_id>

    Examples:
    |id_thread|status_code|result|report_type|user_id|
    |64       |200        |success|This_thread_contains_inappropriate_and_Fraud_elements|107|
    |64       |400        |duplicate reporting|This_thread_contains_inappropriate_and_Fraud_elements|107|
    |100      |400        |unavailable thread|This_thread_contains_inappropriate_and_Fraud_elements|0|



