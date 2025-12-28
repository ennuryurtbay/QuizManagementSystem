package com.project.model;

import java.util.List;

public class FullReportGenerator extends ReportGenerator {
    @Override
    public String generateReport(List<Quiz> quizzes) {
        printHeader("TAM SINAV VE SORU RAPORU");
        StringBuilder report = new StringBuilder();
        
        if (quizzes.isEmpty()) {
            report.append("Kayıtlı sınav bulunamadı.\n");
            return report.toString();
        }
        
        for (Quiz qz : quizzes) {
            int quizTotalPoints = 0; // Her sınav için puan sayacı
            
            report.append("\nSINAV: ").append(qz.getQuizName()).append(" (ID: ").append(qz.getQuizId()).append(")\n");
            report.append("====================================================\n");
            
            if (qz.getQuestions().isEmpty()) {
                report.append("  Bu sınavda henüz soru bulunmamaktadır.\n");
            } else {
                for (Question q : qz.getQuestions()) {
                    report.append(String.format("  - %s (Puan: %d) [Doğru Cevap: %s]\n", 
                                                q.getText(), 
                                                q.getPoints(), 
                                                q.getCorrectAnswer()));
                    quizTotalPoints += q.getPoints(); // Puanları burada topluyoruz
                }
                
                // İSTEDİĞİN TOPLAM NOT KISMI BURASI:
                report.append("----------------------------------------------------\n");
                report.append(">> BU SINAVDAN ALINAN TOPLAM NOT: ").append(quizTotalPoints).append(" PUAN\n");
                report.append("====================================================\n");
            }
        }
        return report.toString();
    }
}