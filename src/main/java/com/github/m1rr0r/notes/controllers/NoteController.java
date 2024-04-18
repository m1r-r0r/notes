package com.github.m1rr0r.notes.controllers;

import com.github.m1rr0r.notes.models.Note;
import com.github.m1rr0r.notes.repositories.NoteRepository;
import com.github.m1rr0r.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private final NoteRepository repository;

    @Autowired
    private NoteService noteService;

    NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/note-list")
    public String manageNotes(Model model,
                               @RequestParam(value = "note-id", required = false) Long[] ids) {
        if(ids != null) {
            noteService.deleteNotesByIds(ids);
        }
        else {
            List<Note> noteList = repository.findAll()
                    .stream().sorted(Comparator.comparing(Note::castNotedateToDate).reversed()).toList();
            model.addAttribute("noteList", noteList);
            return "note-list";
        }
        return "redirect:/note-list";
    }

    @GetMapping("/add-note")
    public String addNote() {
        return "add-note";
    }

    @PostMapping("/add-note")
    public String addNoteSave(Model model,
                              @RequestParam String note_title,
                              @RequestParam String note_text) {
        Long maxId = repository.getMaxId();
        repository.save(new Note(maxId + 1, note_title, note_text));
        return "redirect:/note-list";
    }

    @GetMapping("/edit/{id}")
    public String editNote(Model model,
                           @PathVariable(value = "id") Long id) {
        if(!repository.existsById(id)) return "redirect:/note-list";
        Note note = repository.findById(id).orElse(null);
        model.addAttribute("note", note);
        return "edit-note";
    }

    @PostMapping("/edit/{id}")
    public String updateNote(Model model,
                             @PathVariable(value = "id") Long id,
                             @RequestParam String note_title,
                             @RequestParam String note_text) {
        if(!repository.existsById(id)) return "redirect:/note-list";
        else {
            Note note = repository.findById(id).orElse(null);
            note.setTitle(note_title);
            note.setNotetext(note_text);
            note.setNotedate();
            repository.save(note);
        }
        return "redirect:/note-list";
    }



}
