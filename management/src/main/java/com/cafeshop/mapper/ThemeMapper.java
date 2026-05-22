package com.cafeshop.mapper;


import com.cafeshop.Menu.ThemeResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ThemeMapper {
    /**
     * 查詢主題
     */
    @Select("SELECT id, name, content, is_sale AS isSale,image_url AS imageUrl,create_time,update_time FROM theme")
    List<ThemeResponse> getThemes();
    /**
     * 查詢單一主題
     */
    @Select("SELECT id, name, content, is_sale AS isSale, image_url AS imageUrl,create_time,update_time FROM theme WHERE id = #{id}")
    ThemeResponse getThemeById(Long id);
    /**
     * 新增主題
     */
    void postTheme(ThemeResponse theme);
    /**
     * 更新主題
     * @param
     */
    @Update("UPDATE theme SET name = #{name}, content = #{content}, is_sale = #{isSale}, image_url = #{imageUrl} WHERE id = #{id}")
    void updateTheme(ThemeResponse theme);
    /**
     * 刪除主題
     * @param id
     */
    @Delete("DELETE FROM theme WHERE id = #{id}")
    int deleteTheme(@Param("id") Long id);
}
