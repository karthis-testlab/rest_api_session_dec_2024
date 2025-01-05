package week5.day2;

import java.io.File;

import io.restassured.RestAssured;

public class AddAttachmentInJiraIssue {
	
	public static void main(String[] args) {
		RestAssured.given()		           
		           .auth()
		           .preemptive()
		           .basic("karthike.selene@gmail.com", "<jira-api-token>")
		           .header("X-Atlassian-Token", "no-check")
		           .pathParam("issueIdOrKey", "10067")		           
		           .when()
		           .multiPart(new File("./Post_Testcase_Result.png"))
		           .post("https://karthikeselene.atlassian.net/rest/api/3/issue/{issueIdOrKey}/attachments")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200);
	}

}