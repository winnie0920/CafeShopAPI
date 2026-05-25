package com.cafeshop.controller;

import com.cafeshop.Menu.Meal;
import com.cafeshop.Menu.MealCreateDTO;
import com.cafeshop.Menu.MealUpdateDTO;
import com.cafeshop.Menu.ThemeUpdateDTO;
import com.cafeshop.Result.Result;
import com.cafeshop.service.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/meal")
public class MealController {

    private final MealService mealService;
    /**
     * 查詢全部、單一餐點
     * @param id
     * @return
     */
    @GetMapping
    public Result getMeals(@RequestParam(required = false) Long id) {
        if (id != null) {
            return Result.success(mealService.getMealById(id), "查詢單一餐點成功");
        }
        return Result.success(mealService.getMeals(), "查詢全部餐點成功");
    }

    /**
     * 新增餐點
     * @param dto
     * @return
     */
    @PostMapping
    public Result postMeal(@RequestBody MealCreateDTO dto) {
        mealService.postMeal(dto);
        return Result.success(null, "新增餐點成功");
    }

    /**
     * 更新餐點
     * @param dto
     */
    @PatchMapping
    public Result patchTheme(@RequestBody MealUpdateDTO dto) {
        mealService.patchTheme(dto);
        return Result.success(null, "更新餐點成功");
    }
    /**
     * 刪除餐點
     * @param id
     */
    @DeleteMapping
    public Result deleteMeal(@RequestParam Long id){
        mealService.deleteMeal(id);
        return Result.success(null,"刪除成功");
    }
}