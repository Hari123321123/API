Feature: Trigger the Get API with required parameters

@getapi
Scenario: Trigger the get api request

    Given Send the endpoint
    When Trigger the getAPI and save response in object
    Then Validate the status code of get API
    And Validate the response body parameters of get API