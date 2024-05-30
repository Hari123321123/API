package CommonMethods;

import environmentandrepository.RequestRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Trigger extends RequestRepository {
	
	static String headername = "Content-Type";
	static String headervalue = "application/json";
	
	public static Response post_API_Trigger(String requestbody , String endpoint) {
		
		RequestSpecification reqSpc = RestAssured.given();
		reqSpc.header(headername, headervalue);
		reqSpc.body(requestbody);
		Response response = reqSpc.post(endpoint);
		return response;
	}
	
	public static Response put_API_Trigger(String requestbody, String endpoint) {
		
		RequestSpecification obj = RestAssured.given();
		obj.header(headername, headervalue);
		obj.body(requestbody);
		Response response = obj.put(endpoint);
		return response;
	}
	
	public static Response patch_API_Trigger(String requestbody, String endpoint) {
		
		RequestSpecification obj = RestAssured.given();
		obj.header(headername, headervalue);
		obj.body(requestbody);
		Response response = obj.patch(endpoint);
		return response;
	}
	
	public static Response get_API_Trigger(String endpoint) {
		
		RequestSpecification obj = RestAssured.given();
		obj.header(headername, headervalue);
		Response response = obj.get(endpoint);
		return response;
	}
	
}
