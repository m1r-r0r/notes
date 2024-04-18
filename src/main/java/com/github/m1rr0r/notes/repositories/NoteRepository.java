package com.github.m1rr0r.notes.repositories;

import com.github.m1rr0r.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findById(long id);

    @Query(value = "SELECT coalesce(max(id),0) FROM Note")
    Long getMaxId();

    void deleteByIdIn(List<Long> ids);
}
