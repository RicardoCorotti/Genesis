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
public class OverloadTests {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
	}

	@Test
	public void WHEN_RECEIVES_ONE_THOUSAND_REQUESTS_THEN_IGNORES_LAST_ONE() {

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
		
		//Adds 999 Bids, in descending order of bid values to test data structure performance.
		RestAssured.basePath = "/bids";
		for (int requestNumber = 1; requestNumber <= 999; requestNumber++) {
		
			System.out.println("##### requestNumber:" + requestNumber);
			
			RestAssured.given()
				.body("{\"bidderName\": \"Bidder " + requestNumber  +  "\", \"bidValue\": " + (1000 - requestNumber) + "}")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
		}
		
		//Testing that auction should not accept more than 999 bidders.
		RestAssured.given()
			.body("{\"bidderName\": \"Bidder 1000\", \"bidValue\": 0.01}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.LOCKED.value());
		
		//Testing that the winner should be the bid with value $ 1. 
		//This was the last one (999 th) added to the data structure in the loop above.  
		RestAssured.basePath = "/auctions/result";
		RestAssured.given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("winner", Matchers.equalTo("Bidder 999"))
			.body("bidValue", Matchers.equalTo(1))
			.body("totalCollection", Matchers.equalTo(979.02f));		
		
	}
	

}
