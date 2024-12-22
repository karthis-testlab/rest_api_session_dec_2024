package week3.day2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CreateIncidientTest {
	
	public String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
	IncidentRequestPayload payload = new IncidentRequestPayload();
	
	@DataProvider
	public String[][] testData() {
		return new String[][] {
			{"Name1", "TestData1", "1", "1"}
		};
	}	
	
	@Test(dataProvider = "testData")
	public void userShouldAbleToCreateNewIncident(String desciption, String shortDescription, String state, String urgency) {
		
		payload.setDescription(desciption);
		payload.setShort_description(shortDescription);
		payload.setState(state);
		payload.setUrgency(urgency);
		
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