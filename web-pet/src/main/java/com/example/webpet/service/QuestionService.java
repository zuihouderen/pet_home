package com.example.webpet.service;

import com.example.webpet.entity.Question;
import com.example.webpet.entity.QuestionExample;
import com.example.webpet.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    public List<Question> getQuestions(QuestionExample questionExample){
        return questionMapper.selectByExample(questionExample);
    }

    public long countQuestions(QuestionExample questionExample) {
        return questionMapper.countByExample(questionExample);
    }

    public int newQuestion(Question question) {
        return questionMapper.insertSelective(question);
    }

    public int deleteQuestion(QuestionExample questionExample) {
        return questionMapper.deleteByExample(questionExample);
    }

    public int updateQuestion(Question question) {
        return questionMapper.updateByPrimaryKeySelective(question);
    }
}
