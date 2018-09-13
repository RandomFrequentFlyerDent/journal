package nl.nathalie.journal.entry;

import nl.nathalie.journal.entry.event.Event;
import nl.nathalie.journal.entry.event.EventRepository;
import nl.nathalie.journal.entry.textentry.TextEntry;
import nl.nathalie.journal.entry.textentry.TextEntryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EntryController {

    private TextEntryRepository textEntryRepository;

    private EventRepository eventRepository;

    @Value("${spring.application.name}")
    String appName;

    public EntryController(TextEntryRepository textEntryRepository, EventRepository eventRepository) {
        this.textEntryRepository = textEntryRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/entries")
    public List<Entry> all() {
        List<Entry> entries = new ArrayList<>();
        entries.addAll(textEntryRepository.findAll());
        entries.addAll(eventRepository.findAll());

        return entries;
    }

    @PostMapping("/entries")
    Entry newEntry(@RequestBody Entry entry) {
        if (entry instanceof TextEntry) {
            TextEntry textEntry = (TextEntry) entry;
            return textEntryRepository.save(textEntry);
        }
        if (entry instanceof Event) {
            Event event = (Event) entry;
            return eventRepository.save(event);
        }

        return null;
    }

//    @GetMapping("/entry/{id}")
//    Entry one(@PathVariable Long id) {
//
//        return repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//    }
}
