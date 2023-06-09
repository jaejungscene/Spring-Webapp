package com.example.fastcampusmysql.domain.post.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostDto(
        Long id,
        Long memberId,
        String contents,
        LocalDate createdDate,
        LocalDateTime createdAt
) {
}
