package com.analistas.bloggingplatform.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String text;

    private String author;

    private LocalDateTime createdAt;

    private Long postId;
}
