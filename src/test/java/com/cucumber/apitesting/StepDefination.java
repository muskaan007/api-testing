package com.cucumber.apitesting;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.Scanner;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefination{

	private static final String BASE_URL = "http://localhost:8080";
	private static final String BOOK_URL = "/book";
	private static final String BOOK_WITH_DUMMY_ID = "/book/983";
	public static final String APPLICATION_JSON = "application/json";
	public static final String CONTENT_TYPE = "Content-Type";

	private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");
    private final String requestString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();
	
	private static Response response;


	@When("^to get information about all the books$")
	public void usersGetInformationOfAllBook() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given().accept(APPLICATION_JSON);
		response = request.get(BOOK_URL);

		assertNotNull(response.asString());
	}

	@When("user upload book data")
	public void addBookInList() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request
		.header(CONTENT_TYPE, APPLICATION_JSON)
		.body(requestString);
		
		response = request.post(BOOK_URL);
		Assert.assertEquals(200, response.getStatusCode());
	}

	@When("I remove a book from my reading list")
	public void removeBookFromList() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header(CONTENT_TYPE, APPLICATION_JSON);
		response = request.delete(BOOK_WITH_DUMMY_ID);
	}
	
	@When("users what to update a book")
	public void updateBook() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header(CONTENT_TYPE, APPLICATION_JSON)
		.body(requestString);
		
		response = request.put(BOOK_URL);
		Assert.assertEquals(200, response.getStatusCode());
	}
	
	@Then("the book is added")
	public void bookIsAdded() {
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("The book is removed")
	public void bookIsRemoved() {

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header(CONTENT_TYPE, APPLICATION_JSON);

		response = request.get(BOOK_WITH_DUMMY_ID);
		//Book with Id 983 is deleted so status is 400
		Assert.assertEquals(400, response.getStatusCode());
	}
	
	@Then("^the service should handle it and return the updated book$")
    public void theServerShouldReturnASuccessStatus() {
    }
	@Then("return all the books data")
    public void returnAllBooks() {
		Assert.assertEquals(200, response.getStatusCode());
    }
}
