package week3.day1;

import io.restassured.RestAssured;

public class RestAssuredCodeForQueryParameter {

	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")
		           .queryParam("sysparm_limit", 10)
		           .queryParam("sysparm_fields", "sys_id,number")
		           .log().all() // Request Log
		           .when()
		           .get(url)
		           .then()
		           .log().all() // Response Log
		           .assertThat()
		           .statusCode(200);
		

	}

}
