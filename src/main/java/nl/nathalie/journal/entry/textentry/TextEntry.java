package nl.nathalie.journal.entry.textentry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.nathalie.journal.entry.Entry;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TextEntry extends Entry {

    private String content;

    public TextEntry(String title, String content) {
        super(title);
        this.content = content;
    }

    public TextEntry() {
        super();
    }
}
