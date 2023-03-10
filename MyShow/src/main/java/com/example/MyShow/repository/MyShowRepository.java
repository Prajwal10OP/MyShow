package com.example.MyShow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyShow.model.BookRequest;

public interface MyShowRepository extends JpaRepository<BookRequest, Integer> {

}
