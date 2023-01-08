package com.perry.ClassicNotes.service;


import com.perry.ClassicNotes.data.models.Diary;
import com.perry.ClassicNotes.data.dto.request.EntriesRequest;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.request.UpdateDiaryRequest;
import com.perry.ClassicNotes.data.dto.request.CreateRequest;
import com.perry.ClassicNotes.data.dto.response.CreateResponse;

import java.util.Optional;

public interface DiaryService {
    CreateResponse CreateDiary(CreateRequest writeRequest);
    Response updateDiary(UpdateDiaryRequest updateDiaryRequest);
    Response deleteDiary(int id);
    Optional<Diary> getDiaryById(int id);
    Response addEntryToDiary(EntriesRequest entriesRequest);
//    Diary save(Diary diary);
}
