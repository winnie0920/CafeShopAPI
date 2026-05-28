package com.cafeshop.controller;

import com.cafeshop.Menu.ThemeCreateDTO;
import com.cafeshop.Menu.ThemeUpdateDTO;
import com.cafeshop.Result.Result;
import com.cafeshop.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/theme")
public class ThemeController {

    private final ThemeService themeService;
    /**
     * 查詢全部、單一主題
     * @param id
     */
    @GetMapping
    public Result getThemes(@RequestParam(required = false) Long id) {
        if (id != null && id >= 0) {
            return Result.success(themeService.getThemeById(id), "查詢單一主題成功");
        }
        return Result.success(themeService.getThemesTree(), "查詢完整菜單樹成功");
    }
    /**
     * 新增主題
     * @param dto
     */
    @PostMapping
    public Result postTheme(@RequestBody ThemeCreateDTO dto) {
        themeService.postTheme(dto);
        return Result.success(null, "新增主題成功");
    }
    /**
     * 更新主題
     * @param dto
     */
    @PatchMapping
    public Result patchTheme(@RequestBody ThemeUpdateDTO dto) {
        themeService.patchTheme(dto);
        return Result.success(null, "更新主題成功");
    }
    /**
     * 刪除主題
     * @param id
     */
    @DeleteMapping
    public Result deleteTheme(@RequestParam Long id) {
        themeService.deleteTheme(id);
        return Result.success(null, "刪除主題成功");
    }
    /**
     * 查詢下拉式選單
     * @return ThemeDropdownDTO
     */
    @GetMapping("/dropdown")
    public Result getThemesDropdown() {
        return Result.success( themeService.getThemesDropdown(),"查詢下拉選單成功");
    }
}
