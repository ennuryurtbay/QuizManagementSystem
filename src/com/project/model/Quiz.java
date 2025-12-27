package com.project.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int quizId;
    private String quizName;
    private List<Question> questions;

    public Quiz(int quizId, String quizName) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question q) { this.questions.add(q); }
    public List<Question> getQuestions() { return questions; }
    public String getQuizName() { return quizName; }
    public int getQuizId() { return quizId; }
    
    @Override
    public String toString() { return quizName; }
}