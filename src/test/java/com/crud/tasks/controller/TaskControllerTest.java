package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchTasksTest() throws Exception {
        // Given
        List<Task> listTask = new ArrayList<>();
        listTask.add(new Task(1L, "Test Task1", "Test1"));
        listTask.add(new Task(2L, "Test Task2", "Test2"));
        listTask.add(new Task(3L, "Test Task3", "Test3"));
        List<TaskDto> listTaskDto = new ArrayList<>();
        listTaskDto.add(new TaskDto(1L, "Test Task1", "Test1"));
        listTaskDto.add(new TaskDto(2L, "Test Task2", "Test2"));
        listTaskDto.add(new TaskDto(3L, "Test Task3", "Test3"));

        when(service.getAllTasks()).thenReturn(listTask);
        when(taskMapper.mapToTaskDtoList(listTask)).thenReturn(listTaskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    void shouldFetchTaskByIdTest() throws Exception {
        // Given
        Task task = new Task(1L, "Test Task1", "Test1");
        TaskDto taskDto = new TaskDto(1L, "Test Task1", "Test1");

        when(service.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test1")));
    }

    @Test
    void deleteTaskByIdTest() throws Exception {
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void updateTaskTest() throws Exception {
        // Given
        Task task = new Task(1L, "Test Task1", "Test1");
        Task saveTask = new Task(1L, "Test Task1save", "Test1");
        TaskDto taskDto = new TaskDto(1L, "Test Task1", "Test1");
        TaskDto saveTaskDto = new TaskDto(1L, "Test Task1save", "Test1");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(saveTask);
        when(taskMapper.mapToTaskDto(saveTask)).thenReturn(saveTaskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Task1save")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test1")));
    }

    @Test
    void createTaskTest() throws Exception {
        // Given
        Task task = new Task(1L, "Test Task1", "Test1");
        TaskDto taskDto = new TaskDto(1L, "Test Task1", "Test1");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}