package nl.nathalie.journal.entry.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.nathalie.journal.entry.Entry;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Task extends Entry {

    private String description;

    private LocalDateTime dueDate;

    public Task(String title, String description, LocalDateTime dueDate) {
        super(title);
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task() {
        super();
    }
}
