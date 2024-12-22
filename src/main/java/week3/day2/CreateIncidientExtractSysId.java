package week3.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateIncidientExtractSysId {
	
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
		
		String sysId = RestAssured.given()
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
        .statusCode(201)
        .statusLine(Matchers.containsString("Created"))
        .contentType(ContentType.JSON)
        .time(Matchers.lessThan(5000L))
        .extract()
        .jsonPath()
        .getString("result.sys_id");
		
		System.out.println(sysId);
        
	}
	
	// 201 - Created
	// 200 - OK
	// 204 - No Content
	// 404 - Not Found

}