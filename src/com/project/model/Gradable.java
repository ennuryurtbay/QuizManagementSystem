package com.project.model;


public interface Gradable {
    boolean checkAnswer(String studentAnswer); // Cevabı kontrol eder
    int getPoints(); // Soru puanını döndürür
}