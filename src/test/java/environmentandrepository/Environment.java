package environmentandrepository;

public class Environment {
	
	public static String post_endpoint() {
		
		String hostname = "https://reqres.in";
		String resource = "/api/users";
		String endpoint = hostname + resource;
		return endpoint;
	}
	
	public static String put_endpoint() {
		
		String hostname = "https://reqres.in";
		String resource = "/api/users/2";
		String endpoint = hostname + resource;
		return endpoint;
	}
	
	public static String patch_endpoint() {
		
		String hostname = "https://reqres.in";
		String resource = "/api/users/2";
		String endpoint = hostname + resource;
		return endpoint;
	}
	
	public static String get_endpoint() {
		
		String hostname = "https://reqres.in";
		String resource = "/api/users?page=2";
		String endpoint = hostname + resource;
		return endpoint;
	}
	
	public static String delete_endpoint() {
		
		String hostname = "https://reqres.in";
		String resource = "/api/users/2";
		String endpoint = hostname + resource;
		return endpoint;
	}

}
