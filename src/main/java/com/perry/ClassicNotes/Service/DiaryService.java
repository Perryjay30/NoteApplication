package com.perry.ClassicNotes.Service;


import com.perry.ClassicNotes.Data.Models.Diary;
import com.perry.ClassicNotes.Data.dto.Request.EntriesRequest;
import com.perry.ClassicNotes.Data.dto.Response.Response;
import com.perry.ClassicNotes.Data.dto.Request.UpdateDiaryRequest;
import com.perry.ClassicNotes.Data.dto.Request.CreateRequest;
import com.perry.ClassicNotes.Data.dto.Response.CreateResponse;

import java.util.Optional;

public interface DiaryService {
    CreateResponse CreateDiary(CreateRequest writeRequest);
    Response updateDiary(UpdateDiaryRequest updateDiaryRequest);
    Response deleteDiary(int id);
    Optional<Diary> getDiaryById(int id);
    Response addEntryToDiary(EntriesRequest entriesRequest);
//    Diary save(Diary diary);
}
