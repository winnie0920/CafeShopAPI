package com.cafeshop.mapper;
import com.cafeshop.Menu.Theme;
import com.cafeshop.Menu.ThemeDropdownDTO;
import com.cafeshop.Menu.ThemeResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ThemeMapper {
    /**
     * 查詢全部、單一主題（含餐點 children）
     * @param id
     */
    List<ThemeResponse> getThemesWithMeals(@Param("id") Long id);
    /**
     * 新增主題
     * @param theme
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void postTheme(Theme theme);
    /**
     * 單一主題（不含餐點 children），專門給後台修改（Patch）前檢查用的
     * @param id
     */
    @Select("SELECT id, name, content, is_sale AS isSale, image_url AS imageUrl, create_time AS createTime, update_time AS updateTime FROM theme WHERE id = #{id}")
    Theme getThemeEntityById(Long id);
    /**
     * 更新主題
     * @param theme
     */
    @Update("UPDATE theme SET name = #{name}, content = #{content}, is_sale = #{isSale}, image_url = #{imageUrl} WHERE id = #{id}")
    void updateTheme(Theme theme);
    /**
     * 刪除主題
     * @param id
     */
    @Delete("DELETE FROM theme WHERE id = #{id}")
    int deleteTheme(@Param("id") Long id);
    /**
     * 查詢下拉式選單
     * @return ThemeDropdownDTO
     */
    @Select("SELECT id,name FROM theme")
    List<ThemeDropdownDTO>  getThemesDropdown();
}