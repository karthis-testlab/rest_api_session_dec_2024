package week3.day1;

import io.restassured.RestAssured;

public class MyFirstRestAssuredCode {

	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}/{sys_id}";
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")
		           .pathParam("sys_id", "1c741bd70b2322007518478d83673af3")
		           .log().all() // Request Log
		           .when()
		           .get(url)
		           .then()
		           .log().all() // Response Log
		           .assertThat()
		           .statusCode(200);

	}

}