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

public class Post_API_stepDefinitions {

	String requestbody;
	String endpoint;
	Response response;
	int statusCode;
	ResponseBody responsebody;

	@Given("Enter NAME and JOB in Request-body")
	public void enter_name_and_job_in_request_body() {
		requestbody = RequestRepository.post_request_body();
		endpoint = Environment.post_endpoint();
		//throw new io.cucumber.java.PendingException();
	}
	
	@Given("Enter {string} and {string} in Request-body")
	public void enter_and_in_request_body(String string, String string2) {
		requestbody = "{\r\n"
				+ "    \"name\": \""+string+"\",\r\n"
				+ "    \"job\": \""+string2+"\"\r\n"
				+ "}";
		endpoint = Environment.post_endpoint();
	    //throw new io.cucumber.java.PendingException();
	}

	@When("Send the request with payload")
	public void send_the_request_with_payload() {
		response = API_Trigger.post_API_Trigger(requestbody, endpoint);
		//throw new io.cucumber.java.PendingException();
	}

	@Then("Validate status code")
	public void validate_status_code() {
		statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 201, "Correct status code not found even after retrying for 5 times");
		//throw new io.cucumber.java.PendingException();
	}

	@Then("Validate Response-body parameters")
	public void validate_response_body_parameters() {
		responsebody = response.getBody();
		 System.out.println(responsebody.asPrettyString());
		String resName = responsebody.jsonPath().getString("name");
		String resJob = responsebody.jsonPath().getString("job");
		String resId = responsebody.jsonPath().getString("id");
		String createdAt = responsebody.jsonPath().getString("createdAt");
		createdAt = createdAt.toString().substring(0, 10);

		JsonPath jspreq = new JsonPath(requestbody);
		String reqName = jspreq.getString("name");
		String reqJob = jspreq.getString("job");

		LocalDateTime currentDate = LocalDateTime.now();
		String expDate = currentDate.toString().substring(0, 10);

		Assert.assertEquals(resName, reqName, "Name in responsebody is not same as in the RequestBody");
		Assert.assertEquals(resJob, reqJob, "Job in responsebody is not same as in the RequestBody");
		Assert.assertNotNull(resId, "Id in the ResponseBody is found to be null");
		//Assert.assertEquals(createdAt, expDate, "Created Date in responsebody is not same as in the RequestBody");
		//throw new io.cucumber.java.PendingException();
	}

}
