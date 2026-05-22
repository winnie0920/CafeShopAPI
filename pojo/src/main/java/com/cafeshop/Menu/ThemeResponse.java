package com.cafeshop.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private String content;
    private Integer isSale;
    private LocalDateTime createTime; // 建立時間
    private LocalDateTime updateTime; // 更新時間
    private List<MealResponse> children;
}