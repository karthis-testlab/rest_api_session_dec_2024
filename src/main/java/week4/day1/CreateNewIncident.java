package week4.day1;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateNewIncident {
	
	String token;
	
	@BeforeMethod
	public void createAuthToken() {
		token = RestAssured.given()
        .contentType(ContentType.URLENC)
        .formParam("grant_type", "password")
        .formParam("client_id", "069fe164bfa296100535f9db67ee0f8a")
        .formParam("client_secret", "3#?j]HM&vg")
        .formParam("username", "admin")
        .formParam("password", "vW0eDfd+A0V-")
        .log().all()
        .when()
        .post("https://dev262949.service-now.com/oauth_token.do")
        .then()
        .log().all()
        .extract()
        .jsonPath()
        .getString("access_token");
	}
	
	@Test
	public void main() {
		// Create Object for the POJO class
		IncidentRequestPayload payload = new IncidentRequestPayload();
		payload.setDescription("Simple Description");
		payload.setState(2);
		payload.setUrgency(3);
		
		RestAssured.given()		           
		           .contentType(ContentType.JSON)
		           .header("Authorization", "Bearer "+token)
		           .log().all()
		           .when()
		           .body(new Gson().toJson(payload))
		           .post("https://dev262949.service-now.com/api/now/table/incident")
		           .then()
		           .log().all();
	}

}