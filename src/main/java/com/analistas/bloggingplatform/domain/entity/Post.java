package com.analistas.bloggingplatform.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Post(String title, String content, String subtitle, Category category, List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.subtitle = subtitle;
        this.category = category;
        this.tags = tags;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.status = Boolean.TRUE;
    }
}
