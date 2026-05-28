package com.cafeshop;
import com.cafeshop.Menu.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CovertFunction {
    public static List<OptionGroupResponse> CovertOptionGroupResponse(List<OptionRow> rows) {
        /**
         * OptionRow 轉換 OptionGroupResponse
         * @Param rows
         */
        Map<Long, OptionGroupResponse> map = new LinkedHashMap<>();

        for (OptionRow r : rows) {

            OptionGroupResponse group = map.computeIfAbsent(r.getGroupId(), id -> {
                OptionGroupResponse g = new OptionGroupResponse();
                g.setId(id);
                g.setName(r.getGroupName());
                g.setType(r.getType());
                g.setChoices(new ArrayList<>());
                return g;
            });

            OptionChoiceResponse choice = new OptionChoiceResponse();
            choice.setId(r.getChoiceId());
            choice.setName(r.getChoiceName());
            choice.setPrice(r.getPrice());

            group.getChoices().add(choice);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * ThemeResponse 轉換 Theme
     * @param theme
     * @return
     */

    public static ThemeResponse convertToResponse(Theme theme) {

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
