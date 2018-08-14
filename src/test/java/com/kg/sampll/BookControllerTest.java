package com.kg.sampll;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.kg.sampll.controller.BookController;
import com.kg.sampll.model.Book;
import com.kg.sampll.repository.BookRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * BookControllerTest
 */
@RunWith(SpringRunner.class)
public class BookControllerTest {
    @InjectMocks
    public BookController bookController; 
    @Mock
    public BookRepository bookRepository;
    private static final long BOOK_ID = 1L;
    private static final Book EXISTING_BOOK = new BookBuilder().BookId(1L).BookName("abc").Build();
    private static final Book NEW_BOOK = new BookBuilder().BookId(2L).BookName("abc").Build();
    private static final Optional<Book> EXISTING_BOOK1=Optional.of(EXISTING_BOOK);
    List<Book> expectedResult=Arrays.asList(EXISTING_BOOK);
    
    

    @Test
    public void readTest()
     {   
         when(bookRepository.findAll()).thenReturn(expectedResult);
        Iterable<Book> actualResult=bookController.read();
        assertNotNull(actualResult);
        // assertEquals(1, actEmployee.size());
        assertEquals(expectedResult, actualResult);
 
    }  
    
    
    @Test
    public void readOneTest()
     {   
         when(bookRepository.findById(BOOK_ID)).thenReturn(EXISTING_BOOK1);
        Optional<Book> actualResult=bookController.readOne(BOOK_ID);
        assertNotNull(actualResult);
        
 
    }
 
    @Test
    public void postTest() {
        when(bookRepository.saveAndFlush(NEW_BOOK)).thenReturn(EXISTING_BOOK);
        Book RES = bookController.add(NEW_BOOK);
        assertNotNull(RES);
    }
 
    @Test
    public void deleteTest() {
        bookController.delete(BOOK_ID);
        verify(bookRepository).deleteById(BOOK_ID);
 
    }
 
    @Test
    public void updateTest() {
        when(bookRepository.findById(BOOK_ID)).thenReturn(EXISTING_BOOK1);
        when(bookRepository.saveAndFlush(NEW_BOOK)).thenReturn(NEW_BOOK);
        Book RES = bookController.update(BOOK_ID,NEW_BOOK);
        assertNotNull(RES);
    }
 
 }
 
