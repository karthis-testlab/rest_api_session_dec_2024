package week3.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class SerivceNowIncidentTableE2eTest {
	
	public String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
	IncidentRequestPayload payload = new IncidentRequestPayload();
	String sysId;
	
	@DataProvider
	public String[][] testData() {
		return new String[][] {
			{"Name1", "TestData1", "1", "1"}
		};
	}	
	
	@Test(dataProvider = "testData", priority = 1)
	public void userShouldAbleToCreateNewIncident(String desciption, String shortDescription, String state, String urgency) {
		
		payload.setDescription(desciption);
		payload.setShort_description(shortDescription);
		payload.setState(state);
		payload.setUrgency(urgency);
		
		sysId = RestAssured.given()
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
        .extract()
        .jsonPath()
        .getString("result.sys_id");
        
	}
	
	@Test(priority = 2)
	public void userShouldAbleToFetchSingleIncidentRecord() {
		RestAssured.given()
        .auth()
        .basic("admin", "vW0eDfd+A0V-")
        .pathParam("tableName", "incident")
        .pathParam("sys_id", sysId)        
        .log().all()
        .when()
        .get(url+"/{sys_id}")
        .then()
        .assertThat()
        .statusCode(200)
        .body("result.sys_id", Matchers.equalTo(sysId));       
	}
	
	@Test(priority = 3)
	public void userShouldAbleToUpdateExistingRecord() {
		RestAssured.given()
        .auth()
        .basic("admin", "vW0eDfd+A0V-")
        .pathParam("tableName", "incident")
        .pathParam("sys_id", sysId)
        .header("Content-Type", "application/json")
        .log().all()
        .when()
        .put(url+"/{sys_id}")
        .then()
        .assertThat()
        .statusCode(200)
        .body("result.sys_id", Matchers.equalTo(sysId)); 
	}
	
	@Test(priority = 4)
	public void userShouldAbleToDelteTheExistingRecord() {
		RestAssured.given()
        .auth()
        .basic("admin", "vW0eDfd+A0V-")
        .pathParam("tableName", "incident")
        .pathParam("sys_id", sysId)        
        .log().all()
        .when()
        .delete(url+"/{sys_id}")
        .then()
        .assertThat()
        .statusCode(204);        
	}
	
	@Test(priority = 5)
	public void userShouldNotAbleToSeeDeletedIncidentRecord() {
		RestAssured.given()
        .auth()
        .basic("admin", "vW0eDfd+A0V-")
        .pathParam("tableName", "incident")
        .pathParam("sys_id", sysId)        
        .log().all()
        .when()
        .get(url+"/{sys_id}")
        .then()
        .assertThat()
        .statusCode(404);
	}

}