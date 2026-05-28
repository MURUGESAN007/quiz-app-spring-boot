package com.example.QuizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QuizApp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
