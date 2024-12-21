package week3.day1;

import io.restassured.RestAssured;

public class RestAssuredCodeForDeleteMethod {

	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}/{sysId}";
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")	
		           .pathParam("sysId", "ffa141e683621210695bc7b6feaad3a2")		          
		           .log().all() // Request Log
		           .when()		          
		           .delete(url)
		           .then()
		           .log().all() // Response Log
		           .assertThat()
		           .statusCode(204);

	}

}
