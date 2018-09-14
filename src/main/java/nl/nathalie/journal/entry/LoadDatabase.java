package nl.nathalie.journal.entry;

import lombok.extern.slf4j.Slf4j;
import nl.nathalie.journal.entry.event.Event;
import nl.nathalie.journal.entry.event.EventRepository;
import nl.nathalie.journal.entry.task.Task;
import nl.nathalie.journal.entry.task.TaskRepository;
import nl.nathalie.journal.entry.textentry.TextEntry;
import nl.nathalie.journal.entry.textentry.TextEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class LoadDatabase {

    Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TextEntryRepository repository, TaskRepository taskRepository,
                                   EventRepository eventRepository) {
        return args -> {
            logger.info("Preloading " + repository.save(new TextEntry("Bilbo Baggins", "burglar")));
            logger.info("Preloading " + repository.save(new TextEntry("Frodo Baggins", "thief")));
            logger.info("Preloading " + taskRepository.save(
                    new Task("Go to Mordor",
                            "we need to do something with a ring",
                            LocalDateTime.of(2018, 12, 02, 12, 00, 00))));
            logger.info("Preloading " + eventRepository.save(
                    new Event("Killing of Voldemort",
                            "Bastard needs to die",
                            LocalDateTime.of(2018, 11, 15, 13, 45, 00),
                            LocalDateTime.of(2018, 11, 15, 15, 00,00),
                            "The Shire")));
        };
    }
}
