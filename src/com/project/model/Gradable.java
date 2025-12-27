package com.project.model;

// Hocanın istediği Gradable arayüzü
public interface Gradable {
    boolean checkAnswer(String studentAnswer); // Cevabı kontrol eder
    int getPoints(); // Soru puanını döndürür
}