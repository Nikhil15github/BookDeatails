package com.example.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.Module.BookDetailsModule;
import com.example.book.Service.BookDetailsI;


@RestController
@RequestMapping("/bookdetails")
public class BookDetailsController 
{
	@Autowired
	BookDetailsI bookDetailsI;
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<BookDetailsModule>> getByid(@PathVariable("id")int id)
	
	{
		//Optional<BookDetailsModule> bookDetailsById = bookDetailsImpli.getBookDetailsById(id);
		
		//return new ResponseEntity<Optional<BookDetailsModule>>(bookDetailsById,HttpStatus.OK);
		return new ResponseEntity<Optional<BookDetailsModule>>(bookDetailsI.getBookDetailsById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<BookDetailsModule>> getAll()
	{
		return new ResponseEntity<List<BookDetailsModule>>(bookDetailsI.getAll(),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody BookDetailsModule BookDetails)
	{
		bookDetailsI.create(BookDetails);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id)
	{
		
		bookDetailsI.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PostMapping("/updatebyid/{id}")
	public ResponseEntity<Optional<BookDetailsModule>> updateByid(@PathVariable("id")int id,@RequestBody BookDetailsModule bookDetailsModule)
	{
		BookDetailsModule bookDetailsModule2 = bookDetailsI.updateById(id, bookDetailsModule);
		return new ResponseEntity<Optional<BookDetailsModule>>(HttpStatus.OK);
	
	}
	
	
}
