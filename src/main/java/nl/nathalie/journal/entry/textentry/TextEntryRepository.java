package nl.nathalie.journal.entry.textentry;

import nl.nathalie.journal.entry.EntryRepository;

import javax.transaction.Transactional;

@Transactional
public interface TextEntryRepository extends EntryRepository<TextEntry> {
}
