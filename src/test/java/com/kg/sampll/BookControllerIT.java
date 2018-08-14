package com.kg.sampll;
import com.kg.sampll.model.Book;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.when;
import org.apache.http.HttpStatus;
import com.jayway.restassured.http.ContentType;
import com.kg.sampll.repository.BookRepository;

import static org.hamcrest.Matchers.*;


import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = SampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class BookControllerIT {
    @Value("${local.server.port}")
    private int serverPort;
    private static final String BOOK_NAME_FIELD = "bookname";
    private static final String TEST_RESOURCE1 = "book";
    private static final String TEST_RESOURCE2 = "book";
    private static final String TEST_RESOURCE3 = "book/{bookId}";
    private static final String TEST_RESOURCE4 = "book/{bookId}";
    private static final String TEST_RESOURCE5 = "book/{bookId}";
    private static final String bookname = "DSP";

    private static final Long BOOK_ID = 102L;
    private static final Book FIRST_EVENT = new BookBuilder().BookId(BOOK_ID).BookName("bookname").Build();
    private static final Book SECOND_EVENT = new BookBuilder().BookId(103L).BookName("DSP").Build();
    private static final Book THIRD_EVENT = new BookBuilder().BookId(104L).BookName("DSP").Build();
    
    private Book firstEvent;
    private Book secondEvent;
    @Autowired
    private BookRepository bookRepository;

    @Before
     public void setUp() {
        bookRepository.deleteAll();
        firstEvent = bookRepository.saveAndFlush(FIRST_EVENT);
        secondEvent = bookRepository.saveAndFlush(SECOND_EVENT);
        RestAssured.port = serverPort;
    }
   @Test
    // GET ALL 
   public void getAllBook() {
        when().get(TEST_RESOURCE1).then().statusCode(HttpStatus.SC_OK).body(BOOK_NAME_FIELD,
                hasItems(bookname, bookname));
    }

    @Test
    // POST
    public void addBook() {
        given().body(THIRD_EVENT).contentType(ContentType.JSON).when().post(TEST_RESOURCE2).then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    //UPDATE
    public void updateBookById() {
        given().body(THIRD_EVENT).contentType(ContentType.JSON).when().put(TEST_RESOURCE3, firstEvent.getBookId())
                .then().statusCode(HttpStatus.SC_OK).body(BOOK_NAME_FIELD, is(bookname));

    }

    @Test
    //GET ONE BY ID
    public void getOneBookById() {
        when().get(TEST_RESOURCE4, firstEvent.getBookId()).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    // DELETE
    public void deleteById() {
        when().delete(TEST_RESOURCE5, secondEvent.getBookId()).then().statusCode(HttpStatus.SC_OK);
    }
} 