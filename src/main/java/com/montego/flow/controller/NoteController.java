package com.montego.flow.controller;

import com.montego.flow.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("note")
public class NoteController {
    private int counter = 4;

    private List<Map<String, String>> notes = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("tag","movie");
            put("text", "First message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("tag","movie");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("tag","anime");
            put("text", "Third message");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return notes;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getNote(id);
    }

    private Map<String, String> getNote(@PathVariable String id) {
        return notes.stream().filter(note -> note.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Map<String, String> note) {
        note.put("id", String.valueOf(counter++));
        notes.add(note);
        return note;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> note) {
        Map<String, String> noteFromDB = getNote(id);
        noteFromDB.putAll(note);
        noteFromDB.put("id",id);
        return noteFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> note = getNote(id);
        notes.remove(note);
    }

}
