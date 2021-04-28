package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user_status")
public class Status {
    public static final int START_SEQ = 100000;

    @Id
    protected int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

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

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
