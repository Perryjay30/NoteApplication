package com.perry.ClassicNotes.data.repository;


import com.perry.ClassicNotes.data.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

}
