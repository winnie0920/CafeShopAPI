package com.cafeshop.service.impl;

import com.cafeshop.Menu.OptionGroupResponse;
import com.cafeshop.Menu.OptionRow;
import com.cafeshop.CovertFunction;
import com.cafeshop.mapper.OptionMapper;
import com.cafeshop.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionMapper optionMapper;

    @Override
    public List<OptionGroupResponse> getOptionsByMealId(Long mealId) {

        List<OptionRow> rows = optionMapper.getOptionsByMealId(mealId);

        return CovertFunction.CovertOptionGroupResponse(rows);
    }
}