package com.cafeshop.service;

import com.cafeshop.Menu.ThemeResponse;
import com.cafeshop.Menu.Theme;

import java.util.List;

public interface ThemeService {
    /**
     * 查詢主題
     */
        List<ThemeResponse> getThemes();
    /**
     * 查詢單一主題
     */
    ThemeResponse getThemeById(Long id);
    /**
     * 新增主題
     * @param t
     */
    void postTheme(Theme t);
    /**
     * 更新主題
     * @param t
     */
    void patchTheme(Theme t);
    /**
     * 刪除主題
     * @param id
     */
    void deleteTheme(Long id);

}
