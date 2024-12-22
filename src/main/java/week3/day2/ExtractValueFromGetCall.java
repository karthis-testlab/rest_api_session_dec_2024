package week3.day2;

import java.util.List;

import io.restassured.RestAssured;

public class ExtractValueFromGetCall {

	public static void main(String[] args) {
		List<String> sysIds = RestAssured.given()
        .auth()
        .basic("admin", "vW0eDfd+A0V-")
        .pathParam("tableName", "incident")           
        .log().all()
        .when()
        .get("https://dev262949.service-now.com/api/now/table/{tableName}")
        .then()
        .assertThat()
        .statusCode(200)
        .extract()
        .jsonPath()
        .getList("result.sys_id");
		
		for (String object : sysIds) {
			System.out.println(object);
		}
		
		System.out.println(sysIds.get(2));

	}

}