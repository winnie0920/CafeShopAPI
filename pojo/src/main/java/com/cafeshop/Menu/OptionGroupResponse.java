package com.cafeshop.Menu;

import lombok.Data;

import java.util.List;

@Data
public class OptionGroupResponse {
    private Long id;
    private String name;
    private String type;
    private List<OptionChoiceResponse> choices;
}