Feature: Trigger the Post API with Required parameter

@Post_API
Scenario: Trigger the API Request with valid Request-body parameters

    Given Enter NAME and JOB in Request-body
    When Send the request with payload
    Then Validate status code
    And Validate Response-body parameters

@Post_API  
Scenario Outline: Test Post API with multiple data set

    Given Enter "<NAME>" and "<JOB>" in Request-body
    When Send the request with payload
    Then Validate status code
    And Validate Response-body parameters
    
Examples:

    |NAME|JOB|
    |Shubham|dev|
    |Shrya|CA|
    |Ram|AC|
    |Ana|QA|
