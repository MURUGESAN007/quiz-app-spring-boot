package com.example.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.QuizApp.dto.QuestionWrapper;
import com.example.QuizApp.dto.Response;
import com.example.QuizApp.exception.ResourceNotFoundException;
import com.example.QuizApp.model.Question;
import com.example.QuizApp.model.Quiz;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizrepo;
	
	@Autowired
	private QuestionRepository questionrepo;
	
	public String createQuiz(String category, int numQ, String title) {
		
		List<Question> questions = questionrepo.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizrepo.save(quiz);
		
		return "Quiz Created Successfully";
	}
	
	public List<QuestionWrapper> getQuiz(int id){
		Quiz quiz = quizrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz", " ID ", id));
		List<Question> questionfromDb = quiz.getQuestions();
		List<QuestionWrapper> questinsforUser = new ArrayList<QuestionWrapper>();
		
		for(Question q : questionfromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questinsforUser.add(qw);
		}
		return questinsforUser;
	}
	
	public int submitQuiz(int id,List<Response> responses){
		
		Map<Integer, String> answerMap = responses.stream()
			    .collect(Collectors.toMap(Response::getId, Response::getResponse));

		Quiz quiz = quizrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz", " ID ", id)); 
		List<Question> questions = quiz.getQuestions();
		int right = 0;

		for(Question q : questions) {
		    if(q.getRightAnswer().equals(answerMap.get(q.getId()))) {
		    	right++;
		    }
		}
		
		return right;
	}
	
	
}
