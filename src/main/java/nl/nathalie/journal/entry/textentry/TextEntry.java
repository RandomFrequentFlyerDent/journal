package nl.nathalie.journal.entry.textentry;

import nl.nathalie.journal.entry.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TextEntry extends Entry {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
