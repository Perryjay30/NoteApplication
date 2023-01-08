package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.exceptions.ClassicDiaryException;
import com.perry.ClassicNotes.data.models.Entry;
import com.perry.ClassicNotes.data.repository.EntriesRepository;
import com.perry.ClassicNotes.data.dto.request.EntriesRequest;
import com.perry.ClassicNotes.data.dto.response.EntryRetrievalResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.request.RetrieveRequest;
import com.perry.ClassicNotes.data.dto.response.WriteResponse;
import com.perry.ClassicNotes.validators.UserEntryValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EntriesServiceImpl implements EntriesService {

    @Autowired
    private EntriesRepository entriesRepository;

    @Override
    public WriteResponse writeInEntries(EntriesRequest writeRequest) {

        if(!UserEntryValidators.isValidateEntryTitle(writeRequest.getTitle()))
            throw new ClassicDiaryException("Title should not be more than 150 characters");
        if(!UserEntryValidators.isValidateEntryBody(writeRequest.getBody()))
            throw new ClassicDiaryException("Character limit exceeded");
        Entry myEntry = newEntryCreation(writeRequest);
        Entry savedEntry = entriesRepository.save(myEntry);

        return newEntryCreationResponse(savedEntry);
    }

    private WriteResponse newEntryCreationResponse(Entry savedEntry) {
        WriteResponse writeResponse = new WriteResponse();
        writeResponse.setMessage("Your entry has been saved");
        writeResponse.setStatusCode(201);
        writeResponse.setId(savedEntry.getId());
        return writeResponse;
    }

    private Entry newEntryCreation(EntriesRequest writeRequest) {
        Entry myEntry = new Entry();
        myEntry.setTitle(writeRequest.getTitle());
        myEntry.setBody(writeRequest.getBody());
        myEntry.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yy, HH:mm")));
        return myEntry;
    }

    @Override
    public EntryRetrievalResponse retrieveEntries(RetrieveRequest retrieveRequest) {

        Entry entryRetrieval = entriesRepository.findEntryByTitle(retrieveRequest.getTitle())
                .orElseThrow(() -> new ClassicDiaryException("Entry has not available "));
        EntryRetrievalResponse retrievalResponse = new EntryRetrievalResponse();
//        if(entryRetrieval.getTitle().equals(retrieveRequest.getTitle()))
            retrievalResponse.setMessage("Entry has been found");
//        else
//            retrievalResponse.setMessage("Unfortunately, entry can't be found");
        return retrievalResponse;
    }

    @Override
    public Response updateEntries(EntriesRequest updateEntriesRequest) {
        Optional<Entry> updateEntry = entriesRepository.findEntryById(updateEntriesRequest.getId());
        if(updateEntry.isEmpty()) throw  new ClassicDiaryException("Entry not found");
        Entry foundEntries = UpdatingEntries(updateEntriesRequest, updateEntry);
        entriesRepository.save(foundEntries);

        return new Response("Your entry has been updated successfully");
    }

    private Entry UpdatingEntries(EntriesRequest updateEntriesRequest, Optional<Entry> updateEntry) {
        Entry foundEntries = updateEntry.get();
//        foundEntries.setDate(updateEntriesRequest.getTheDate() != null && !updateEntriesRequest.getTheDate()
//                .equals("") ? updateEntriesRequest.getTheDate() : foundEntries.getDate());
        foundEntries.setTitle(updateEntriesRequest.getTitle() != null && !updateEntriesRequest.getTitle()
                .equals("") ? updateEntriesRequest.getTitle() : foundEntries.getTitle());
        foundEntries.setBody(updateEntriesRequest.getBody() != null && !updateEntriesRequest.getBody()
                .equals("") ? updateEntriesRequest.getBody() : foundEntries.getBody());
        return foundEntries;
    }

    @Override
    public Response deleteEntries(int id) {
        entriesRepository.deleteById(id);
        return new Response("Entry deleted successfully");
    }



    @Override
    public List<Entry> getAllEntries() {
        return entriesRepository.findAll();
    }

    @Override
    public Response deleteAllEntries() {
        entriesRepository.deleteAll();
        return new Response("All Entries deleted successfully");
    }
}
