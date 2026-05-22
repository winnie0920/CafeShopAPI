package com.cafeshop.service.ipml;

import com.cafeshop.ImageUtil;
import com.cafeshop.Menu.MealResponse;
import com.cafeshop.Menu.Meal;
import com.cafeshop.Menu.ThemeResponse;
import com.cafeshop.mapper.MealMapper;
import com.cafeshop.mapper.ThemeMapper;
import com.cafeshop.service.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealServiceIpml implements MealService {

    private final MealMapper mealMapper;
    private final ThemeMapper menuMapper;
    /**
     * 查詢全部餐點
     */
    @Override
    public List<ThemeResponse> getMeals() {
        // 查出所有 theme（關鍵：補這一步，不然一定 null）
        List<ThemeResponse> themes = menuMapper.getThemes();
        // 查出所有 meal
        List<MealResponse> meals = mealMapper.getMeals();
        // 圖片處理
        for (MealResponse meal : meals) {
            meal.setImageUrl(ImageUtil.buildUrl(meal.getImageUrl()));
        }
        // 用 themeId 分組 meal
        Map<Long, List<MealResponse>> mealMap = meals.stream()
                .collect(Collectors.groupingBy(MealResponse::getThemeId));
        // 把 meal 塞回 theme
        for (ThemeResponse theme : themes) {
            List<MealResponse> children = mealMap.get(theme.getId());
            // 沒餐點也要給空 list
            theme.setChildren(children != null ? children : new ArrayList<>());
        }
        // 回傳完整 Theme list
        return themes;
    }
    /**
     * 查詢單一餐點
     * @Param id
     */
    @Override
    public MealResponse getMealById(Long id) {
        MealResponse meal = mealMapper.getMealById(id);
        if (meal == null) {return null;}
        // 圖片處理
        meal.setImageUrl(ImageUtil.buildUrl(meal.getImageUrl()));
        // 查這個 meal 有哪些 option group id
        List<Long> groupIds = mealMapper.getMealOptionIds(id);
        // 防空處理
        meal.setGroupIds(groupIds != null ? groupIds : new ArrayList<>());

        return meal;
    }

    /**
     * 新增餐點
     * @param m
     */
    @Override
    public void postMeal(Meal m) {

        MealResponse meal = new MealResponse();
        meal.setThemeId(m.getThemeId());
        meal.setName(m.getName());
        meal.setPrice(m.getPrice());
        meal.setDescription(m.getDescription());
        meal.setImageUrl(m.getImageUrl());
        meal.setCount(m.getCount());
        meal.setIsSale(m.getIsSale());
        // 存 DB
        mealMapper.postMeal(meal);
        Long mealId = meal.getId();
        if (m.getGroupIds() != null && !m.getGroupIds().isEmpty()) {
            mealMapper.insertMealOptions(mealId, m.getGroupIds());
        }
    }

}
