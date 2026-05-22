package com.cafeshop.mapper;

import com.cafeshop.Menu.MealResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MealMapper {
    /**
     * 查詢全部餐點
     */
    List<MealResponse> getMeals();
    /**
     * 查詢單一餐點
     * @Param id
     */
    MealResponse getMealById(@Param("id") Long id);

    List<Long> getMealOptionIds(@Param("mealId") Long mealId);
    /**
     * 新增餐點
     * @param meal
     */
    void postMeal(MealResponse meal);
    /**
     * 新增餐點與選項關聯
     * @param mealId
     * @param groupIds
     */
    int insertMealOptions(@Param("mealId") Long mealId,
                          @Param("groupIds") List<Long> groupIds);
}
