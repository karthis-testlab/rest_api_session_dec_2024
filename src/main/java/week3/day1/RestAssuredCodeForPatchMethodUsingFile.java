package week3.day1;

import java.io.File;

import io.restassured.RestAssured;

public class RestAssuredCodeForPatchMethodUsingFile {

	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}/{sysId}";
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")	
		           .pathParam("sysId", "ffa141e683621210695bc7b6feaad3a2")
		           .header("Content-Type", "application/json")
		           .log().all() // Request Log
		           .when()
		           .body(new File("src/main/resources/incident_request_payload.json"))
		           .put(url)
		           .then()
		           .log().all() // Response Log
		           .assertThat()
		           .statusCode(200);

	}

}
