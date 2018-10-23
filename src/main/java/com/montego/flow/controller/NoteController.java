package com.montego.flow.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.montego.flow.entity.Note;
import com.montego.flow.entity.Views;
import com.montego.flow.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("note")
public class NoteController {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    @JsonView(Views.IdTextTag.class)
    public List<Note> list() {
        return noteRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Note getOne(@PathVariable("id") Note note) {
        return note;
    }

    @PostMapping()
    public Note create(@RequestBody Note note) {
        note.setCreationDate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @PutMapping("{id}")
    public Note update(
            @PathVariable("id") Note noteFromDB,
            @RequestBody Note note) {
        BeanUtils.copyProperties(note, noteFromDB, "id");
        return noteRepository.save(noteFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Note note) {
        noteRepository.delete(note);
    }

}
