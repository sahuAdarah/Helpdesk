package com.ai.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "help_desk_tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String summary;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    
    private String category;

    @Column(length = 1000)
    private String description;
    
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    // Runs ONLY when entity is saved first time
    @PrePersist
    void onCreate() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();

        if (this.status == null) {
            this.status = Status.OPEN; // optional default
        }
    }

    // Runs ONLY when entity is updated
    @PreUpdate
    void onUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}
