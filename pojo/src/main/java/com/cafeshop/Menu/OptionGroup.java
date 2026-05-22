package com.cafeshop.Menu;

import lombok.Data;

import java.util.List;

@Data
public class OptionGroup {
    private Long id;
    private String type;
    private String name;
    private List<OptionChoice> children;
}
