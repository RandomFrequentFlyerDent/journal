package nl.nathalie.journal.entry.event;

import nl.nathalie.journal.entry.EntryRepository;

import javax.transaction.Transactional;

@Transactional
public interface EventRepository extends EntryRepository<Event> {
}
