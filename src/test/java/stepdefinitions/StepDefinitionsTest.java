package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.ParseException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitionsTest {
	private final static Logger logger = Logger.getLogger(StepDefinitionsTest.class.getName());
	public static String apiEndPointUri;
	public static String testName;
	public static String CONTENT_TYPE;
	public static String STATUS_CODE;
	public static String FILE_PATH;
	public static String REQUESTBODY;
	public static String RESPONSEBODY;
	public static Response response;
	
	@Given("^I want to set URL as \"([^\"]*)\" for test case \"([^\"]*)\"$")
	public void setAPIEndpointURL(String URL, String testCaseName) {
		String apiHostName = "https://reqres.in";
		apiEndPointUri = String.format("%s%s", apiHostName, URL);
		
		ExtentCucumberAdapter.addTestStepLog("set endpoint url");
		
		logger.info("Cucumber Hostname URL is :: " + apiEndPointUri);
		logger.info("Cucumber Test case name is :: " + testName);
	}

	@When("^I set header content type as \"([^\"]*)\"$")
	public void setHeader(String contentType) {
		if (contentType != null && !contentType.isEmpty()) {
			CONTENT_TYPE = contentType;
			
		} else {
			
		}
	}

	@And("^I hit the API with requestbody \"([^\"]*)\" and request method is \"([^\"]*)\"$")
	public void submitRequest(String requestBodyPath, String requestType) throws Throwable {
		RestAssured.baseURI = apiEndPointUri;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", CONTENT_TYPE);
		if (requestBodyPath != null && !requestBodyPath.isEmpty() && requestType.equalsIgnoreCase("PUT")
				|| requestType.equalsIgnoreCase("POST")) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH =requestBodyPath;
			
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
				
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
			if (REQUESTBODY.length() > 0) {
				request.body(REQUESTBODY);
				response = request.post();
			} else {
				
			}
		} else if (requestType.equalsIgnoreCase("GET")) {
			response = request.get();
		}
		STATUS_CODE = String.valueOf(response.getStatusCode());
		RESPONSEBODY = response.getBody().asString();
		System.out.println(STATUS_CODE );
		
	}

	@Then("^I try to verify the status code is \"([^\"]*)\"$")
	public void verifyStatusCode(String statusCode) {
		if (statusCode.equals(String.valueOf(STATUS_CODE))) {
			Assert.assertEquals(STATUS_CODE, statusCode);
			
		} else {
			Assert.assertEquals(STATUS_CODE, statusCode);
			
		}
	}

	@And("^I try to verify the response value \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verifyResponseValue(String responseKey, String value) throws Throwable {
		Object obj = responseKey;
		JSONParser parser = new JSONParser();
		JSONObject responseObject = (JSONObject) parser.parse(RESPONSEBODY);
		Object key = (Object) responseObject.get(responseKey);
		compareResponseValues(String.valueOf(value), String.valueOf(key), responseKey);
	}

	private void compareResponseValues(String expected, String actual, String responseKey) {
		
		if (expected.equals(actual)) {
			Assert.assertEquals(actual, expected);
			
		} else {
			
			Assert.assertEquals(actual, expected);
		}
	}

}
