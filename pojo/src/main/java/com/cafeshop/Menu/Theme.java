package com.cafeshop.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    private Long id;
    private String name;
    private String content;
    private Integer isSale;
    private String imageUrl;
}
