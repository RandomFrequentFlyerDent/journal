package nl.nathalie.journal.entry;

import nl.nathalie.journal.entry.event.EventRepository;
import nl.nathalie.journal.entry.task.TaskRepository;
import nl.nathalie.journal.entry.textentry.TextEntryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
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
    public List<Entry> all() {
        List<Entry> entries = new ArrayList<>();
        entries.addAll(textEntryRepository.findAll());
        entries.addAll(eventRepository.findAll());
        entries.addAll(taskRepository.findAll());

        return entries;
    }

    @GetMapping("/entry/{type}/{id}")
    Entry one(@PathVariable Entry.EntryType type, @PathVariable Long id) throws Exception {
        if (type == Entry.EntryType.EVENT) {
            return eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        }
        if (type == Entry.EntryType.TASK) {
            return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        }
        if (type == Entry.EntryType.TEXT_ENTRY) {
            return textEntryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        }

        throw new EntityNotFoundException();
    }
}
