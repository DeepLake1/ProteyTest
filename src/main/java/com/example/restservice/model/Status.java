package com.example.restservice.model;


import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "user_status")
public class Status {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "user_id", updatable = false, nullable = false)
    protected int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", nullable = false)
    private StatusType statusType;

    @Column(name = "status_last_time_changed", nullable = false, columnDefinition = "timestamp default now()")
    @Nullable
    private LocalDateTime lastTimeStatusChanged;

    public Status() {
    }

    public Status(StatusType statusType, LocalDateTime lastTimeStatusChanged) {
        this.statusType = statusType;
        this.lastTimeStatusChanged = lastTimeStatusChanged;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public LocalDateTime getLastTimeStatusChanged() {
        return lastTimeStatusChanged;
    }

    public void setLastTimeStatusChanged(LocalDateTime lastTimeStatusChanged) {
        this.lastTimeStatusChanged = lastTimeStatusChanged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
