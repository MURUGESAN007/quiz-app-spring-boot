package com.example.QuizApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.QuizApp.exception.ResourceNotFoundException;
import com.example.QuizApp.model.Question;
import com.example.QuizApp.repository.QuestionRepository;


@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionrepo;
	
	public List<Question> getAllQuestion(){
		return questionrepo.findAll();
	}

	public String addQuestion(Question q) {
		questionrepo.save(q);
		return "Question Added Successfully";
	}
	
	public List<Question> getByCategory(String category){
		return questionrepo.findByCategory(category);
	}
	
	public String deleteById(int id){
		Question q = questionrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Question ", " ID ", id));
		questionrepo.delete(q);
		
		return "Question Deleted Successfully";
	}
	
	public String updateById(int id,Question q){
		Question db_q = questionrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Question ", " ID ", id));
		
		db_q.setCategory(q.getCategory());
		db_q.setDifficultylevel(q.getDifficultylevel());
		db_q.setOption1(q.getOption1());
		db_q.setOption2(q.getOption2());
		db_q.setOption3(q.getOption3());
		db_q.setOption4(q.getOption4());
		db_q.setQuestionTitle(q.getQuestionTitle());
		db_q.setRightAnswer(q.getRightAnswer());
		
		questionrepo.save(db_q);
		
		return "Question Updated Successfully";
		
	}
	
}
