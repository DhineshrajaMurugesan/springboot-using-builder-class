package com.kg.sampll.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long bookId;
 @Column
   private String bookname;
    public Long getBookId() {

        return bookId;

    }
      public void setBookId(Long bookId) {
         this.bookId = bookId;
      }

    public String getBookname() {
           return bookname;
       }
    public void setBookname(String bookname) {
      this.bookname = bookname;
   }
}

  