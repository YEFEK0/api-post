package com.apipost.api_post.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // genera id numericos 32 estudiar
    private UUID id;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, columnDefinition = "TEXT", length = 500) // Forza a que el tipo de dato en PostgreSQL sea
                                                                       // TEXT y no el VARCHAR
    private String content;

    @Builder.Default
    @Column(nullable = false)
    private Integer likes = 0;

    // @CreationTimestamp //
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}