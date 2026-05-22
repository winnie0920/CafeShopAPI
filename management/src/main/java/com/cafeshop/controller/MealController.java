package com.cafeshop.controller;

import com.cafeshop.Menu.Meal;
import com.cafeshop.Result.Result;
import com.cafeshop.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth/meal")
public class MealController {
    @Autowired
    private MealService mealService ;

    /**
     * 查詢餐點
     */
    @GetMapping
    public Result getMeals(@RequestParam(required = false) Long id) {
        if (id != null) {
            return Result.success(mealService.getMealById(id), "查詢全部成功");
        }
        return Result.success(mealService.getMeals(), "查詢單一成功");
    }
    /**
     * 新增餐點
     * @param m
     */
    @PostMapping
    public Result postMeal(@RequestBody Meal m) {
        mealService.postMeal(m);
        return Result.success(null,"新增成功");
    }
}
