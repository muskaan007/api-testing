package com.cucumber.apitesting;

import static org.junit.Assert.assertTrue;

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

	private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");
    private final String requestString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();
	
	private static Response response;
	private static String jsonString;


	@When("^users want to get information about all the books$")
	public void usersGetInformationOfAllBook() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given().accept("application/json");
		response = request.get("/book");

		jsonString = response.asString();
		assertTrue(!jsonString.isEmpty());
	}

	@When("users upload a book data")
	public void addBookInList() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request
		.header("Content-Type", "application/json")
		.body(requestString);
		
		response = request.post("/book");
		Assert.assertEquals(200, response.getStatusCode());
	}

	@When("I remove a book from my reading list")
	public void removeBookFromList() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header("Content-Type", "application/json");
		response = request.delete("/book/983");
	}
	
	@When("users what to update a book")
	public void updateBook() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header("Content-Type", "application/json")
		.body(requestString);
		
		response = request.put("/book");
		Assert.assertEquals(200, response.getStatusCode());
	}
	
	@Then("The book is added")
	public void bookIsAdded() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header("Content-Type", "application/json");

		response = request.get("/book/983");
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("The book is removed")
	public void bookIsRemoved() {

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request
		.header("Content-Type", "application/json");

		response = request.get("/book/983");
		//Book with Id 983 is deleted so status is 400
		Assert.assertEquals(400, response.getStatusCode());
	}
	
	@Then("^the server should handle it and return a success status$")
    public void theServerShouldReturnASuccessStatus() {
    }
	@Then("return all the books data")
    public void returnAllBooks() {
		Assert.assertEquals(200, response.getStatusCode());
    }
}
