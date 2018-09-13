package nl.nathalie.journal.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntryRepository<T extends Entry> extends JpaRepository<T, Long> {
}
