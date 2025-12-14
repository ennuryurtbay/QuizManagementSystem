package com.project.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Görevleri önceliğe göre sıralayan polimorfik sınıf
public class PrioritySorter implements TaskSorter {
    @Override
    public List<Task> sort(List<Task> tasks) {
        // En yüksek öncelikli görevler (en büyük değer) en başta
        return tasks.stream()
            .sorted(Comparator.comparingInt(Task::getPriority).reversed())
            .collect(Collectors.toList());
    }
}