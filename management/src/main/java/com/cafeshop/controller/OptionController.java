package com.cafeshop.controller;

import com.cafeshop.Result.Result;
import com.cafeshop.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    /**
     * 查詢某個餐點的選項
     */
    @GetMapping
    public Result getOptionsByMealId(@RequestParam Long mealId) {

        return Result.success(
                optionService.getOptionsByMealId(mealId),
                "查詢餐點選項成功"
        );
    }
}