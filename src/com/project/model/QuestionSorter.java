package com.project.model;

import java.util.List;

// Soruları puanına veya metnine göre sıralamak için kullanılan arayüz (Polimorfizm)
public interface QuestionSorter {
    List<Question> sort(List<Question> questions);
}