package com.example.book.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.Module.BookDetailsModule;
import com.example.book.Repository.BookDetailsRepository;

@SpringBootTest
public class BookDetailsTest 
{
	@Autowired
	BookDetailsRepository bookDetailsRepository;
	
	@Autowired
	BookDetailsImpli bookDetatilsImpli;
	
	@BeforeEach
	public void deleteAll()
	{
		bookDetailsRepository.deleteAll();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetById()
	{
		assertEquals(0,bookDetailsRepository.count());
		
		//given
		BookDetailsModule data = new BookDetailsModule();
		data.setName("nikk");
		data.setAuthorName("ngg");
		
		bookDetailsRepository.save(data);
		
		BookDetailsModule data1 = new BookDetailsModule();
		data.setName("nikk p");
		data.setAuthorName("ngg p");
		
		bookDetailsRepository.save(data1);
		
		assertEquals(2,bookDetailsRepository.count());
		
		//when
		
		
		Optional<BookDetailsModule> details = bookDetailsRepository.findById(data.getId());
		
		BookDetailsModule data5 = details.get();
		assertEquals("nikk", data5.getName());
		
	}
}
