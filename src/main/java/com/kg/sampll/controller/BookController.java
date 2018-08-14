package com.kg.sampll.controller;

import java.util.List;
import java.util.Optional;

import com.kg.sampll.model.Book;
import com.kg.sampll.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(value = "/book")
public class BookController {

   @Autowired
   private BookRepository bookRepository;
   @RequestMapping(method = RequestMethod.GET)
   public Iterable<Book> read() {
       return bookRepository.findAll();
   }
   @RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = { ("application/json") })
   public Optional<Book> readOne(@PathVariable Long bookId) {
       System.out.println(bookRepository.findById(bookId));
       return bookRepository.findById(bookId);
   }
   @RequestMapping(method = RequestMethod.POST)
   public Book add(@RequestBody Book book) {
       book.setBookId(null);
       return bookRepository.saveAndFlush(book);
   }
   @RequestMapping(value="/{id}",method=RequestMethod.PUT)
   public Book update(@PathVariable Long id,@RequestBody Book updateBook )
   {
        updateBook.setBookId(id);
        return bookRepository.saveAndFlush(updateBook);
   }
   @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
   public void  delete(@PathVariable Long id)
   {
         bookRepository.deleteById(id);
   }

   @RequestMapping(value="/find",method=RequestMethod.GET)
   public List<Object> findBook()
   {
	return bookRepository.findBook();
   }


   @RequestMapping(value="/find/{id}",method=RequestMethod.GET)
   public List<Object> findBook(@PathVariable Long id)
   {
	return bookRepository.findBook(id);
    
   }
   
}