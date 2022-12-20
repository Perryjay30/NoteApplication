package com.perry.ClassicNotes.Service;

import com.perry.ClassicNotes.Data.Models.Entry;
import com.perry.ClassicNotes.Data.dto.Request.EntriesRequest;
import com.perry.ClassicNotes.Data.dto.Request.RetrieveRequest;
import com.perry.ClassicNotes.Data.dto.Response.EntryRetrievalResponse;
import com.perry.ClassicNotes.Data.dto.Response.Response;
import com.perry.ClassicNotes.Data.dto.Response.WriteResponse;

import java.util.List;

public interface EntriesService {
    WriteResponse writeInEntries(EntriesRequest writeRequest);
    EntryRetrievalResponse retrieveEntries(RetrieveRequest retrieveRequest);
    Response updateEntries(EntriesRequest updateEntriesRequest);
    Response deleteEntries(int id);
    List<Entry> getAllEntries();
    Response deleteAllEntries();

}
