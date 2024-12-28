package week3.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SerivceNowIncidentTableE2eTest {
	
	public String url = "https://dev262949.service-now.com/api/now/table/{tableName}";
	IncidentRequestPayload payload = new IncidentRequestPayload();
	String sysId;
	RequestSpecification requestSpecification;
	
	@DataProvider
	public String[][] testData() {
		return new String[][] {
			{"Name1", "TestData1", "1", "1"}
		};
	}	
	
	@BeforeMethod
	public void beforeMethod() {
		requestSpecification = new RequestSpecBuilder()
		                        .setBaseUri("https://dev262949.service-now.com")
		                        .setBasePath("/api/now/table")
		                        .setAuth(RestAssured.basic("admin", "vW0eDfd+A0V-"))
		                        .addHeader("Content-Type", "application/json")
		                        .addPathParam("tableName", "incident")
		                        .addFilter(new RestAssuredListener())
		                        .build();
	}
	
	ResponseSpecification globalReponseValidation(int statusCode, String statusLine) {
		ResponseSpecification expect = RestAssured.expect();
		expect.statusCode(statusCode);
		expect.statusLine(Matchers.containsString(statusLine));
		return expect;
	}
	
	@Test(dataProvider = "testData", priority = 1)
	public void userShouldAbleToCreateNewIncident(String desciption, String shortDescription, String state, String urgency) {
		
		payload.setDescription(desciption);
		payload.setShort_description(shortDescription);
		payload.setState(state);
		payload.setUrgency(urgency);
		
		sysId = RestAssured.given()
        .spec(requestSpecification)       
        .when()
        .body(payload)
        .post("/{tableName}")
        .then()       
        .spec(globalReponseValidation(201, "Created"))
        .contentType(ContentType.JSON)
        .extract()
        .jsonPath()
        .getString("result.sys_id");
        
	}
	
	@Test(priority = 2)
	public void userShouldAbleToFetchSingleIncidentRecord() {
		RestAssured.given()
        .spec(requestSpecification)
        .pathParam("sys_id", sysId)
        .when()
        .get("/{tableName}/{sys_id}")
        .then()
        .spec(globalReponseValidation(200, "OK"))
        .contentType(ContentType.JSON)
        .body("result.sys_id", Matchers.equalTo(sysId));       
	}
	
	@Test(priority = 3)
	public void userShouldAbleToUpdateExistingRecord() {
		RestAssured.given()
		.spec(requestSpecification)       
        .pathParam("sys_id", sysId)
        .when()
        .put("/{tableName}/{sys_id}")
        .then()
        .spec(globalReponseValidation(200, "OK"))
        .contentType(ContentType.JSON)
        .body("result.sys_id", Matchers.equalTo(sysId)); 
	}
	
	@Test(priority = 4)
	public void userShouldAbleToDelteTheExistingRecord() {
		RestAssured.given()
		.spec(requestSpecification)
        .pathParam("sys_id", sysId) 
        .when()
        .delete("/{tableName}/{sys_id}")
        .then()
        .spec(globalReponseValidation(204, "No Content"));
	}
	
	@Test(priority = 5)
	public void userShouldNotAbleToSeeDeletedIncidentRecord() {
		RestAssured.given()       
        .spec(requestSpecification)   
        .pathParam("sys_id", sysId)
        .when()
        .get("/{tableName}/{sys_id}")
        .then()
        .spec(globalReponseValidation(404, "Not Found"))
        .contentType(ContentType.JSON);
	}

}