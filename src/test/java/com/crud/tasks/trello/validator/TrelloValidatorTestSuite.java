package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTestSuite {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void validateTrelloBoardsTest() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("101", "test", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("102", "Trello Board", new ArrayList<>());
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(trelloBoard1);
        trelloBoardsList.add(trelloBoard2);

        //When
        List<TrelloBoard> trelloBoardsListValidate = trelloValidator.validateTrelloBoards(trelloBoardsList);

        //Then
        assertEquals(1, trelloBoardsListValidate.size());
        assertEquals("Trello Board", trelloBoardsListValidate.get(0).getName());
        assertEquals("102", trelloBoardsListValidate.get(0).getId());
    }
}