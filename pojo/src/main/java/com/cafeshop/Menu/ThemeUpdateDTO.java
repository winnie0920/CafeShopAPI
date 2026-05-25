package com.cafeshop.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ThemeUpdateDTO {
    private Long id;
    private String name;
    private String content;
    private Integer isSale;
    private String imageUrl;
}