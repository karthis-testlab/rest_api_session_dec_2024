package week3.day2;

import java.util.logging.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredListener implements Filter {
	
	private static final Logger LOGGER = Logger.getLogger(RestAssuredListener.class.getName());

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {		
		
		Response response = ctx.next(requestSpec, responseSpec);
		
		// Request Log
		LOGGER.info("BASE URI: "+requestSpec.getBaseUri());
		LOGGER.info("BASE PATH: "+requestSpec.getBasePath());
		LOGGER.info("Request Payload: "+requestSpec.getBody().toString());
		
		// Response Log
		LOGGER.info("Response Body"+response.getBody().asPrettyString());
		
		return response;
		
	}

}