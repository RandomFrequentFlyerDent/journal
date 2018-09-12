package nl.nathalie.journal.entry.task;

import nl.nathalie.journal.entry.EntryRepository;

import javax.transaction.Transactional;

@Transactional
public interface TaskRepository extends EntryRepository<Task> {
}
