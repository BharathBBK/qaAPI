package apiQA.qaAPI;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredQAAPI {
	
	public static void main(String[] args) {
		
		RestAssuredQAAPI cal = new RestAssuredQAAPI();
		String User = "";
		User = cal.post_createEmployee();
		System.out.println("Main Method opened"+User);
		cal.get_employeeByUserID(User);
		System.out.println("Data before update");
		cal.update_Employee_Details(User);
		System.out.println("Data after update");
		cal.get_employeeByUserID(User);
		System.out.println("Account Deleted");
		cal.delete_empdetails(User);
		cal.get_employeeByUserID(User);
			}
	
	RestAssuredQAAPI(){RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";}
	
	public void getCall() {
		
		//RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpreq = RestAssured.given();
		
		Response res = httpreq.request(Method.GET,"/employees");
		
		System.out.println(res.getBody().asString());
		System.out.println(res.statusLine());
		System.out.println("content type is :"+ res.header("Content-Type"));
		
		Assert.assertEquals(res.statusCode(),200);
		Assert.assertEquals(res.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(res.header("Content-Type"),"text/html; charset=UTF-8");
		
		      Headers allhead = res.headers();
		      
		      for ( Header  header:allhead) {System.out.println(header.getName()+"--"+header.getValue());}}
	
	 
	
	public void get_employeeByUserID (String UserID) {
		
		
		RequestSpecification req = RestAssured.given();
		//UserID = "4350";
		Response res = req.request(Method.GET, "/employee/"+UserID);
		System.out.println(res.getBody().asString());
		}
	
	public String post_createEmployee() {
		
		RequestSpecification httpreq = RestAssured.given();
		
		JSONObject postparams= new JSONObject();
		
		postparams.put("name", "2BharathBBkXYZ");
		postparams.put("salary", "123");
		postparams.put("age", "24");
		
		httpreq.header("Content-Type","application/json");
		
		httpreq.body(postparams.toJSONString());
		
		 Response res =   httpreq.request(Method.POST,"/create");
		 
		 String response = res.getBody().asString();
		 
		 System.out.println("Successfully created"+ response);
		 
		 System.out.println("status code is "+ res.statusCode());
		 
		 Assert.assertEquals(res.statusCode(), 200);
		 
		 String retur =  res.jsonPath().get("id");
		 return retur;
			}
	public void update_Employee_Details(String user) {
		RequestSpecification req= RestAssured.given();
		JSONObject postparams = new JSONObject();
		postparams.put("name", "2BharathBBkXYZ");
		postparams.put("salary", "2500");
		postparams.put("age", "25");
		req.body(postparams.toJSONString());
		Response responsee = req.request(Method.PUT, "/update/"+user);
		System.out.println("Updated response>>"+responsee.getBody().toString());
	}
	public void delete_empdetails(String User) {
		RequestSpecification RestRequest = RestAssured.given();
		Response res = RestRequest.request(Method.DELETE, "/delete/"+User);
		System.out.println(res.getBody().toString());
		int a = 1234;
		String b = String.valueOf(a);
		System.out.println(b);
		
	}
	
}
