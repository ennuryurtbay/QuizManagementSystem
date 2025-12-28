package com.project.view;

import com.project.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private QuizManager manager;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTotalQuestions, lblTotalPoints;
    private JList<Quiz> quizJList; 
    private DefaultListModel<Quiz> listModel;
    private Quiz selectedQuiz;

    public MainFrame(QuizManager manager) {
        this.manager = manager;
        setTitle("Quiz Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        // Renkler
        Color headerColor = new Color(33, 37, 41);
        Color sideColor = new Color(241, 243, 245);

        // 1. SOL PANEL (Sınav Listesi)
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(250, 650));
        sidePanel.setBackground(sideColor);
        
        listModel = new DefaultListModel<>();
        quizJList = new JList<>(listModel);
        quizJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedQuiz = quizJList.getSelectedValue();
                if (selectedQuiz != null) refreshTable(selectedQuiz.getQuestions());
            }
        });

        sidePanel.add(new JLabel("  MEVCUT SINAVLAR"), BorderLayout.NORTH);
        sidePanel.add(new JScrollPane(quizJList), BorderLayout.CENTER);

        lblTotalQuestions = new JLabel("Toplam Soru: 0");
        lblTotalPoints = new JLabel("Toplam Puan: 0");
        JPanel stats = new JPanel(new GridLayout(2,1));
        stats.add(lblTotalQuestions); stats.add(lblTotalPoints);
        sidePanel.add(stats, BorderLayout.SOUTH);

        // 2. ÜST PANEL
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(headerColor);
        JButton btnAddQuiz = new JButton("Yeni Sınav");
        JButton btnAddQuestion = new JButton("Soru Ekle");
        JButton btnStart = new JButton("Sınavı Başlat");
        JButton btnReport = new JButton("Yönetici Raporu");
        topPanel.add(btnAddQuiz); topPanel.add(btnAddQuestion); topPanel.add(btnStart); topPanel.add(btnReport);

        // 3. MERKEZ PANEL (SORU VE CEVAP GİZLİ)
        // İstediğin gibi hem Soru hem de Cevap durumunu gizli gösteriyoruz
        String[] columns = {"ID", "Soru Durumu", "Cevap Durumu", "Puan Değeri"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        
        // AKSİYONLAR
        btnAddQuiz.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Sınav Adı:");
            if (name != null) {
                Quiz nq = new Quiz(manager.getQuizzes().size() + 1, name);
                manager.addQuiz(nq);
                updateQuizList();
                quizJList.setSelectedValue(nq, true);
            }
        });

        btnAddQuestion.addActionListener(e -> addNewQuestion());
        
        btnStart.addActionListener(e -> startQuizSession());

        btnReport.addActionListener(e -> {
            FullReportGenerator gen = new FullReportGenerator();
            JTextArea area = new JTextArea(20, 50);
            area.setText(gen.generateReport(manager.getQuizzes()));
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Detaylı Yönetici Raporu", JOptionPane.INFORMATION_MESSAGE);
        });

        add(topPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        updateQuizList();
    }

    private void updateQuizList() {
        listModel.clear();
        for (Quiz q : manager.getQuizzes()) listModel.addElement(q);
        if (!manager.getQuizzes().isEmpty() && selectedQuiz == null) quizJList.setSelectedIndex(0);
    }

    private void startQuizSession() {
        if (selectedQuiz == null || selectedQuiz.getQuestions().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seçili sınavda soru bulunamadı!");
            return;
        }
        int score = 0;
        for (Question q : selectedQuiz.getQuestions()) {
            String ans = JOptionPane.showInputDialog(this, "SORU: " + q.getText());
            if (ans != null && q.checkAnswer(ans)) score += q.getPoints();
        }
        JOptionPane.showMessageDialog(this, "Sınav Bitti. Toplam Puanınız: " + score);
    }

    private void addNewQuestion() {
        if (selectedQuiz == null) return;
        try {
            String txt = JOptionPane.showInputDialog(this, "Soru Metni:");
            String ans = JOptionPane.showInputDialog(this, "Doğru Cevap:");
            int pts = Integer.parseInt(JOptionPane.showInputDialog(this, "Puan Değeri:"));
            selectedQuiz.addQuestion(new MultipleChoiceQuestion(selectedQuiz.getQuestions().size()+1, txt, ans, pts, null));
            refreshTable(selectedQuiz.getQuestions());
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Geçersiz giriş!"); }
    }

    // TABLO GÜNCELLEME: Hem Soru hem Cevap Gizlendi
    private void refreshTable(List<Question> questions) {
        tableModel.setRowCount(0);
        int total = 0;
        for (Question q : questions) {
            tableModel.addRow(new Object[]{
                q.getId(), 
                "Soru Hazır (Gizli)", 
                "Cevap Kayıtlı (Gizli)", 
                q.getPoints()
            });
            total += q.getPoints();
        }
        lblTotalQuestions.setText("Toplam Soru: " + questions.size());
        lblTotalPoints.setText("Toplam Puan: " + total);
        setTitle("Quiz System - Active Quiz: " + selectedQuiz.getQuizName());
    }
}