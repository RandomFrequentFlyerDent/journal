package nl.nathalie.journal.entry;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "systemid", updatable = false)
    private Integer id;

    @Column
    @CreationTimestamp
    private LocalDateTime insertDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    public Entry(String title) {
        this.title = title;
    }

    public Entry() {
    }
}
