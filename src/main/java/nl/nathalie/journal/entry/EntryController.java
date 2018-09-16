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
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

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

    @GetMapping("/entry/text/{id}")
    public String getTextEntryById(@PathVariable Long id, Model model) {
        TextEntry entry = textEntryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        model.addAttribute("entry", entry);
        model.addAttribute("appName", appName);
        model.addAttribute("pageTitle", entry.getTitle());

        return "forms/textentry";
    }

    @GetMapping("/entry/task/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        model.addAttribute("task", task);
        model.addAttribute("appName", appName);
        model.addAttribute("pageTitle", task.getTitle());

        return "forms/task";
    }

    @GetMapping("/entry/event/{id}")
    public String getEventById(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        model.addAttribute("event", event);
        model.addAttribute("appName", appName);
        model.addAttribute("pageTitle", event.getTitle());

        return "forms/event";
    }
}
