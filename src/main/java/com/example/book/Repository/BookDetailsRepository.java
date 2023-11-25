package com.example.book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book.Module.BookDetailsModule;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsModule,Integer>
 
{

}
