package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "user_status")
public class Status extends AbstractBaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", nullable = false)
    private StatusType statusType;

    @Column(name = "status_last_time_changed", nullable = false, columnDefinition = "timestamp default now()")
    @Nullable
    private Date lastTimeStatusChanged;



    public Status() {
    }

    public Status(StatusType statusType, Date lastTimeStatusChanged) {
        this.statusType = statusType;
        this.lastTimeStatusChanged = lastTimeStatusChanged;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public Date getLastTimeStatusChanged() {
        return lastTimeStatusChanged;
    }

    public void setLastTimeStatusChanged(Date lastTimeStatusChanged) {
        this.lastTimeStatusChanged = lastTimeStatusChanged;
    }

    public User getUser() {
        return user;
    }

}
