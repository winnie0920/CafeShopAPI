package com.cafeshop.service.impl;

import com.cafeshop.Menu.*;
import com.cafeshop.mapper.MealMapper;
import com.cafeshop.service.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealMapper mealMapper;
    private final OptionServiceImpl optionService;
    /**
     * 查詢全部餐點
     */
    @Override
    public List<MealResponse> getMeals() {

        List<Meal> meals = mealMapper.getMeals();
        List<MealResponse> result = new ArrayList<>();

        for (Meal meal : meals) {
            MealResponse res = convertToResponse(meal);
            List<OptionGroupResponse> options =
                    optionService.getOptionsByMealId(meal.getId());
            res.setOptions(options);
            result.add(res);
        }

        return result;
    }

    /**
     * 查詢單一餐點
     * @param id
     * @return
     */
    @Override
    public MealResponse getMealById(Long id) {
        Meal meal = mealMapper.getMealById(id);
        if (meal == null) return null;

        MealResponse res = convertToResponse(meal);
        List<OptionGroupResponse> options =
                optionService.getOptionsByMealId(meal.getId());
        res.setOptions(options);
        return res;
    }
    /**
     * 新增餐點
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void postMeal(MealCreateDTO dto) {
        // DTO 轉成與 DB 一致的 Meal 實體
        Meal meal = new Meal();
        meal.setThemeId(dto.getThemeId());
        meal.setName(dto.getName());
        meal.setPrice(dto.getPrice());
        meal.setDescription(dto.getDescription());
        meal.setImageUrl(dto.getImageUrl());
        meal.setCount(dto.getCount());
        meal.setIsSale(dto.getIsSale());

        // 存入 DB
        mealMapper.postMeal(meal);
        Long mealId = meal.getId();



        // 建立關聯
        if (dto.getGroupIds() != null && !dto.getGroupIds().isEmpty()) {
            mealMapper.insertMealOptions(mealId, dto.getGroupIds());
        }
    }
    /**
     * 更新餐點
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void patchTheme(MealUpdateDTO dto) {
        // DTO 轉成與 DB 一致的 Meal 實體
        Meal meal = new Meal();
        meal.setId(dto.getId());
        meal.setThemeId(dto.getThemeId());
        meal.setName(dto.getName());
        meal.setPrice(dto.getPrice());
        meal.setDescription(dto.getDescription());
        meal.setImageUrl(dto.getImageUrl());
        meal.setCount(dto.getCount());
        meal.setIsSale(dto.getIsSale());

        mealMapper.updateMeal(meal);

        Long mealId = meal.getId();
        // 刪掉舊關聯（很重要）
        mealMapper.deleteMealOption(mealId);
        // 建立關聯
        if (dto.getGroupIds() != null && !dto.getGroupIds().isEmpty()) {
            mealMapper.insertMealOptions(mealId, dto.getGroupIds());
        }
    }
    /**
     * 刪除餐點
     * @param id
     */
    @Override
    public void deleteMeal(Long id) {
        // 刪掉舊關聯
        mealMapper.deleteMealOption(id);
        // 再刪主表
        mealMapper.deleteMeal(id);
    }
    /**
     * MealResponse 轉換 Meal
     * @param meal
     * @return
     */
    private MealResponse convertToResponse(Meal meal) {
        MealResponse res = new MealResponse();
        res.setId(meal.getId());
        res.setThemeId(meal.getThemeId());
        res.setName(meal.getName());
        res.setPrice(meal.getPrice());
        res.setDescription(meal.getDescription());
        res.setImageUrl(meal.getImageUrl()); // 統一封裝圖片組裝
        res.setCount(meal.getCount());
        res.setIsSale(meal.getIsSale());
        res.setCreateTime(meal.getCreateTime());
        res.setUpdateTime(meal.getUpdateTime());
        return res;
    }
}