package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.project.model.*;
import java.time.LocalDateTime;

public class ProjectManagerTest {

    @Test
    public void testAddUser() throws UserNotFoundException {
        ProjectManager manager = new ProjectManager();
        User user = new User(1, "Test Kullanicisi", "test@mail.com");
        
        manager.addUser(user);
        
        // Kullanıcının başarıyla eklendiğini ve ID ile bulunabildiğini test et
        assertNotNull(manager.findUserById(1));
        assertEquals("Test Kullanicisi", manager.findUserById(1).getUsername());
    }

    @Test
    public void testTaskCreation() {
        ProjectManager manager = new ProjectManager();
        Project project = new Project(101, "Test Projesi", "Aciklama");
        User user = new User(1, "Ennur", "ennur@mail.com");
        
        Task task = manager.createTaskAndAssign("Test Gorevi", "Detay", 5, 
                                               LocalDateTime.now(), user, project);
        
        // Görevin projeye atanıp atanmadığını test et
        assertEquals(1, project.getTasks().size());
        assertEquals("Test Gorevi", project.getTasks().get(0).getTitle());
    }
}