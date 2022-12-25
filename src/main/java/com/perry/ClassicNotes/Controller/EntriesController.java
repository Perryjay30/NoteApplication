package com.perry.ClassicNotes.Controller;

import com.perry.ClassicNotes.Data.dto.Request.EntriesRequest;
import com.perry.ClassicNotes.Data.dto.Request.RetrieveRequest;
import com.perry.ClassicNotes.Service.EntriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/entries")
@Slf4j
public class EntriesController {
    @Autowired
    private EntriesService entriesService;

    @PostMapping
    public ResponseEntity<?> writeInEntries(@RequestBody EntriesRequest writeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(entriesService.writeInEntries((writeRequest)));
    }

    @PatchMapping
    public ResponseEntity<?> updateEntries(@RequestBody EntriesRequest updateRequest) {
        return ResponseEntity.ok(entriesService.updateEntries(updateRequest));
    }

    @GetMapping
    public ResponseEntity<?> retrieveEntries(@RequestBody RetrieveRequest retrieveRequest) {
        return ResponseEntity.ok(entriesService.retrieveEntries(retrieveRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntries(@PathVariable int id) {
        return ResponseEntity.ok(entriesService.deleteEntries(id));
    }

}
