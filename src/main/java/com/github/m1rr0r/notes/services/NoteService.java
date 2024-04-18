package com.github.m1rr0r.notes.services;

import com.github.m1rr0r.notes.repositories.NoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Transactional
    public void deleteNotesByIds(Long[] ids) {
        noteRepository.deleteByIdIn(Arrays.stream(ids).toList());
    }
}
