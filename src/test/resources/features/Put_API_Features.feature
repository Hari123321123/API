Feature: Trigger the Put API with required parameters

@Put_API
Scenario: Trigger the API request with valid request body parameters

    Given Enter the NAME and JOB in the request body
    When Send the request with payload for put API
    Then Validate the status code for put API
    And Validate the response body parameters
    
@Put_API
Scenario Outline: Test the Put API with multiple data set
    
    Given Enter "<NAME>" and "<JOB>" in the request body
    When Send the request with payload for put API
    Then Validate the status code for put API
    And Validate the response body parameters
    
Examples:
    
    |NAME|JOB|
    |Veena|TR|
    |Bella|CEO|
    |Saum|ML|