package com.montego.flow.controller;

import com.montego.flow.entity.User;
import com.montego.flow.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    final NoteRepository noteRepository;
    @Autowired
    public MainController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }



    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile",user);
        data.put("notes",noteRepository.findAll());

        model.addAttribute("frontendData", data);
        return "index";
    }
}
