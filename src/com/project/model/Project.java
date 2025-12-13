package com.project.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int projectId;
    private String projectName;
    private String description;
    private List<Task> tasks; // Görevler Listesi

    public Project(int projectId, String projectName, String description) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

    // Görev Projeye Atama (Minimum Özellik)
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println(task.getTitle() + " " + projectName + " projesine atandi.");
    }
    
    // Kapsülleme için getter'lar
    public int getProjectId() { return projectId; }
    public String getProjectName() { return projectName; }
    public List<Task> getTasks() { return tasks; }
}