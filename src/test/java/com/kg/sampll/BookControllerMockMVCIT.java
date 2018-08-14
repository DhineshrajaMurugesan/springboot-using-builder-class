package com.kg.sampll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kg.sampll.controller.BookController;
import com.kg.sampll.model.Book;
import com.kg.sampll.repository.BookRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class, secure = false)

public class BookControllerMockMVCIT {
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private BookRepository bookRepository;

    private static final long BOOK_ID = 101L;
//    private static final Book NEW_BOOK = new BookBuilder().BookId(BOOK_ID).BookName("abc").Build();
   private static final Book OLD_BOOK = new BookBuilder().BookId(101L).BookName("bcd").Build();
 
   private static final Optional<Book> OLD_BOOK1=Optional.of(OLD_BOOK);
   List<Book> expectedbook = Arrays.asList(OLD_BOOK);
   @Test
   public void getAll() throws Exception {
       given(bookRepository.findAll()).willReturn(expectedbook);
       mockMvc.perform(get("/book").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
               .andExpect(content().json(
                       "[{'bookId':101,'bookname':'bcd'}]"));
   }
   @Test
   public void postmapping() throws Exception {
        when(bookRepository.findById(BOOK_ID)).thenReturn(OLD_BOOK1);
                mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON).content(asJsonString(OLD_BOOK1)))
                .andExpect(status().isOk());
   }
   @Test
   public void getOneItemsById() throws Exception {
       given(bookRepository.findById(BOOK_ID)).willReturn(OLD_BOOK1);
       mockMvc.perform(get("/book/101").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
               .andExpect(content().json(
                       "{'bookId':101,'bookname':'bcd'}"));
   }
   @Test
   public void updateById() throws Exception {
   when(bookRepository.findById(BOOK_ID)).thenReturn(OLD_BOOK1);
   
           mockMvc.perform(put("/book/101").contentType(MediaType.APPLICATION_JSON)
                   .content(asJsonString(OLD_BOOK1))).andExpect(status().isOk());
   }
   @Test
   public void deleteById() throws Exception {
   when(bookRepository.findById(BOOK_ID)).thenReturn(OLD_BOOK1);
   
           mockMvc.perform(delete("/book/101").contentType(MediaType.APPLICATION_JSON)
                   .content(asJsonString(OLD_BOOK1))).andExpect(status().isOk());
   }
   public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}