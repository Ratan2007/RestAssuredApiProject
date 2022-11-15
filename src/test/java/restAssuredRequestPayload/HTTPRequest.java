package restAssuredRequestPayload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequest {
	// Given()
		// content type,set cookies,add auth, add param, set header info etc
		// When()
		// get,post,put,delete
		// Then()part of then//And()
		// Validate status code,extract response,extract header,extract response body
		
		int id;
		
		@Test(priority=1,enabled=true)
		void getUsers()
		{
			given()
					
			.when()
				.get("https://dummy.restapiexample.com/api/v1/employees")
					
			.then()
				.statusCode(200)
				.log().all();
				
		}
		

		//@Test(priority=2)
		void createUser()
		{
			HashMap data=new HashMap();
			data.put("name","Hafiz");
			data.put("salary","120000");
			data.put("age","55");
			
			
			id=given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.post("https://dummy.restapiexample.com/api/v1/create")
				.jsonPath().getInt("id");
			
//			.then()
//				.statusCode(201)
//				.log().all();
				
			
		}
		
		//@Test(priority=3,dependsOnMethods= {"createUser"})
		void updateUser()
		{

			HashMap data=new HashMap();
			data.put("name","Hafizur");
			data.put("salary","150000");
			data.put("age","45");
			
			
			given()
				.contentType("application/json")
				.body(data)
				
			.when()
			.put("https://dummy.restapiexample.com/api/v1/update/"+id)
				//.put("https://reqres.in/api/users/"+id)
					
			.then()
				.statusCode(200)
				.log().all();
			
		}
		
		//@Test(priority=4)
		void deleteUser()
		{
			given()
				
			.when()
			.delete("https://dummy.restapiexample.com/api/v1/delete/"+id)
				//.delete("https://reqres.in/api/users/"+id)
			
			.then()
				.statusCode(204)
				.log().all();
		}
}
