package com.kg.sampll.repository;

import java.io.Serializable;
import java.util.List;

import com.kg.sampll.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Serializable> {
	@Query(value = "SELECT b.BOOKNAME FROM BOOK b where b.BOOK_ID=101 ", nativeQuery = true)
	public List<Object> findBook();

	@Query(value = "SELECT b.BOOKNAME FROM BOOK b where b.BOOK_ID= :id", nativeQuery = true)
	public List<Object> findBook(@Param("id") Long id);
 }