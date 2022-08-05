package ru.punko.spingcourse.Project2Boot.controllers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.punko.spingcourse.Project2Boot.models.Person;
import ru.punko.spingcourse.Project2Boot.models.Setting;
import ru.punko.spingcourse.Project2Boot.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private static List<Person> currentListPeople;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String send(Model model, @ModelAttribute("setting") Setting setting) {

        model.addAttribute("people", peopleService.generate(setting));
        currentListPeople = peopleService.generate(setting);
        return "people/main";
    }

    @PostMapping("/region")
    public String sendMessage(Model model, @ModelAttribute("setting") Setting setting) {
        model.addAttribute("people", peopleService.generate(setting));
        currentListPeople = peopleService.generate(setting);
        return "people/main";
    }

    @PostMapping("/export")
    public void exportCSV(HttpServletResponse response) throws Exception {
        String filename = "users.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        StatefulBeanToCsv<Person> writer = new StatefulBeanToCsvBuilder<Person>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        writer.write(currentListPeople);
    }

}
