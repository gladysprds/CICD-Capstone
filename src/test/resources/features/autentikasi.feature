Feature: Autentikasi
  As a user
  I want to get in to website
  So that i must have an account


  @registration
  Scenario Outline: POST - As a user i have to be able to register
    Given I set an endpoint for new user
    When I request POST user with "<name>" and "<email>" and "<password>" and this user is "<admin>"
    Then I validate the status code <status_code>
    And I validate the "<message>" detail after register

    Examples:
      |name |email|password|admin|status_code| message |
      |adminfound|adminfound@gmail.com|adminFound1511|true|200|success|
      |          |adminfound@gmail.com|ihsandududu|true|400|required|
      |ihsan     |                    |ihsandududu|true|400|required|
      |ihsan     |adminfound@gmail.com|           |true|400|required|
      |          |                    |           |true|400|required|
      |adminfound|adminfound@gmail.com|adminFound1511|  |200|success|
      |adminfound|adminfound@gmail.com|adminFound1511|false|200|success|
      |same      |adminfound@gmail.com|adminFound1511|true|200|success|
      |adminfound|same                |adminFound1511|true|400|exist|
      |adminfound|adminfound@gmail.com|same          |true|200|success|
      |adminfound|admin               |adminFound1511|true|400|invalid email|
      |ad        |adminfound@gmail.com|adminFound1511|true|400|invalid name|
      |adminfound|adminfound@gmail.com|adminss        |true|400|invalid password|



  @login
  Scenario Outline: POST - As a user i have to be able to login
    Given I set an endpoint for logged into website
    When I request POST user with "<email>" and "<password>"
    Then I validate the status code <status_code>
    And I validate the "<result>" detail after login


    Examples:
    |email|password|status_code|result|
    |adminfound@gmail.com|adminFound1511|200|success|
    |                    |adminFound1511|400|email required|
    |adminfound@gmail.com|             |400|password required|
    |                    |             |400|email required|
    |adminfound@gmail.com|adminFound151|400|Data not found|
    |adminsfounds@gmail.com|adminFound1511|400|Data not found|
    |adminsfounds@gmail.com|adminFound151|400|Data not found|
