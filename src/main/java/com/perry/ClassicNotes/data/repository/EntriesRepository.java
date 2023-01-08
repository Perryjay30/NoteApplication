package com.perry.ClassicNotes.data.repository;


import com.perry.ClassicNotes.data.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntriesRepository extends JpaRepository<Entry, Integer> {

    Optional<Entry> findEntryByTitle(String title);
    Optional<Entry> findEntryById(int id);

}
