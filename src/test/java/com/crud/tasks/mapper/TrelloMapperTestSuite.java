package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> listTrelloBoardDto = new ArrayList<>();
        listTrelloBoardDto.add(new TrelloBoardDto("101", "Test1", new ArrayList<>()));
        listTrelloBoardDto.add(new TrelloBoardDto("102", "Test2", new ArrayList<>()));

        //When
        List<TrelloBoard> listTrelloBoard = trelloMapper.mapToBoards(listTrelloBoardDto);

        //Then
        assertEquals(2, listTrelloBoard.size());
        assertEquals("101", listTrelloBoard.get(0).getId());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> listTrelloBoard = new ArrayList<>();
        listTrelloBoard.add(new TrelloBoard("101", "Test1", new ArrayList<>()));
        listTrelloBoard.add(new TrelloBoard("102", "Test2", new ArrayList<>()));

        //When
        List<TrelloBoardDto> listTrelloBoardDto = trelloMapper.mapToBoardsDto(listTrelloBoard);

        //Then
        assertEquals(2, listTrelloBoardDto.size());
        assertEquals("102", listTrelloBoardDto.get(1).getId());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("101", "Test1", true));
        trelloListDto.add(new TrelloListDto("102", "Test2", false));

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(2, trelloList.size());
        assertEquals("Test2", trelloList.get(1).getName());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("101", "Test1", true));
        trelloList.add(new TrelloList("102", "Test2", false));

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(2, trelloListDto.size());
        assertEquals("Test1", trelloListDto.get(0).getName());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test Card1", "First test", "center", "101");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Test Card1", trelloCardDto.getName());
        assertEquals("First test", trelloCardDto.getDescription());
        assertEquals("center", trelloCardDto.getPos());
        assertEquals("101", trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card Dto", "First test", "center", "1011");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Test Card Dto", trelloCard.getName());
        assertEquals("First test", trelloCard.getDescription());
        assertEquals("center", trelloCard.getPos());
        assertEquals("1011", trelloCard.getListId());
    }
}