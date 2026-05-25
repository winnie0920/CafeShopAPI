package com.cafeshop.service;

import com.cafeshop.Menu.*;

import java.util.List;

public interface MealService {
    /**
     * 查詢全部餐點
     */
    List<MealResponse> getMeals();
    /**
     * 查詢單一餐點
     * @param id
     * @return
     */
    MealResponse getMealById(Long id);
    /**
     * 新增餐點
     * @param dto
     * @return
     */
    void postMeal(MealCreateDTO dto);
    /**
     * 更新餐點
     * @param dto
     */
    void patchTheme(MealUpdateDTO dto);
    /**
     * 刪除餐點
     * @param id
     */
    void deleteMeal(Long id);
}