package com.project.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// Task sınıfından kalıtım alır (extends)
public class TimedTask extends Task {
    private LocalDateTime startTime;
    private long estimatedDurationMinutes; 
    // Üst sınıfın (Task) constructor'ını çağırır ve ek alanları alır
    public TimedTask(int taskId, String title, String description, int priority, LocalDateTime dueDate, User assignedUser, LocalDateTime startTime, long estimatedDurationMinutes) {
        super(taskId, title, description, priority, dueDate, assignedUser);
        this.startTime = startTime;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    // tahmini bitiş zamanını hesaplar
    public LocalDateTime getEstimatedEndTime() {
        return startTime.plus(estimatedDurationMinutes, ChronoUnit.MINUTES);
    }

    // kapsülleme için getter/setter'lar
    public LocalDateTime getStartTime() { return startTime; }
    public long getEstimatedDurationMinutes() { return estimatedDurationMinutes; }
    
    // TimedTask'i override 
    @Override
    public void completeTask() {
        super.completeTask(); // Task'ın tamamlama metodunu çağır
        System.out.println("Zamanli gorev kaydi tamamlandi. Baslangic: " + startTime);
    }
}