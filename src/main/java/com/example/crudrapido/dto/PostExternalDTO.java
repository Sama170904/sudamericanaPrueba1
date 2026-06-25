package com.example.crudrapido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostExternalDTO {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}