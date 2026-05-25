package com.cafeshop.service;

import com.cafeshop.Menu.ThemeCreateDTO;
import com.cafeshop.Menu.ThemeDropdownDTO;
import com.cafeshop.Menu.ThemeResponse;
import com.cafeshop.Menu.ThemeUpdateDTO;

import java.util.List;

public interface ThemeService {
    /**
     * 查詢全部主題
     */
    List<ThemeResponse> getThemesTree();
    /**
     * 查詢單一主題
     * @param id
     */
    ThemeResponse getThemeById(Long id);
    /**
     * 新增主題
     * @param dto
     */
    void postTheme(ThemeCreateDTO dto);
    /**
     * 更新主題
     * @param dto
     */
    void patchTheme(ThemeUpdateDTO dto);
    /**
     * 刪除主題
     * @param id
     */
    void deleteTheme(Long id);
    /**
     * 查詢下拉式選單
     * @return ThemeDropdownDTO
     */
    List<ThemeDropdownDTO>  getThemesDropdown();
}