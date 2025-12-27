package com.project.model;

import java.util.*;

public class QuizManager {
    private List<Quiz> quizzes = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    // Yeni Quiz ekleme
    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    // MainFrame'deki tablonun verileri buradan gelir
    public List<Quiz> getProjects() {
        return quizzes;
    }

    public void addStudent(Student s) {
        students.add(s);
    }
}