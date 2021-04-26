package com.example.restservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected int id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Please enter your name")
    @Size(max = 250)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank(message = "Please enter your email")
    @Size(max = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    @NotBlank(message = "Please enter your phone number")
    @Pattern(regexp="/(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]\u200C\u200B)\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)([2-9]1[02-9]\u200C\u200B|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})\\s*(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+)\\s*)?$/i", message = "Phone number must be in +79********* format")
    private String phone_number;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_status", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "status"}, name = "user_roles_unique_idx")})
    @Column(name = "status")
    private Status status;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @Nullable
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered;


    public User() {
        this.registered = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phoneNumber) {
        this.phone_number = phoneNumber;
    }
}
