package com.cafeshop.Menu;

import lombok.Data;

import java.util.List;
@Data
public class MealUpdateDTO {
    private Long id;
    private Long themeId;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Integer count;
    private Long isSale;
    private List<Long> groupIds;
}