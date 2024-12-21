package week3.day1;

import java.io.File;

import io.restassured.RestAssured;

public class RestAssuredCodeForPostMethodUsingFile {

	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")	
		           .header("Content-Type", "application/json")
		           .log().all() // Request Log
		           .when()
		           .body(new File("src/main/resources/incident_request_payload.json"))
		           .post(url)
		           .then()
		           .log().all() // Response Log
		           .assertThat()
		           .statusCode(201);

	}

}
