package week3.day2;

import io.restassured.RestAssured;

public class RestAssuresPostCall {
	
	public static void main(String[] args) {
		
		String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
		
		String request_payload = """
				{
                    "description": "Call Post MEthod with request payload",
                    "short_description": "RESTAPISEP2024",
                    "state": "1",
                    "urgency": "1"
                }
				""";
		
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .pathParam("tableName", "incident")
		           .header("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(request_payload)
		           .post(url)
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(201);
		           
	}

}