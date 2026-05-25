package com.cafeshop.Menu;

import lombok.Data;

@Data
public class ThemeCreateDTO {
    private String name;
    private String content;
    private Integer isSale;
    private String imageUrl;
}