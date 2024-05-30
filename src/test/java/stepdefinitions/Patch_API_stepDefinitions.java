package stepdefinitions;

import java.time.LocalDateTime;

import org.testng.Assert;
import CommonMethods.API_Trigger;
import environmentandrepository.Environment;
import environmentandrepository.RequestRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Patch_API_stepDefinitions {
	
	String requestbody;
	String endpoint;
	int statuscode;
	Response response;
	ResponseBody responsebody;
	
	@Given("Enter the NAME and JOB in Patch request body")
	public void enter_the_name_and_job_in_patch_request_body() {
	    requestbody = RequestRepository.patch_request_body();
	    endpoint = Environment.patch_endpoint();
	}
	
	@Given("Enter {string} and {string} in patch request body")
	public void enter_and_in_patch_request_body(String string, String string2) {
	    requestbody = "{\r\n"
	    		+ "    \"name\": \""+string+"\",\r\n"
	    		+ "    \"job\": \""+string2+"\"\r\n"
	    		+ "}";
	    endpoint = Environment.patch_endpoint();
	}
	
	@When("Send the request with payload for Patch API")
	public void send_the_request_with_payload_for_patch_api() {
	    response = API_Trigger.patch_API_Trigger(requestbody, endpoint);
	}
	
	@Then("Validate the status code for patch API")
	public void validate_the_status_code_for_patch_api() {
	    statuscode = response.statusCode();
	    Assert.assertEquals(statuscode, 200);
	}
	@Then("Validate the response body parameters for patch API")
	public void validate_the_response_body_parameters_for_patch_api() {
	    responsebody = response.getBody();
	    System.out.println(responsebody.asPrettyString());
	    String resName = responsebody.jsonPath().getString("name");
	    String resJob = responsebody.jsonPath().getString("job");
	    String resDate = responsebody.jsonPath().getString("updatedAt").toString().substring(0, 10);
	    
	    JsonPath request = new JsonPath(requestbody);
	    String reqName = request.getString("name");
	    String reqJob = request.getString("job");
	    
	    LocalDateTime currentDate = LocalDateTime.now();
	    String expDate = currentDate.toString().substring(0, 10);
	    
	    Assert.assertEquals(resName, reqName);
	    Assert.assertEquals(resJob, reqJob);
	    Assert.assertEquals(resDate, expDate);
	}

}
