package com.montego.flow.controller;

import com.montego.flow.entity.Note;
import com.montego.flow.exceptions.NotFoundException;
import com.montego.flow.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("note")
public class NoteController {

    private final NoteRepository noteRepository;
    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public List<Note> list() {
        return noteRepository.findAll();
    }

    @GetMapping("{id}")
    public Note getOne(@PathVariable("id") Note note) {
        return note;
    }

    @PostMapping()
    public Note create(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("{id}")
    public Note update(
                @PathVariable("id") Note noteFromDB,
                @RequestBody Note note) {
        BeanUtils.copyProperties(note, noteFromDB,"id");
        return noteRepository.save(note);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Note note){
        noteRepository.delete(note);
    }

}
