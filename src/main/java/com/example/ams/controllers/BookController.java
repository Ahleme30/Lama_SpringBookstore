package com.example.ams.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.ams.entities.Book;
import com.example.ams.repositories.BookRepository;

import java.util.List;
//import javax.validation.Valid;

import javax.validation.Valid;
 
@Controller
@RequestMapping("/book/")

public class BookController {
	
	
	private final BookRepository bookrepository;
	
	@Autowired
	
	public BookController(BookRepository bookrepository) {
		super();
		this.bookrepository = bookrepository;
	}

	@GetMapping("list")
	// @ResponseBody
	public String listbooks(Model model) {
	List<Book> lb = (List<Book>) bookrepository.findAll();
	if (lb.size() == 0)
	lb = null;
	model.addAttribute("books", lb);
	return "book/listBooks";
	 //System.out.println(lb);
	 //return "Nombre de livres = " + lb.size();
	}
	
	@GetMapping("add")
	public String showAddBookForm(Model model) {
	Book book = new Book();// object dont la valeur des attributs par defaut
	model.addAttribute("book", book);
	return "book/addBook";
	}
	
	@PostMapping("add")
	public String addBook(@Valid Book book, BindingResult result, Model model) {
	if (result.hasErrors()) {
	return "book/addBook";
	}
	bookrepository.save(book);
	return "redirect:list";
	
	}
	
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") long id, Model model) {
	// long id2 = 100L;
	Book book = bookrepository.findById(id)
	.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
	System.out.println("suite du programme...");
	bookrepository.delete(book);
	/*
	* model.addAttribute("books", bookrepository.findAll()); return
	* "book/listBooks";
	*/
	return "redirect:../list";
	}
	
	@GetMapping("edit/{id}")
	public String showBookFormToUpdate(@PathVariable("id") long id, Model model)
	{
	Book book = bookrepository.findById(id)
	.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
		model.addAttribute("book", book);
	return "book/updateBook";
	}
	@PostMapping("update")
	public String updateBook(@Valid Book book, BindingResult result, Model
	model) {
	if (result.hasErrors()) {
	return "book/updateBook";
	}
	bookrepository.save(book);
	return "redirect:list";
	}

	
	
	
	
	
	
}
