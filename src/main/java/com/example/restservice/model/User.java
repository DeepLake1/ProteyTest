package com.example.restservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    public static final int START_SEQ = 100000;

    @Id
    @JsonIgnore
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
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", message = "Not valid phone number. Please enter phone number in +79********* format")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JsonIgnore
    private Status status;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @Nullable
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime registered;


    public User() {
        this.status = new Status(StatusType.ONLINE, LocalDateTime.now());
        this.registered = LocalDateTime.now();
    }

    public User(String name, String email, String phoneNumber, LocalDateTime registered, Status status) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registered = registered;
        this.status = status;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
