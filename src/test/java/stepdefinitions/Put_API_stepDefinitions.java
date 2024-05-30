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

public class Put_API_stepDefinitions {
	
	String requestbody;
	String endpoint;
	Response response;
	int statuscode;
	ResponseBody responsebody;
	
	@Given("Enter the NAME and JOB in the request body")
	public void enter_the_name_and_job_in_the_request_body() {
	    requestbody = RequestRepository.put_request_body();
	    endpoint = Environment.put_endpoint();
	    //throw new io.cucumber.java.PendingException();
	}
	
	@Given("Enter {string} and {string} in the request body")
	public void enter_and_in_the_request_body(String string, String string2) {
	    requestbody = "{\r\n"
	    		+ "    \"name\": \""+string+"\",\r\n"
	    		+ "    \"job\": \""+string2+"\"\r\n"
	    		+ "}";
	    endpoint = Environment.put_endpoint();
	    //throw new io.cucumber.java.PendingException();
	}
	
	@When("Send the request with payload for put API")
	public void send_the_request_with_payload_for_put_api() {
	    response = API_Trigger.put_API_Trigger(requestbody, endpoint);
	    //throw new io.cucumber.java.PendingException();
	}
	@Then("Validate the status code for put API")
	public void validate_the_status_code_for_put_api() {
	    statuscode = response.statusCode();
	    Assert.assertEquals(statuscode, 200);
	    //throw new io.cucumber.java.PendingException();
	}
	
	@Then("Validate the response body parameters")
	public void validate_the_response_body_parameters() {
	    responsebody = response.getBody();
	    
	    System.out.println(responsebody.asPrettyString());
	    String resName = responsebody.jsonPath().getString("name");
	    String resJob = responsebody.jsonPath().getString("job");
	    String updatedAt = responsebody.jsonPath().getString("updatedAt").toString().substring(0, 10);
	    
	    JsonPath request = new JsonPath(requestbody);
	    String reqName = request.getString("name");
	    String reqJob = request.getString("job");
	    
	    LocalDateTime currentDate = LocalDateTime.now();
	    String expDate = currentDate.toString().substring(0, 10);
	    
	    Assert.assertEquals(resName, reqName);
	    Assert.assertEquals(resJob, reqJob);
	    //Assert.assertEquals(updatedAt, expDate);
	    //throw new io.cucumber.java.PendingException();
	}
	
}
