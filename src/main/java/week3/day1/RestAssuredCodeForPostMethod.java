package week3.day1;

import io.restassured.RestAssured;

public class RestAssuredCodeForPostMethod {

	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
		String request_payload = """
				{
				  "description": "Creating one record using postman - Test1",
				  "short_description": "Smoke Test"
                }				
				""";		
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")	
		           .header("Content-Type", "application/json")
		           .log().all() // Request Log
		           .when()
		           .body(request_payload)
		           .post(url)
		           .then()
		           .log().all() // Response Log
		           .assertThat()
		           .statusCode(201);

	}

}
