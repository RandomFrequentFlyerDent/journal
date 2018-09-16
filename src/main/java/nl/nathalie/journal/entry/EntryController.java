package nl.nathalie.journal.entry;

import nl.nathalie.journal.entry.event.Event;
import nl.nathalie.journal.entry.event.EventRepository;
import nl.nathalie.journal.entry.task.Task;
import nl.nathalie.journal.entry.task.TaskRepository;
import nl.nathalie.journal.entry.textentry.TextEntry;
import nl.nathalie.journal.entry.textentry.TextEntryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController {

    private TextEntryRepository textEntryRepository;

    private EventRepository eventRepository;

    private TaskRepository taskRepository;

    @Value("${spring.application.name}")
    String appName;

    public EntryController(TextEntryRepository textEntryRepository, EventRepository eventRepository,
                           TaskRepository taskRepository) {
        this.textEntryRepository = textEntryRepository;
        this.eventRepository = eventRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/entries")
    public String all(Model model) {
        Iterable<TextEntry> entries = textEntryRepository.findAll();
        Iterable<Event> events = eventRepository.findAll();
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("entries", entries);
        model.addAttribute("events", events);
        model.addAttribute("tasks", tasks);
        model.addAttribute("appName", appName);
        model.addAttribute("pageTitle", appName);

        return "entries";
    }
}
