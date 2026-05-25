package com.cafeshop.service.impl;
import com.cafeshop.Menu.*;
import com.cafeshop.mapper.ThemeMapper;
import com.cafeshop.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeMapper themeMapper;
    private final UploadServiceImpl uploadService;
    /**
     * 查詢全部主題
     */
    @Override
    public List<ThemeResponse> getThemesTree() {
        // 1. 傳入 null，查出所有主題以及各自底下的 children 餐點
        List<ThemeResponse> trees = themeMapper.getThemesWithMeals(null);
        return trees;
    }
    /**
     * 查詢單一主題
     * @param id
     */
    @Override
    public ThemeResponse getThemeById(Long id) {
        // 2. 傳入特定的 id，這時 SQL 會加上 WHERE t.id = #{id}
        List<ThemeResponse> results = themeMapper.getThemesWithMeals(id);

        if (results == null || results.isEmpty()) {
            throw new RuntimeException("找不到該主題");
        }

        return  results.get(0);
    }
    /**
     * 新增主題
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void postTheme(ThemeCreateDTO dto) {
        Theme theme = new Theme();
        theme.setName(dto.getName());
        theme.setContent(dto.getContent());
        theme.setIsSale(dto.getIsSale());
        theme.setImageUrl(dto.getImageUrl());

        themeMapper.postTheme(theme);
    }
    /**
     * 更新主題
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void patchTheme(ThemeUpdateDTO dto) {
        // 💡 修正點：改用專門查詢資料庫實體的 getThemeEntityById
        Theme theme = themeMapper.getThemeEntityById(dto.getId());

        // 防呆
        if (theme == null) {
            throw new RuntimeException("Theme 不存在");
        }
        theme.setName(dto.getName());
        theme.setContent(dto.getContent());
        theme.setIsSale(dto.getIsSale());

        String oldImage = theme.getImageUrl();
        String newImage = dto.getImageUrl();
        if (newImage != null && !newImage.equals(oldImage)) {
            if (oldImage != null) {
                uploadService.deleteByUrl(oldImage);
            }
            theme.setImageUrl(newImage);
        }

        // 這裡傳入的 theme 型態與 Mapper 的要求就完全一致了！
        themeMapper.updateTheme(theme);
    }
    /**
     * 刪除主題
     * @param id
     */
    @Override
    public void deleteTheme(Long id) {
        int rows = themeMapper.deleteTheme(id);
        if (rows == 0) {
            throw new RuntimeException("刪除失敗或資料不存在");
        }
    }
    /**
     * 查詢下拉式選單
     * @return ThemeDropdownDTO
     */
    @Override
    public List<ThemeDropdownDTO>  getThemesDropdown() {
        return themeMapper.getThemesDropdown();
    }
    /**
     * ThemeResponse 轉換 Theme
     * @param theme
     * @return
     */
    private ThemeResponse convertToResponse(Theme theme) {
        ThemeResponse res = new ThemeResponse();
        res.setId(theme.getId());
        res.setName(theme.getName());
        res.setContent(theme.getContent());
        res.setIsSale(theme.getIsSale());
        res.setImageUrl(theme.getImageUrl());
        res.setCreateTime(theme.getCreateTime());
        res.setUpdateTime(theme.getUpdateTime());
        return res;
    }
}