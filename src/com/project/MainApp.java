package com.project;

import com.project.model.*;
import com.project.view.MainFrame;
import javax.swing.*;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        QuizManager manager = new QuizManager();

        // Örnek bir Sınav (Quiz) ve Soru ekleyelim
        Quiz finalSinavi = new Quiz(1, "Nesne Tabanlı Programlama Finali");
        manager.addQuiz(finalSinavi);

        // Çoktan seçmeli bir soru ekleyelim (Kalıtım ve Polimorfizm örneği)
        MultipleChoiceQuestion q1 = new MultipleChoiceQuestion(
            1, "Java'da çok biçimlilik hangi anahtar kelimeyle sağlanır?", 
            "Polimorfizm", 20, Arrays.asList("Kalıtım", "Kapsülleme", "Polimorfizm")
        );
        finalSinavi.addQuestion(q1);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(manager);
            frame.setVisible(true);
        });
    }
}