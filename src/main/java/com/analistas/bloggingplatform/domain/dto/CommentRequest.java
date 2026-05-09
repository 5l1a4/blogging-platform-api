package com.analistas.bloggingplatform.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @Size(min = 1, max = 100)
    @NotBlank
    private String text;

    @Size(min = 1, max = 100)
    @NotBlank
    private String author;

}
