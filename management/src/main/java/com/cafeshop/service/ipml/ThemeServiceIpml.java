package com.cafeshop.service.ipml;


import com.cafeshop.ImageUtil;
import com.cafeshop.Menu.*;
import com.cafeshop.Result.ResultCode;
import com.cafeshop.exception.BusinessException;
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
public class ThemeServiceIpml implements ThemeService {

    private final ThemeMapper menuMapper;
    private final UploadServiceImpl uploadServiceImpl;
    private static final String R2_BASE_URL =
            "https://pub-248e3a67abac41649f69b5e50260c0f5.r2.dev/";
    /**
     * 查詢主題
     */
    @Override
    public List<ThemeResponse> getThemes() {
        return menuMapper.getThemes()
                .stream()
                .peek(t -> t.setImageUrl(ImageUtil.buildUrl(t.getImageUrl())))
                .toList();
    }
    /**
     * 查詢單一主題
     */
    @Override
    public ThemeResponse getThemeById(Long id) {
        ThemeResponse theme = menuMapper.getThemeById(id);
        if (theme == null) {return null;}
        theme.setImageUrl(ImageUtil.buildUrl(theme.getImageUrl()));
        return theme;
    }
    /**
     * 新增主題
     * @param t
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void postTheme(Theme t) {

        // 直接組裝資料 (imageUrl 直接從 t 拿)
        ThemeResponse theme = new ThemeResponse();
        theme.setName(t.getName());
        theme.setContent(t.getContent());
        theme.setIsSale(t.getIsSale());
        theme.setImageUrl(t.getImageUrl());

        // 存 DB
        menuMapper.postTheme(theme);
    }
    /**
     * 更新主題
     * @param t
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void patchTheme(Theme t) {
        ThemeResponse theme = menuMapper.getThemeById(t.getId());
        if (theme == null) {
            throw new RuntimeException("Theme 不存在");
        }
        theme.setName(t.getName());
        theme.setContent(t.getContent());
        theme.setIsSale(t.getIsSale());
        // 圖片處理
        String oldImage = theme.getImageUrl();
        String newImage = t.getImageUrl();
        if (!newImage.equals(oldImage)) {
        // 使用者換圖
            if (oldImage != null) {
                uploadServiceImpl.deleteByUrl(oldImage);
            }
            theme.setImageUrl(newImage);
        }
        // 更新 DB
        menuMapper.updateTheme(theme);
    }
    /**
     * 刪除主題
     * @param id
     */
    @Override
    public void deleteTheme(Long id) {
        int rows = menuMapper.deleteTheme(id);
        if (rows == 0) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "刪除失敗或資料不存在");
        }
    }

}