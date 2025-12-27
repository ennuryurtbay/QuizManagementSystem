package com.project.model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;

    public MultipleChoiceQuestion(int id, String text, String correctAnswer, int points, List<String> options) {
        super(id, text, correctAnswer, points);
        this.options = options;
    }

    // Seçenekleri listelemek için ek metot
    public List<String> getOptions() { return options; }
}