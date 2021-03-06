package com.vermeg.ams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.ams.entities.Book;
@EnableJpaRepositories
@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book,Integer> {

}
