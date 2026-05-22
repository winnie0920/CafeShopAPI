package com.cafeshop.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealResponse {
    private Long id;
    private Long themeId;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Integer count;
    private Long isSale;
    private List<Long> groupIds;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}