package com.project.view;

import com.project.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MainFrame extends JFrame {
    private QuizManager quizManager;
    private JTable questionTable;
    private DefaultTableModel tableModel;

    public MainFrame(QuizManager quizManager) {
        this.quizManager = quizManager;
        
        // Pencere Ayarları
        setTitle("Quiz / Sınav Yönetim Sistemi - Ennur");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ana Yerleşim
        setLayout(new BorderLayout(10, 10));

        // 1. ÜST PANEL: Sınav İşlemleri
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(44, 62, 80)); // Koyu lacivert tema
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAddQuestion = new JButton("Yeni Soru Ekle");
        btnAddQuestion.setBackground(new Color(39, 174, 96)); // Yeşil
        btnAddQuestion.setForeground(Color.WHITE);

        JButton btnStartQuiz = new JButton("Sınavı Başlat / Güncelle");
        btnStartQuiz.setBackground(new Color(41, 128, 185)); // Mavi
        btnStartQuiz.setForeground(Color.WHITE);

        topPanel.add(btnAddQuestion);
        topPanel.add(btnStartQuiz);
        add(topPanel, BorderLayout.NORTH);

        // 2. ORTA PANEL: Soru Listesi Tablosu
        // Sütunları Quiz sistemine göre güncelledik
        String[] columns = {"ID", "Soru Metni", "Doğru Cevap", "Puan", "Tip"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        questionTable = new JTable(tableModel);
        questionTable.setRowHeight(30);
        
        // Tablo Başlık Tasarımı
        JTableHeader header = questionTable.getTableHeader();
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        add(new JScrollPane(questionTable), BorderLayout.CENTER);

        // 3. BUTON AKSİYONLARI
        
        // Soru Ekleme
     // MainFrame içindeki buton aksiyonunu şu şekilde kontrol et:
        btnAddQuestion.addActionListener(e -> {
            // quizManager'ı parametre olarak gönderiyoruz
            AddTaskDialog dialog = new AddTaskDialog(this, quizManager); 
            dialog.setVisible(true);
            if (dialog.isSucceeded()) {
                // Yeni eklenen soruyu kaydetmek için dialogdan verileri alalım
                Quiz secilenQuiz = dialog.getSelectedQuiz();
                
                // Polimorfizm örneği: MultipleChoiceQuestion oluşturup ekleyebiliriz
                Question yeniSoru = new MultipleChoiceQuestion(
                    (int)(Math.random()*1000), 
                    dialog.getQuestionText(), 
                    "Cevap", // Varsayılan cevap
                    dialog.getPoints(), 
                    java.util.Arrays.asList("A", "B", "C")
                );
                
                secilenQuiz.addQuestion(yeniSoru);
                refreshTable();
            }
        });

        // Listeyi Güncelleme
        btnStartQuiz.addActionListener(e -> {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Soru listesi güncellendi!");
        });

        refreshTable();
    }

    /**
     * Tabloyu QuizManager'daki güncel sorularla doldurur.
     * Polimorfizm: Question tipindeki tüm sorular (MultipleChoice vb.) burada listelenir.
     */
    public void refreshTable() {
        tableModel.setRowCount(0);
        // Tüm quizlerdeki soruları dönerek tabloya ekle
        for (Quiz qz : quizManager.getQuizzes()) {
            for (Question q : qz.getQuestions()) {
                tableModel.addRow(new Object[]{
                    q.getId(),
                    q.getText(),
                    q.getCorrectAnswer(),
                    q.getPoints(),
                    (q instanceof MultipleChoiceQuestion) ? "Çoktan Seçmeli" : "Doğru/Yanlış"
                });
            }
        }
    }
}