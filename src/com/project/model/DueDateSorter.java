package com.project.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// görevleri bitiş tarihine göre sıralayan polimorfik sınıf
public class DueDateSorter implements TaskSorter {
    @Override
    public List<Task> sort(List<Task> tasks) {
        // en erken bitiş tarihi olanlar en başta
        return tasks.stream()
            .sorted(Comparator.comparing(Task::getDueDate))
            .collect(Collectors.toList());
    }
}