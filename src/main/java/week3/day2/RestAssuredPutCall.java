package week3.day2;

import java.io.File;

import io.restassured.RestAssured;

public class RestAssuredPutCall {
	
	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}/{sysId}";
		
		RestAssured.given()
        .auth()
        .basic("admin", "vW0eDfd+A0V-")
        .pathParam("tableName", "incident")
        .pathParam("sysId", "a3c4bd7e8322d610695bc7b6feaad3e8")
        .header("Content-Type", "application/json")
        .when()
        .body(new File("src/main/resources/incident_request_payload.json"))
        .put(url)
        .then()
        .log().all()
        .assertThat()
        .statusCode(200);
		
	}

}