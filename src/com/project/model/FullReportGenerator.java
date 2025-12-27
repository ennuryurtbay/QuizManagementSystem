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
            report.append("\nSINAV: ").append(qz.getQuizName()).append(" (ID: ").append(qz.getQuizId()).append(")\n");
            report.append("----------------------------------------------------\n");
            
            if (qz.getQuestions().isEmpty()) {
                report.append("  Bu sınavda henüz soru bulunmamaktadır.\n");
                continue;
            }
            
            for (Question q : qz.getQuestions()) {
                report.append(String.format("  - %s (Puan: %d) [Doğru Cevap: %s]\n", 
                                            q.getText(), 
                                            q.getPoints(), 
                                            q.getCorrectAnswer()));
            }
        }
        return report.toString();
    }
}