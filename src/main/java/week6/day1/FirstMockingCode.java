package week6.day1;

import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;

public class FirstMockingCode {
	
	public static void main(String[] args) {
		
		// WireMock
		WireMock.stubFor(
				         WireMock.get("/java/welcome")
				                 .willReturn(WireMock.aResponse()
				                		             .withStatus(200)
				                		             .withStatusMessage("OK")
				                		             .withBody("Hello! WireWock Java Code."))
				         );
		
		// RestAssured
		RestAssured.given()
		           .log().all()
		           .when()
		           .get("http://localhost:8080/java/welcome")
		           .then()
		           .log().all();
		
		
	}

}