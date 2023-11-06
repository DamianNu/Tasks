package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void fetchTrelloBoardsTest() {

        // Given
        List<TrelloBoardDto> listTrelloBoardDto = new ArrayList<>();
        listTrelloBoardDto.add(new TrelloBoardDto("101", "Trello Board Test1", new ArrayList<>()));
        listTrelloBoardDto.add(new TrelloBoardDto("102", "Trello Board Test2", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(listTrelloBoardDto);

        // When
        List<TrelloBoardDto> trelloBoardDto = trelloClient.getTrelloBoards();

        // Then
        assertEquals(2, trelloBoardDto.size());
        assertEquals("101", trelloBoardDto.get(0).getId());
        assertEquals("Trello Board Test1", trelloBoardDto.get(0).getName());
        assertEquals("102", trelloBoardDto.get(1).getId());
        assertEquals("Trello Board Test2", trelloBoardDto.get(1).getName());
    }

    @Test
    void createTrelloCardTest() {

        // Given
        CreatedTrelloCardDto newCardDto = new CreatedTrelloCardDto("101", "Trello Card Test", "Test.pl");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Trello Card Test", "Test", "up", "101");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@mail.com");

        //When
        CreatedTrelloCardDto createdCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("101", createdCardDto.getId());
        assertEquals("Trello Card Test", createdCardDto.getName());
        assertEquals("Test.pl", createdCardDto.getShortUrl());
    }
}