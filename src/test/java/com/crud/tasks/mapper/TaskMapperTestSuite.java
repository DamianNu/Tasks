package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTestSuite {
    @Autowired
    TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto1 = new TaskDto(10L, "Task1 Test", "Test1");

        //When
        Task task1 = taskMapper.mapToTask(taskDto1);

        //Then
        assertEquals(10L, task1.getId());
        assertEquals("Task1 Test", task1.getTitle());
        assertEquals("Test1", task1.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task1 = new Task(10L, "Task1 Test", "Test1");
        //When
        TaskDto taskDto1 = taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals(10L, taskDto1.getId());
        assertEquals("Task1 Test", taskDto1.getTitle());
        assertEquals("Test1", taskDto1.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        List<Task> listTask = new ArrayList<>();
        listTask.add(new Task(10L, "Task1 Test", "Test1"));
        listTask.add(new Task(11L, "Task2 Test", "Test2"));

        //When
        List<TaskDto> listTaskDto = taskMapper.mapToTaskDtoList(listTask);

        //Then
        assertEquals(2, listTaskDto.size());
        assertEquals(10L, listTaskDto.get(0).getId());
        assertEquals("Task1 Test", listTaskDto.get(0).getTitle());
        assertEquals("Test1", listTaskDto.get(0).getContent());
        assertEquals(11L, listTaskDto.get(1).getId());
        assertEquals("Task2 Test", listTaskDto.get(1).getTitle());
        assertEquals("Test2", listTaskDto.get(1).getContent());
    }
}