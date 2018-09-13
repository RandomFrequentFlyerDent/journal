package nl.nathalie.journal.entry;

import lombok.extern.slf4j.Slf4j;
import nl.nathalie.journal.entry.textentry.TextEntry;
import nl.nathalie.journal.entry.textentry.TextEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TextEntryRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(new TextEntry("Bilbo Baggins", "burglar")));
            logger.info("Preloading " + repository.save(new TextEntry("Frodo Baggins", "thief")));
        };
    }
}
