package com.analistas.bloggingplatform.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String text;

    private String author;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Comment(String text, String author) {
        this.text = text;
        this.author = author;
    }

    @PrePersist
    protected void onCreated(){
        this.createdAt = LocalDateTime.now();
    }
}
