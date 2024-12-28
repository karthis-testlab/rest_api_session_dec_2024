package week4.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateOAuthToken {

	public static void main(String[] args) {
		
		OAuthResponse oAuthResponse = RestAssured.given()
		           .contentType(ContentType.URLENC)
		           .formParam("grant_type", "password")
		           .formParam("client_id", "069fe164bfa296100535f9db67ee0f8a")
		           .formParam("client_secret", "3#?j]HM&vg")
		           .formParam("username", "admin")
		           .formParam("password", "vW0eDfd+A0V-")
		           .log().all()
		           .when()
		           .post("https://dev262949.service-now.com/oauth_token.do")
		           .as(OAuthResponse.class);
		           
		
		System.out.println(oAuthResponse.getAccess_token());
		System.out.println(oAuthResponse.getToken_type());
		
	}

}