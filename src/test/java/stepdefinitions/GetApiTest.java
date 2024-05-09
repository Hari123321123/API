package stepdefinitions;

import static io.restassured.RestAssured.given;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetApiTest {
   
    Response response;
   
    @Given("^the valid endpoint to fetch users$")
    public void setupEndpoint()
    {
  RestAssured.baseURI="https://reqres.in/";
    RestAssured.basePath="/api/users";
    }
   
    @When("the request is send to server with page number as “{int}”")
    public void the_request_is_send_to_server_with_page_number_as(Integer pageNumber) {
    	 response = given().
    			    queryParam("page",pageNumber).
    			    when().
    			    get().
    			    then().
    			    contentType(ContentType.JSON).
    			    extract().response();  
    	 
    }
    @Then("^validate the response of first user record having email as \"([^\"]*)\"$")
    public void validateUserData(String emailID)
    { 
    	
    	System.out.println("  response body  "+response.asPrettyString());
  String userEmail = response.path("data[0].email");
  Assert.assertEquals(userEmail, emailID);  
  System.out.println("  response body parameter email validated successfully");
  
    }
   
   
}