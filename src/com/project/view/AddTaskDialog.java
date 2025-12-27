package com.project.view;

import com.project.model.*;
import javax.swing.*;
import java.awt.*;

public class AddTaskDialog extends JDialog {
    private JTextField txtText = new JTextField(20);
    private JSpinner spPoints = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    private JComboBox<Quiz> comboQuizzes;
    private boolean succeeded = false;

    public AddTaskDialog(Frame parent, QuizManager manager) {
        super(parent, "Yeni Soru Detayları", true);
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Soru Metni:"));
        panel.add(txtText);

        panel.add(new JLabel("Puan:"));
        panel.add(spPoints);

        panel.add(new JLabel("Sınav Seçin:"));
        // Burada manager içindeki Quiz listesini çekiyoruz
        comboQuizzes = new JComboBox<>(manager.getQuizzes().toArray(new Quiz[0]));
        panel.add(comboQuizzes);

        JButton btnAdd = new JButton("Kaydet");
        btnAdd.addActionListener(e -> {
            succeeded = true;
            dispose();
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(btnAdd, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
    }

    public String getQuestionText() { return txtText.getText(); }
    public int getPoints() { return (int) spPoints.getValue(); }
    public Quiz getSelectedQuiz() { return (Quiz) comboQuizzes.getSelectedItem(); }
    public boolean isSucceeded() { return succeeded; }
}