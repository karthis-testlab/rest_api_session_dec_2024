package week6.day1;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;

public class RestfulBookerApiMockingTest {
	
	
	String request_Payload = """
			{
            "firstname" : "King",
            "lastname" : "Carry",
            "totalprice" : 200,
            "depositpaid" : true,
            "bookingdates" : {
              "checkin" : "2025-01-11",
              "checkout" : "2025-01-20"
            },
            "additionalneeds" : "Breakfast, Lunch"
            }
			""";
	
	String response_Payload = """
			{
             "bookingid": 617,
             "booking": {
                 "firstname": "King",
                 "lastname": "Carry",
                 "totalprice": 200,
                 "depositpaid": true,
                 "bookingdates": {
                   "checkin": "2025-01-11",
                   "checkout": "2025-01-20"
                 },
                "additionalneeds": "Breakfast, Lunch"
             }
            }
			""";
	
	@BeforeMethod
	public void createStub() {
		WireMock.stubFor(WireMock.post("/booking")
				                 .withHeader("Content-Type", WireMock.containing("x-www-form-urlencoded"))
				                 .withHeader("Accept", WireMock.containing("json"))
				                 .withRequestBody(WireMock.equalTo(request_Payload))				                 
				                 .willReturn(WireMock.aResponse()
				                		             .withStatus(200)
				                		             .withStatusMessage("OK")
				                		             .withBody(response_Payload)));
		       
	}
	
	@Test
	public void mockBookingTest() {
		RestAssured.given()
		           .log().all()
		           .header("Content-Type", "application/json")
		           .header("Accept", "application/json")
		           .when()
		           .body(request_Payload)
		           .post("http://localhost:8080/booking")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"));
	}
	
	@Test
	public void realBookingTest() {
		RestAssured.given()
		           .log().all()
		           .header("Content-Type", "application/json")
		           .header("Accept", "application/json")
		           .when()
		           .body(request_Payload)
		           .post("http://restful-booker.herokuapp.com/booking")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"));
	}

}