package week6.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidateInRestAssured {

	static String json_schema = """
			{
			  "$schema": "http://json-schema.org/draft-07/schema#",
			  "type": "object",
			  "properties": {
			    "id": {
			      "type": "integer"
			    },
			    "name": {
			      "type": "string"
			    },
			    "releaseDate": {
			      "type": "string"
			    },
			    "reviewScore": {
			      "type": "integer"
			    },
			    "category": {
			      "type": "string"
			    },
			    "rating": {
			      "type": "string"
			    }
			  },
			  "required": [
			    "id",
			    "name",
			    "releaseDate",
			    "reviewScore",
			    "category",
			    "rating"
			  ]
			}
			""";
	
	public static void main(String[] args) {
		RestAssured.given()
		           .header("Accept", "application/json")
		           .when()
		           .get("http://localhost:8080/app/videogames/10")
		           .then()
		           .log().all()
		           .assertThat()	
		           .contentType(ContentType.JSON)
		           .body(JsonSchemaValidator.matchesJsonSchema(json_schema));
	}

}