package graph.resources;

import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqBase;
	
	public RequestSpecification getRequestSpecification() throws Exception {
		
		if(reqBase == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqBase = new RequestSpecBuilder()
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.addHeader("Content-Type", "application/json")
			.build();
		}
		return reqBase;
	}
	
	public String getJsonPath(String response , String key ) {
		
		JsonPath js = new JsonPath(response);
		return js.get(key).toString();
	}
}
