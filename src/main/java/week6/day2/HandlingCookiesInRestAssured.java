package week6.day2;

import java.util.Map;
import java.util.Map.Entry;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HandlingCookiesInRestAssured {
	
	public static void main(String[] args) {
		Response response = RestAssured.given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .when()
		           .get("https://dev262949.service-now.com/api/now/table/incident");
		
		Map<String, String> cookies = response.getCookies();
		
		for (Entry<String, String> cookie : cookies.entrySet()) {
			System.out.println("Key is: "+cookie.getKey());
			System.out.println("Value is: "+cookie.getValue());
		}
		
		System.out.println(response.getCookie("JSESSIONID"));
		
		RestAssured.given()
		           .cookie("JSESSIONID", response.getCookie("JSESSIONID"))
		           .when()
		           .get("https://dev262949.service-now.com/api/now/table/incident/1c741bd70b2322007518478d83673af3")
		           .then()
		           .log().all();
		
	}

}