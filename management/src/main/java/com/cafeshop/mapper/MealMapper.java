package com.cafeshop.mapper;

import com.cafeshop.Menu.Meal;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface MealMapper {
    /**
     * 查詢全部餐點
     */
    List<Meal> getMeals();
    /**
     * 查詢單一餐點
     * @param id
     * @return
     */
    Meal getMealById(@Param("id") Long id);
    /**
     * 新增餐點
     * @param meal
     */
    void postMeal(Meal meal);
    /**
     * 更新餐點
     * @param meal
     */
    @Update("UPDATE meal SET theme_id = #{themeId}, name = #{name}, price = #{price}, description = #{description}, image_url = #{imageUrl}, count = #{count}, is_sale = #{isSale} WHERE id = #{id}")
    void updateMeal(Meal meal);
    /**
     * 刪除餐點
     * @param id
     */
    @Delete("DELETE FROM meal WHERE id = #{id}")
    void deleteMeal(Long id);
    /**
     * 取得餐點選擇的選項Id
     * @param mealId
     */
    List<Long> getMealOptionIds(@Param("mealId") Long mealId);
    /**
     * 建立餐點與選項的關聯
     * @param mealId
     * @param groupIds
     */
    int insertMealOptions(@Param("mealId") Long mealId, @Param("groupIds") List<Long> groupIds);
    /**
     * 刪除餐點與選項的關聯
     * @param mealId
     */
    @Delete("DELETE FROM meal_option_map WHERE meal_id = #{mealId}")
    void deleteMealOption(@Param("mealId") Long mealId);
}
