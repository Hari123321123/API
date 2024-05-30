package stepdefinitions;

import java.util.List;
import org.testng.Assert;
import CommonMethods.API_Trigger;
import environmentandrepository.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Get_API_stepDefinitions {
	
	String endpoint;
	int statuscode;
	Response response;
	ResponseBody responsebody;
	
	@Given("Send the endpoint")
	public void send_the_endpoint() {
	    endpoint = Environment.get_endpoint();
	}
	
	@When("Trigger the getAPI and save response in object")
	public void trigger_the_get_api_and_save_response_in_object() {
	    response = API_Trigger.get_API_Trigger(endpoint);
	    
	}
	
	@Then("Validate the status code of get API")
	public void validate_the_status_code_of_get_api() {
	    statuscode = response.statusCode();
	    Assert.assertEquals(statuscode, 200);
	}
	
	@Then("Validate the response body parameters of get API")
	public void validate_the_response_body_parameters_of_get_api() {
	    responsebody = response.getBody();
	    System.out.println(responsebody.asPrettyString());
	    int exp_page = 2;
		int exp_per_page = 6;
		int exp_total = 12;
		int exp_total_pages = 2;
		int[] exp_id = { 7, 8, 9, 10, 11, 12 };
		String[] exp_email = { "michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
				"byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in" };
		
		String exp_url = "https://reqres.in/#support-heading";
		
		int res_page = responsebody.jsonPath().getInt("page");
		int res_per_page = responsebody.jsonPath().getInt("per_page");
		int res_total = responsebody.jsonPath().getInt("total");
		int res_total_pages = responsebody.jsonPath().getInt("total_pages");
		
		List<String> dataArray = responsebody.jsonPath().getList("data");
		int sizeofarray = dataArray.size();
		
		Assert.assertEquals(res_page, exp_page);
		Assert.assertEquals(res_per_page, exp_per_page);
		Assert.assertEquals(res_total, exp_total);
		Assert.assertEquals(res_total_pages, exp_total_pages);
		
		for (int i = 0; i < sizeofarray; i++) {
			Assert.assertEquals(Integer.parseInt(responsebody.jsonPath().getString("data[" + i + "].id")), exp_id[i],
					"Validation of id failed for json object at index : " + i);
			Assert.assertEquals(responsebody.jsonPath().getString("data[" + i + "].email"), exp_email[i],"Validation of email failed for json object at index : " + i);
		}
		
		Assert.assertEquals(responsebody.jsonPath().getString("support.url"), exp_url, "Validation of URL failed");
	}

}
