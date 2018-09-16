package nl.nathalie.journal.entry.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.nathalie.journal.entry.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Event extends Entry {

    private String description;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    private String location;

    public Event(String title, String description, LocalDateTime startTime, LocalDateTime endTime, String location) {
        super(title);
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public Event(String title) {
        super(title);
    }

    public Event() {
        super();
    }
}
