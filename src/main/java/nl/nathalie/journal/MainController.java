package nl.nathalie.journal;

import com.google.common.collect.Iterables;
import nl.nathalie.journal.entry.Entry;
import nl.nathalie.journal.entry.event.Event;
import nl.nathalie.journal.entry.event.EventRepository;
import nl.nathalie.journal.entry.textentry.TextEntry;
import nl.nathalie.journal.entry.textentry.TextEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    private TextEntryRepository textEntryRepository;

    @Autowired
    private EventRepository eventRepository;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/journal")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("journal/entry/add")
    public String getTextEntryForm(Model model) {
        return "forms/textentry";
    } // bootstrap not on classpath?

    @PostMapping("journal/entry/add")
    public String addTextEntry(@RequestParam String title, @RequestParam String content) {
        TextEntry entry = new TextEntry();
        entry.setTitle(title);
        entry.setContent(content);
        return "journal/entries"; // kan niet want dit is geen html maar responsebody, iets met redirect
    }

    @GetMapping(path = "/add_entry")
    public @ResponseBody String addTextEntry(@RequestParam String title) {
        TextEntry entry = new TextEntry();
        entry.setTitle(title);
        textEntryRepository.save(entry);
        return "saved";
    }

    @GetMapping(path = "journal/entries")
    public @ResponseBody Iterable<? extends Entry> getAllEntries() {
        Iterable<TextEntry> textEntries = textEntryRepository.findAll();
        Iterable<Event> calendarEntries = eventRepository.findAll();

        return Iterables.concat(textEntries, calendarEntries);
    }
}
