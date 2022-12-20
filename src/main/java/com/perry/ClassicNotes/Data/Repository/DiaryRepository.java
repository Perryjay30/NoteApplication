package com.perry.ClassicNotes.Data.Repository;


import com.perry.ClassicNotes.Data.Models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

}
