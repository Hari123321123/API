Feature: Trigger the Patch API with required parameters

@Patch_API
Scenario: Trigger the patch API with valid request body paramters

    Given Enter the NAME and JOB in Patch request body
    When Send the request with payload for Patch API
    Then Validate the status code for patch API
    And Validate the response body parameters for patch API
    
@Patch_API
Scenario Outline: Test the Patch API with multiple data set
     Given Enter "<NAME>" and "<JOB>" in patch request body
     When Send the request with payload for Patch API
     Then Validate the status code for patch API
     And Validate the response body parameters for patch API
     
Examples:
    
    |NAME|JOB|
    |Shree|PDO|
    |De|BA|
    |Sky|CEO|