package com.example.book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.book.Module.BookDetailsModule;

@Repository
public interface BookDetailsI 
{

	public Optional<BookDetailsModule> getBookDetailsById(int id);
	public List<BookDetailsModule> getAll();
	public void create(BookDetailsModule bookDetails);
	void deleteById(int id);
	BookDetailsModule updateById(int id,BookDetailsModule bookDetailsModule);
}
