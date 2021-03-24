package com.genesis.auction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationLogicTests {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
	}

	@Test
	public void WHEN_RUNS_COMPLETE_AUCTION_THEN_RETURNS_CORRECT_WINNER() {

		RestAssured.basePath = "/bids";
		
		RestAssured.given()
			.body("{\"bidderName\": \"João\", \"bidValue\": 0.01}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
		RestAssured.given()
			.body("{\"bidderName\": \"Maria\", \"bidValue\": 0.03}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
		RestAssured.given()
			.body("{\"bidderName\": \"Renata\", \"bidValue\": 0.01}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
		RestAssured.given()
			.body("{\"bidderName\": \"Pedro\", \"bidValue\": 12.34}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
		RestAssured.basePath = "/auctions/result";
		
		RestAssured.given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("winner", Matchers.equalTo("Maria"))
			.body("bidValue", Matchers.equalTo(0.03f))
			.body("totalCollection", Matchers.equalTo(3.92f));		
				
	}
	

}
