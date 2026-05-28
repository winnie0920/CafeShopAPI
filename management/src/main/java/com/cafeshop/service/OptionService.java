package com.cafeshop.service;

import com.cafeshop.Menu.OptionGroupResponse;

import java.util.List;

public interface OptionService {
    List<OptionGroupResponse> getOptionsByMealId(Long mealId);
}
