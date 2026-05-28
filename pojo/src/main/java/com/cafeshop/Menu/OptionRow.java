package com.cafeshop.Menu;

import lombok.Data;

@Data
public class OptionRow {
    private Long groupId;
    private String groupName;
    private String type;

    private Long choiceId;
    private String choiceName;
    private Integer price;
}