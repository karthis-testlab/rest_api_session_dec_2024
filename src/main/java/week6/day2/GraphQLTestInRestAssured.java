package week6.day2;

import org.json.JSONObject;

import io.restassured.RestAssured;

public class GraphQLTestInRestAssured {
	
	static String query = """
						query {
			  viewer {
			    login,
			    name,
			    company,
			    followers {
			      totalCount
			    }
			  }
			}
			""";

	public static void main(String[] args) {
		RestAssured.given()
		           .header("Authorization", "Bearer <github-api-token>")
		           .log().all()
		           .when()
		           .body(convertQueryToJsonString(query))
		           .post("https://api.github.com/graphql")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200);
		           
	}
	
	public static String convertQueryToJsonString(String query) {
		JSONObject json = new JSONObject();
		json.put("query", query);
		return json.toString();
	}

}
