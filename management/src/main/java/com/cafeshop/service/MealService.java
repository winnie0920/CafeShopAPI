package com.cafeshop.service;

import com.cafeshop.Menu.MealResponse;
import com.cafeshop.Menu.Meal;
import com.cafeshop.Menu.ThemeResponse;

import java.util.List;

public interface MealService {
    /**
     * 查詢全部餐點
     */
    List<ThemeResponse> getMeals();
    /**
     * 查詢單一餐點
     * @Param id
     */
    MealResponse getMealById(Long id);
    /**
     * 新增餐點
     * @param m
     */
    void postMeal(Meal m);
}
