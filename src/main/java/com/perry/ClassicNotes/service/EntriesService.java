package com.perry.ClassicNotes.service;

import com.perry.ClassicNotes.data.models.Entry;
import com.perry.ClassicNotes.data.dto.request.EntriesRequest;
import com.perry.ClassicNotes.data.dto.request.RetrieveRequest;
import com.perry.ClassicNotes.data.dto.response.EntryRetrievalResponse;
import com.perry.ClassicNotes.data.dto.response.Response;
import com.perry.ClassicNotes.data.dto.response.WriteResponse;

import java.util.List;

public interface EntriesService {
    WriteResponse writeInEntries(EntriesRequest writeRequest);
    EntryRetrievalResponse retrieveEntries(RetrieveRequest retrieveRequest);
    Response updateEntries(EntriesRequest updateEntriesRequest);
    Response deleteEntries(int id);
    List<Entry> getAllEntries();
    Response deleteAllEntries();

}
