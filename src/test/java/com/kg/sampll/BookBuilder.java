package com.kg.sampll;

import com.kg.sampll.model.Book;

/**
 * BookBuilder
 */
public class BookBuilder {
    private Long bookId;
    private String bookname;
    Book book=new Book();
    
    public BookBuilder BookId(Long bookId) {
        book.setBookId(bookId);
        return this;
    }

    public BookBuilder BookName(String bookname) {
        book.setBookname(bookname);
        return this;
    }

    public Book Build()
    {
       return book;
    }
    
    
}