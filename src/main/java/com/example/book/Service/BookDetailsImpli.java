package com.example.book.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

import com.example.book.Exception.Errorr;
import com.example.book.Exception.ResourceNotFoundException;
import com.example.book.Module.BookDetailsModule;
import com.example.book.Repository.BookDetailsRepository;

@Service
public class BookDetailsImpli implements BookDetailsI
{
	private static final Logger LOG= LoggerFactory.getLogger(BookDetailsImpli.class);
	
	@Autowired
	BookDetailsRepository bookDetailsRepository;
	
	@Override
	public Optional<BookDetailsModule> getBookDetailsById(int id) 
	{
		 Optional<BookDetailsModule> BookDetailsById = bookDetailsRepository.findById(id);
		 if(BookDetailsById.isPresent())
		 {
			 LOG.info("Details present");
			
		 }
		 else
		 {
			 LOG.info("Details Not Present");
		 }
		return BookDetailsById;
		 
	}

	@Override
	public List<BookDetailsModule> getAll() 
	{
		 List<BookDetailsModule> findAll = bookDetailsRepository.findAll();
		 
		 if(findAll.isEmpty())
		 {
			 LOG.info("Details Not Present");
		 }
		 return findAll;
	}

	@Override
	public void create(BookDetailsModule bookDetails) 
	{
		
		bookDetailsRepository.save(bookDetails);
	}

	@Override
	public void deleteById(int id) 
	{
		
		bookDetailsRepository.deleteById(id);
	}

	@Override
	public BookDetailsModule updateById(int id, BookDetailsModule bookDetailsModule) {
		
		try
		{
		Optional<BookDetailsModule> bookDetails = bookDetailsRepository.findById(id);
		
		if(bookDetails.isPresent())
		{
			 LOG.info("Details are Present");
			BookDetailsModule existingBookDetails = bookDetails.get();
			
			existingBookDetails.setName(bookDetailsModule.getName());
			existingBookDetails.setAuthorName(bookDetailsModule.getAuthorName());
			 LOG.info("Details changed");
			
			bookDetailsRepository.save(existingBookDetails);
			 LOG.info("Details saved in database");
			return existingBookDetails;
		
		}
		else
		{
			LOG.info("Details are not Present");
			throw new Exception();
		}
		}
		catch(Exception ex)
		{
			LOG.info("Details not available");
			throw new ResourceNotFoundException(Errorr.DATA_NOT_FOUND.getCode(),ex.getMessage());
		}
	}
	
	

}
