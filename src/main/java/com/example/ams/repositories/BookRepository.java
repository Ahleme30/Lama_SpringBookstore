package com.example.ams.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ams.entities.Book;



public interface BookRepository extends CrudRepository<Book, Long> { 

}
