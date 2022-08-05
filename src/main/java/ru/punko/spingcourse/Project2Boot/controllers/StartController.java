package ru.punko.spingcourse.Project2Boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping("/")
    public String start() {
        return "redirect:/people";
    }


}
