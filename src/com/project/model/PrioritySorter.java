package com.project.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Soruları puan değerine göre yüksekten düşüğe sıralar
public class PrioritySorter implements QuestionSorter {
    @Override
    public List<Question> sort(List<Question> questions) {
        return questions.stream()
            .sorted(Comparator.comparingInt(Question::getPoints).reversed())
            .collect(Collectors.toList());
    }
}