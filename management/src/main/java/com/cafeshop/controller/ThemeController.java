package com.cafeshop.controller;

import com.cafeshop.Menu.Theme;
import com.cafeshop.Result.Result;
import com.cafeshop.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth/theme")
public class ThemeController {

    @Autowired
    private ThemeService menuService ;
    /**
     * 查詢主題、查詢單一主題
     * @param id
     */
    @GetMapping
    public Result getThemes(@RequestParam(required = false) Long id) {
        if (id != null) {
            return Result.success(menuService.getThemeById(id),"查詢全部成功");
        }
        return Result.success(menuService.getThemes(),"查詢單一成功");
    }

    /**
     * 新增主題
     * @param t
     */
    @PostMapping
    public Result postTheme(@RequestBody Theme t) {
        menuService.postTheme(t);
        return Result.success(null,"新增成功");
    }
    /**
     * 更新主題
     * @param t
     */
    @PatchMapping
    public Result patchTheme(@RequestBody Theme t) {
        menuService.patchTheme(t);
        return Result.success(null,"更新成功");
    }
    /**
     * 刪除主題
     * @param id
     */
    @DeleteMapping
    public Result deleteTheme(@RequestParam Long id) {
        menuService.deleteTheme(id);
        return Result.success(null, "刪除成功");
    }

}
