package week3.day2;

import io.restassured.RestAssured;

public class RestAssuredPostCallByObjecct {
	
	public static void main(String[] args) {
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
		
		// POJO -> Plain Old Java Object
		IncidentRequestPayload payload = new IncidentRequestPayload();
		payload.setDescription("Call Post MEthod with request payload as POJO Object");
		payload.setShort_description("RESTAPISEP2024");
		payload.setState("1");
		payload.setUrgency("1");		

		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")
		           .header("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(payload)
		           .post(url)
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(201);
	}

}
