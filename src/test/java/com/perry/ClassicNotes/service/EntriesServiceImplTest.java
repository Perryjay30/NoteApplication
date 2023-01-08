package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.models.Entry;
import com.perry.ClassicNotes.data.dto.request.EntriesRequest;
import com.perry.ClassicNotes.data.dto.request.RetrieveRequest;
import com.perry.ClassicNotes.data.dto.response.EntryRetrievalResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.WriteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EntriesServiceImplTest {

    @Autowired
    private EntriesService entriesService;
    private EntriesRequest entriesRequest;
    private EntriesRequest entriesRequest2;

    @BeforeEach
    void doThisRepeatedly() {
    }

    @Test
    void writeInEntries() {
        entriesRequest = new EntriesRequest();
        entriesRequest.setTheDate(LocalDateTime.now());
        entriesRequest.setTitle("My first love");
        entriesRequest.setBody("A regular expression consists of literal characters and special symbols. \n" +
                "specifies some predefined character classes that can be used with regular expressions. A\n" +
                "character class is an escape sequence that represents a group of characters. ");

        entriesRequest2 = new EntriesRequest();
        entriesRequest2.setTheDate(LocalDateTime.now());
        entriesRequest2.setTitle("My days learning Software programming");
        entriesRequest2.setBody("Class String provides several methods for performing regular-expression operations,\n" +
                "the simplest of which is the matching operation. String method matches receives.");
        WriteResponse writeResponse =
                entriesService.writeInEntries(entriesRequest2);
        assertNotNull(writeResponse);
        System.out.println(writeResponse);
        assertEquals("Your entry has been saved",
                writeResponse.getMessage());
    }

    @Test
    void updateEntries() {
        EntriesRequest updatingEntry = new EntriesRequest();
        updatingEntry.setId(17);
        updatingEntry.setTheDate(LocalDateTime.now());
        updatingEntry.setTitle("How I became rich");
        updatingEntry.setBody("A regular expression is a String that describes a search pattern for matching characters in\n" +
                "other Strings.");
        Entry newEntry = new Entry();
        newEntry.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yy, hh:mm")));
        newEntry.setTitle(updatingEntry.getTitle());
        newEntry.setBody(updatingEntry.getBody());
        Response response = entriesService.updateEntries(updatingEntry);
        System.out.println(response);
        assertEquals("Your entry has been updated successfully", response.getMessage());

    }

    @Test
    void retrieveEntries() {
        RetrieveRequest retrieveEntry = new RetrieveRequest();
        retrieveEntry.setTitle("My days learning Software programming");
        EntryRetrievalResponse retrievalResponse = entriesService.retrieveEntries(retrieveEntry);
        System.out.println(retrievalResponse);
        assertEquals("Entry has been found", retrievalResponse.getMessage());
    }

    @Test
    void deleteEntries() {
        Response deleteResponse = entriesService.deleteEntries(17);
        System.out.println(deleteResponse);
        assertEquals("Entry deleted successfully",
                deleteResponse.getMessage());
    }

    @Test
    void deleteAllEntries() {
        Response response = entriesService.deleteAllEntries();
        assertEquals("All Entries deleted successfully",
                response.getMessage());
    }



}