package com.github.m1rr0r.notes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Entity
public class Note {
    @Id
    private Long id;
    private String title;
    private String notetext;
    private String notedate;

    private static final String datePattern = "HH:mm dd.MM.yyyy";

    public Note() {}

    public Note(Long id, String title, String notetext) {
        this.id = id;
        this.title = title;
        this.notetext = notetext;
        this.notedate = new SimpleDateFormat("HH:mm dd.MM.yyyy").format(Calendar.getInstance().getTime());
    }

    @Override
    public String toString() {
        return String.format("Note[id=%s, title=%s, notetext=%s, notedate=%s",
                id, title, notetext, notedate);
    }

    public Long getId() {return this.id;}
    public String getTitle() {return this.title;}
    public String getNotetext() {return this.notetext;}
    public String getNotedate() {return this.notedate;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotetext(String notetext) {
        this.notetext = notetext;
    }

    public void setNotedate() {
        this.notedate = new SimpleDateFormat(datePattern).format(Calendar.getInstance().getTime());
    }

    public LocalDateTime castNotedateToDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return LocalDateTime.parse(this.notedate, formatter);
    }
}
