package apiQA.qaAPI;
import java.util.ArrayList;

//import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;



public class RestServicesDemo {
	
	static String user;
	static ArrayList<String> num = new ArrayList<String>();
	public static void main (String[] args)  {
		
		//RestServicesDemo.get_all();
		RestServicesDemo.get_UserDetails("4346");
		
		
//		for (String i:num){
//			  System.out.println(i);  
//			  RestServicesDemo.get_UserDetails(i);
//			  
//		}	
		//RestServices obj= new RestServices();
		//Post call
        // user =  RestServicesDemo.create_UserID();
        //Get call 
        
        //RestServicesDemo.putCall();
        //RestServicesDemo.get_UserDetails();
        //RestServicesDemo.delete_User();
        //RestServicesDemo.get_UserDetails();
        
	}
	//@Test
	public static String create_UserID() {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		ResponseBody<?> r = RestAssured.given().body("{\"name\":\"BharathBBK12\",\"salary\":\"123\",\"age\":\"23\"}")
				.header("Content-Type","application/json").post("/create").then().extract().response().body();
		System.out.println("Post Response"+"\n"+r.asString());
		String number = r.jsonPath().get("id");
		
		System.out.println("New User is created and accountid :"+ number);
		
		return number;
	}
  public static void get_UserDetails(String userD) {
	  
	  RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	  ResponseBody<?> r = RestAssured.given().get("employee/" + userD).then().extract().response().body();
	  
	  System.out.println("Get Response"+"\n"+r.asString());
	  if (r.asString().isEmpty() == false) {
		  System.out.println("Used details"+"\n"+r.asString());  
	  } else {
		  System.out.println("Employess details are succssfully deleted for User"+ userD);
	  }
	  String value = r.jsonPath().get("employee_age");
	  System.out.println(value);
	  
  }	
  
  public static void get_all() {
	  RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	  ResponseBody r = RestAssured.given().get("employees").then().extract().response().body();
	  System.out.println("All employees Details"+"\n"+r.asString());
	  
	  num = r.jsonPath().get("id");
	  System.out.println(num);
	  
  }
  

  public static void putCall() {
	  
	  ResponseBody r = RestAssured.given().body("{\"name\":\"BharathBBK12\",\"salary\":\"1123\",\"age\":\"24\"}")
			  .header("Content-Type","application/json").put("update/"+user).then().extract().response().body();
	  System.out.println("employee details updated successfully"+"\n"+r.asString());
	  
  }
  
  public static void delete_User() {
	  
	  ResponseBody r = RestAssured.given().delete("delete/"+user).then().extract().response().body();
	  System.out.println("Record is successfully deleted"+"\n"+r.asString());
	  
  }
  
  
}
