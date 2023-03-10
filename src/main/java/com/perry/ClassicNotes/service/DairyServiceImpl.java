package com.perry.ClassicNotes.service;


import com.perry.ClassicNotes.data.models.Diary;
import com.perry.ClassicNotes.data.models.Entry;
import com.perry.ClassicNotes.data.repository.DiaryRepository;
import com.perry.ClassicNotes.data.repository.EntriesRepository;
import com.perry.ClassicNotes.data.dto.request.CreateRequest;
import com.perry.ClassicNotes.data.dto.request.EntriesRequest;
import com.perry.ClassicNotes.data.dto.request.UpdateDiaryRequest;
import com.perry.ClassicNotes.data.dto.response.CreateResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class DairyServiceImpl implements DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private EntriesRepository entryRepository;


    @Override
    public CreateResponse CreateDiary(CreateRequest createRequest) {
        Diary diary = savedDiaryResponse(createRequest);

        Diary savedDiary = diaryRepository.save(diary);

        CreateResponse createResponse = new CreateResponse();
        createResponse.setId(savedDiary.getId());
        createResponse.setMessage("Diary Created successfully");
        createResponse.setStatusCode(201);
        return createResponse;
    }

    private Diary savedDiaryResponse(CreateRequest createRequest) {
        Diary diary = new Diary();
        diary.setName(createRequest.getDiaryName());
        return diary;
    }

    @Override
    public Response updateDiary(UpdateDiaryRequest updateDiaryRequest) {
        var findDiary = diaryRepository.findById(updateDiaryRequest.getDiaryId());
        if (findDiary.isEmpty()) throw new RuntimeException("Diary not found");
        Diary diaryUpdate = findDiary.get();
        diaryUpdate.setId(updateDiaryRequest.getDiaryId());
        diaryUpdate.setName(updateDiaryRequest.getDiaryName());
        diaryRepository.save(diaryUpdate);
        return new Response("Diary has been updated");
    }

    @Override
    public Response deleteDiary(int id) {
        diaryRepository.deleteById(id);
        return new Response("Diary deleted successfully");
    }

    @Override
    public Optional<Diary> getDiaryById(int id) {
        return diaryRepository.findById(id);
    }

    @Override
    public Response addEntryToDiary(EntriesRequest entriesRequest) {
//        Diary diary = getDiaryById(request.diaryId()).orElseThrow(
//                () -> new RuntimeException("diary not found")
//        );
//        Entry entry = Entry.builder()
//                .body(request.body())
//                .title(request.title())
//                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yy, hh:mm")))
//                .build();
//        diary.getDiaryEntries().add(entry);
//
//        diaryRepository.save(diary);
//        return Response.builder()
//                .message("entry added successfully")
////                .entry(entry)
//                .build();
        Diary diary = diaryRepository.findById(entriesRequest.getId())
                .orElseThrow(() -> new RuntimeException("Diary not found"));
        Entry entry = new Entry();
        entry.setBody(entriesRequest.getBody());
        entry.setTitle(entriesRequest.getTitle());
        entry.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yy, hh:mm")));
        diary.getDiaryEntries().add(entry);
        entryRepository.save(entry);
        return new Response("entry added successfully");
    }
}
