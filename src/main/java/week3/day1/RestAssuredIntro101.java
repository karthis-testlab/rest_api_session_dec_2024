package week3.day1;

public class RestAssuredIntro101 {

	public static void main(String[] args) {
		
		/*
		 * 
		 * Title: Introduction to REST-Assured for API Test Automation
		 * 
		 * Objective: In this session, we aim to provide team members with an understanding of REST-Assured, 
		 * a powerful tool used for testing and validating RESTful APIs in Java. This session will focus on the basic concepts 
		 * and examples that even beginners can understand and apply.
		 * 
		 * Agenda:
		 * 
		 *  1. Overview of RESTful APIs
		 *  2. Introduction to REST-Assured
		 *  3. Setting Up REST-Assured in the eclipse maven Project
		 *  4. Basic Examples of API Testing with REST-Assured
		 *  5. Key Features of Rest-Assured
		 *  
		 * 1. Overview of RESTful APIs: RESTful APIs (Representational State Transfer) are a standard method for designing networked applications, 
		 *    allowing systems to communicate over HTTP in a simple and standard way. They are characterized by their stateless operation and use standard 
		 *    HTTP methods such as GET, POST, PUT, and DELETE.
		 *    
		 * 2. Introduction to REST-Assured: REST-Assured is a Java library that simplifies the process of testing and validating RESTful APIs. 
		 *    It is widely used due to its ease of use and its ability to integrate seamlessly with existing Java-based automation frameworks, such as JUnit and TestNG.
		 *    
		 *    Java: Rest-Assured, HTTPClient, Karate
	     *    JavaScript: SuperTest (npm package)
	     *    Python: request
	     *    
	     *    Domain-Specific Language (Gherkin Format) - cucumber
	     *    
	     *    Given - Pre-codition for your test action
	     *    When - Action we need to perform
	     *    Then - Assert the action is worked or not
	     *    
	     *    Key Features of Rest-Assured:
	     * 
	     * 1.) Readable Syntax: Provides a readable DSL for writing tests, making tests easy to understand and write.
	     * 
	     * 2.) BDD-style Tests: Supports Behavior-Driven Development (BDD) given/when/then syntax.
	     * 
	     * 3.) Support for Various HTTP Methods: Easily supports GET, POST, PUT, DELETE, PATCH, OPTIONS, and HEAD requests.
	     * 
	     * 4.) Authentication Support: Supports various authentication mechanisms like Basic, Digest, Form, and OAuth.
	     * 
	     * 5.) Easy Extraction and Validation: Provides easy ways to extract response data and validate JSON & XML responses.
	     * 
	     * 6.) Integration: Seamlessly integrates with testing frameworks like JUnit and TestNG.
	     * 
	     * 7.) Logging: Allows detailed request and response logging.
	     * 
	     * ex: First RestAssured Test Script
	     * 
	     * URL = "https://api.yourdomain.com/books/{bookId}"
	     * HTTP Method = GET
	     * 
	     * RestAssured.given() // Pre-condition for the API request needed
	     *     .pathParam("bookId", "12345")                               // Set path parameter
	     *   .when() // HTTP method action GET/POST/PUT/DELETE - Request Body
	     *     .get("https://api.yourdomain.com/books/{bookId}")           // Make GET request
	     *   .then() // Assertion and Extraction
	     *     .statusCode(200)                                           // Check status code is 200
	     *     .and()
	     *     .statusLine(containsString("OK"))                          // Check status line contains OK
	     *     .and()
	     *     .contentType(ContentType.JSON)                             // Check response contentType is JSON format
	     *     .and()
	     *     .time(Matchers.lessThan(3000L));                           // Check response time should be less than 3000L
	     *     
	     * Here's a breakdown of the code:
	     * 
	     * -> baseURI sets the base URL for the API.
	     * -> given() begins a request specification. Inside it, we use .pathParam("bookId","2345") to set a path parameter for the book ID.
	     * -> when().get("/books/{bookId}") makes a GET request to the endpoint.
	     * -> then() begins a response specification. It verifies that the status code is 200, status line contains OK, 
	     *    response contentType is JSON format and response time should be less than 3000L
	     *    
	     *    With its fluent DSL and seamless integration with Java testing frameworks, 
	     *    Rest-Assured is an excellent choice for automating REST API testing. 
	     *    As you get more familiar with its features, you'll find it helps significantly in creating robust API tests.
		 *  
		 * 
		 * 
		 */

	}

}