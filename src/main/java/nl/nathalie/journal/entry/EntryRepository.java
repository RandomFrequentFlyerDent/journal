package nl.nathalie.journal.entry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntryRepository<T extends Entry> extends CrudRepository<T, Long> {
    public T findByTitle(String title);
}
