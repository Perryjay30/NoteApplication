package com.perry.ClassicNotes.Controller;

import com.perry.ClassicNotes.Data.dto.Request.CreateRequest;
import com.perry.ClassicNotes.Data.dto.Request.EntriesRequest;
import com.perry.ClassicNotes.Data.dto.Request.UpdateDiaryRequest;
import com.perry.ClassicNotes.Service.DiaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public ResponseEntity<?> CreateDiary(@RequestBody CreateRequest createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(diaryService.CreateDiary(createRequest));
    }

    @PatchMapping
    public ResponseEntity<?> updateDiary(@RequestBody UpdateDiaryRequest updateDiaryRequest) {
        return ResponseEntity.ok(diaryService.updateDiary(updateDiaryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiary(@PathVariable int id) {
        return ResponseEntity.ok(diaryService.deleteDiary(id));
    }

    @PostMapping("/addEntries")
    public ResponseEntity<?> addEntryToDiary(@RequestBody EntriesRequest entriesRequest) {
        return ResponseEntity.ok(diaryService.addEntryToDiary(entriesRequest));
    }
}
