package com.analistas.bloggingplatform.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String subtitle;

    private Boolean status;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Post(String title, String content, String subtitle, Category category) {
        this.title = title;
        this.content = content;
        this.subtitle = subtitle;
        this.category = category;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.status = Boolean.TRUE;
    }
}
