package com.project.model;
//Geliştirme sürecinin başında 'Task' olarak modellenen bu yapı, sınav sistemine özelleştirilerek 'Question' sınıfına dönüştürülmüştür.
public abstract class Question implements Gradable {
    private int id; // Değişken ismimiz bu
    private String text;
    private String correctAnswer;
    private int points;

    public Question(int id, String text, String correctAnswer, int points) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    // BU METODU EKLE: MainFrame burayı arıyor
    public int getId() {
        return id;
    }

    @Override
    public boolean checkAnswer(String studentAnswer) {
        return this.correctAnswer.equalsIgnoreCase(studentAnswer);
    }

    public String getText() { return text; }
    public int getPoints() { return points; }
    public String getCorrectAnswer() { return correctAnswer; }
}