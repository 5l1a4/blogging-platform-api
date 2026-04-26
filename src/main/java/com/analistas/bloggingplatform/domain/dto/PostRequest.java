package com.analistas.bloggingplatform.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @Size(min = 4, max = 50)
    @NotBlank
    private String title;

    @Size(min = 4, max = 12000)
    @NotBlank
    private String content;

    @Size(min = 4, max = 50)
    private String subtitle;

}
