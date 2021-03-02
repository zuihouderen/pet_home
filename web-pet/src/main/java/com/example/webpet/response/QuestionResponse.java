package com.example.webpet.response;


import com.example.webpet.entity.Question;

import java.util.List;

public class QuestionResponse {
    private Question question;
    private String message;
    private int success;
    private List<Question> questions;

    public QuestionResponse(Question question, String message, int success){
        this.message=message;
        this.success=success;
        this.question=question;
    }
    public QuestionResponse(List<Question> questions, String message, int success){
        this.message=message;
        this.success=success;
        this.questions=questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Question getQuestion() {
        return question;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
