package com.analistas.bloggingplatform.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private String title;

    private String content;

    private String subtitle;

    private LocalDateTime createdAt;

}
