package com.example.book.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.PostConstruct;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import com.example.book.Module.BookDetailsModule;
import com.example.book.Repository.BookDetailsRepository;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/application-Junit.properties")
public class BookDetailsControllerTest 
{
	@LocalServerPort
	private int port;
	
	@Autowired
	BookDetailsRepository bookDetailsRepository;
	
	RequestSpecification requestSpecification;
	
	@PostConstruct
	public void initRequestSpecification()
	{
		RequestSpecBuilder spec = new RequestSpecBuilder();
		requestSpecification=spec.setBaseUri("http://localhost:"+port+"/bookdetails").build();
		
	}
	
	@BeforeEach
	public void testDeleteAll()
	{
		bookDetailsRepository.deleteAll();
	}
	@Test
	public void testCreate()
	{
		//given
		BookDetailsModule book = new BookDetailsModule();
		
		assertEquals(0,bookDetailsRepository.count());
		
		book.setName("nikk");
		book.setAuthorName("uuy");
		bookDetailsRepository.save(book);
		
		//when
		RestAssured.given(requestSpecification).contentType("application/json")
		.accept(MediaType.APPLICATION_JSON_VALUE).body(book).post("/create").then();
		
		//then
		assertEquals(1,bookDetailsRepository.count());	
	}
	
	@Test
	public void testDeleteById()
	{
		//when
		BookDetailsModule book = new BookDetailsModule();
		book.setName("jik");
		book.setAuthorName("htfth");
		
		assertEquals(0, bookDetailsRepository.count());
		bookDetailsRepository.save(book);
		
		BookDetailsModule book1 = new BookDetailsModule();
		book1.setName("jik");
		book1.setAuthorName("htfth");
		bookDetailsRepository.save(book1);
		
		assertEquals(2, bookDetailsRepository.count());
		//then
		ValidatableResponse then = RestAssured.given(requestSpecification).contentType("application/json").
		accept(MediaType.APPLICATION_JSON_VALUE).delete("/delete/"+book.getId()).then();
		//then
		assertEquals(2, bookDetailsRepository.count());
	}
}
