package com.genesis.auction;

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
public class ConsistencyChecksTests {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
	}

	@Test
	public void WHEN_RECEIVES_ZERO_BID_VALUE_THEN_RETURNS_BAD_REQUEST() {

		//Starts a new Auction
		RestAssured.basePath = "/auctions";
		RestAssured.given()
			.body("{}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.OK.value());
		
		//Tries to add a bid with value 0
		RestAssured.basePath = "/bids";
		RestAssured.given()
			.body("{\"bidderName\": \"João\", \"bidValue\": 0}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
			
	}

	@Test
	public void WHEN_RECEIVES_NEGATIVE_BID_VALUE_THEN_RETURNS_BAD_REQUEST() {

		//Starts a new Auction
		RestAssured.basePath = "/auctions";
		RestAssured.given()
			.body("{}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.OK.value());
		
		//Tries to add a bid with negative value
		RestAssured.basePath = "/bids";
		RestAssured.given()
			.body("{\"bidderName\": \"João\", \"bidValue\": -1}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
				
	}
	
	@Test
	public void WHEN_RECEIVES_BID_VALUE_WITH_MORE_THAN_TWO_DECIMALS_THEN_RETURNS_BAD_REQUEST() {

		//Starts a new Auction
		RestAssured.basePath = "/auctions";
		RestAssured.given()
			.body("{}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.OK.value());
		
		//Tries to add a bid with more than two decimals
		RestAssured.basePath = "/bids";
		RestAssured.given()
			.body("{\"bidderName\": \"João\", \"bidValue\": 5.123}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
				
	}
	
}
