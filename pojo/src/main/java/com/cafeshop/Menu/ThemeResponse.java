package com.cafeshop.Menu;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ThemeResponse {
    private Long id;
    private String name;
    private String content;
    private Integer isSale;
    private String imageUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer count;
    private List<MealResponse> children; // 樹狀結構子餐點
}