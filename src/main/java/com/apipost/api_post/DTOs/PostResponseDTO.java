package com.apipost.api_post.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDTO {

    private UUID id;
    private String author;
    private String content;
    private Integer likes;
    private LocalDateTime createdAt;

}
