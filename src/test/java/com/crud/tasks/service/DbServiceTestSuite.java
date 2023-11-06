package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DbServiceTestSuite {

    @Autowired
    private DbService service;

    @Test
    void saveTaskTest() {
        //Given
        Task task1 = new Task(null, "Task1 Test", "Test1");

        //When
        service.saveTask(task1);
        Long idTask1 = task1.getId();

        //Then
        assertNotNull(idTask1);

        //CleanUp
        service.deleteTask(idTask1);
    }

    @Test
    void getTaskTest() throws TaskNotFoundException {
        //Given
        Task task = new Task(null, "Task1 Test", "Test1");

        //When
        service.saveTask(task);
        Long idTask = task.getId();
        Task taskTest = service.getTask(idTask);

        //Then
        assertEquals(idTask, taskTest.getId());
        assertEquals("Task1 Test", taskTest.getTitle());
        assertEquals("Test1", taskTest.getContent());

        //CleanUp
        service.deleteTask(idTask);
    }
}