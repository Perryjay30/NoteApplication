package com.perry.ClassicNotes.Service;

import com.perry.ClassicNotes.Data.Repository.DiaryRepository;
import com.perry.ClassicNotes.Data.dto.Request.CreateRequest;
import com.perry.ClassicNotes.Data.dto.Request.EntriesRequest;
import com.perry.ClassicNotes.Data.dto.Request.UpdateDiaryRequest;
import com.perry.ClassicNotes.Data.dto.Response.CreateResponse;
import com.perry.ClassicNotes.Data.dto.Response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DairyServiceImplTest {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private DiaryService diaryService;

    private CreateRequest createFirstDiary;

    @BeforeEach
    void alwaysStartWithThis() {
       createFirstDiary = new CreateRequest();
       createFirstDiary.setDiaryName("I'm getting better in Programming");
//       createFirstDiary.setDiaryEntries("Entries 007");

    }

    @Test
    void createDiary() {
       CreateResponse createResponse =
               diaryService.CreateDiary(createFirstDiary);
       assertNotNull(createResponse);
        System.out.println(createResponse);
       assertEquals("Diary Created successfully", createResponse.getMessage());
    }

    @Test
    void updateDiary() {
        UpdateDiaryRequest updateDiaryRequest = new UpdateDiaryRequest();
        updateDiaryRequest.setDiaryId(2);
        updateDiaryRequest.setDiaryName("my first business");
//        updateDiaryRequest.setDiaryEntries("Entries 011");
        Response updateResponse =
                diaryService.updateDiary(updateDiaryRequest);
//        Optional<Diary> foundDiary = diaryRepository.findById
//                (updateDiaryRequest.getDiaryId());
//        assertNotNull(foundDiary);
//        Diary updateDiary = new Diary();
//        updateDiary.setName(updateDiaryRequest.getDiaryName());
//        Set<String> entriesList = updateDiary.getDiaryEntries();
//        entriesList.add(updateDiaryRequest.getDiaryEntries());
//        diaryRepository.save(updateDiary);
        assertEquals("Diary has been updated", updateResponse.getMessage());
    }

    @Test
    void deleteDiary() {
        Response deleteResponse = diaryService.deleteDiary(13);
        assertNotNull(deleteResponse);
        assertEquals("Diary deleted successfully",
                deleteResponse.getMessage());
    }
    @Test
    void addEntryToDiary(){
//        Response response = diaryService.addEntryToDiary(
//                new AddEntryRequest(1,"test title","test body")
//        );
//
//        assertEquals(response.getMessage(), "entry added successfully");
        EntriesRequest entryRequest = new EntriesRequest();
        entryRequest.setId(5);
        entryRequest.setBody("I'm trying out a different method");
        entryRequest.setTitle("My title");
        Response response = diaryService.addEntryToDiary(entryRequest);
        assertEquals("entry added successfully", response.getMessage());


    }

}