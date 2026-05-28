package com.example.QuizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuizApp.dto.QuestionWrapper;
import com.example.QuizApp.dto.Response;
import com.example.QuizApp.service.QuizService;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

	@Autowired
	QuizService quizservice;
	
	@PostMapping
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
		return new ResponseEntity<String>(quizservice.createQuiz(category,numQ,title), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
		return new ResponseEntity<List<QuestionWrapper>>(quizservice.getQuiz(id), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/submit")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
		return new ResponseEntity<Integer>(quizservice.submitQuiz(id, responses), HttpStatus.OK);
	}
}
