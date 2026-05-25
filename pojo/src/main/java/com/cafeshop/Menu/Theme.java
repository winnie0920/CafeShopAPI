package com.cafeshop.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    private Long id;
    private String name;
    private String content;
    private Integer isSale;
    private String imageUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}