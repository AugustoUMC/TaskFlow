package com.taskflow.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String priority;

    private LocalDate dueDate;

    @Column(name = "google_event_id")
    private String googleEventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}