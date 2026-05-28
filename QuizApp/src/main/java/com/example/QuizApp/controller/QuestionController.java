package com.example.QuizApp.controller; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuizApp.model.Question;
import com.example.QuizApp.service.QuestionService;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionservice;

	@PostMapping
	public ResponseEntity<String> addQuestion(@RequestBody Question q){
		return new ResponseEntity(questionservice.addQuestion(q), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Question>> getAllQuestion(){
		List<Question> ques = questionservice.getAllQuestion();
		return new ResponseEntity<List<Question>>(ques, HttpStatus.OK);
	}
	
	@GetMapping("/{category}")
	public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
		List<Question> ques = questionservice.getByCategory(category);
		return new ResponseEntity<List<Question>>(ques, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteQuetionById(@PathVariable int id){
		return new ResponseEntity(questionservice.deleteById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateQuestionById(@PathVariable int id,@RequestBody Question q){
		return new ResponseEntity(questionservice.updateById(id, q), HttpStatus.CREATED);
	}
}
