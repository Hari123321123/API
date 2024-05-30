package environmentandrepository;

import java.io.IOException;
import java.util.ArrayList;
import CommonMethods.Utilities;

public class RequestRepository extends Environment {
	
	public static String post_param_requestbody(String testCaseName) throws IOException {
		
		ArrayList<String> data = Utilities.ReadExcelData("Post_API", testCaseName);
		String key1 = data.get(1);
		String req_name = data.get(2);
		String key2 = data.get(3);
		String req_job = data.get(4);
		String requestbody = "{\r\n"
	     		+ "    \""+key1+"\": \""+req_name +"\",\r\n"
	     		+ "    \""+key2+"\": \""+req_job+"\"\r\n"
	     		+ "}";
		return requestbody;
		
	}
	
	public static String post_request_body() {
		
		String requestbody = "{\r\n"
	     		+ "    \"name\": \"morpheus\",\r\n"
	     		+ "    \"job\": \"leader\"\r\n"
	     		+ "}";
		return requestbody;
	}
	
	public static String put_request_body() {
		
		String requestbody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		return requestbody;
	}
	
	public static String patch_request_body() {
		String requestbody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		return requestbody;
	}
	
	public static String get_request_body() {
		String requestbody = "";
		return requestbody;
	}

}
