package com.example.QuizApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.QuizApp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	public List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	public List<Question> findRandomQuestionByCategory(String category, int numQ);

}
