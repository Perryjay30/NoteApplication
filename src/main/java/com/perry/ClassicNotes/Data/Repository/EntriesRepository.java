package com.perry.ClassicNotes.Data.Repository;


import com.perry.ClassicNotes.Data.Models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntriesRepository extends JpaRepository<Entry, Integer> {

    Optional<Entry> findEntryByTitle(String title);
    Optional<Entry> findEntryById(int id);

}
