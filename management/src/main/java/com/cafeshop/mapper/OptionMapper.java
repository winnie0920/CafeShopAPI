package com.cafeshop.mapper;

import com.cafeshop.Menu.OptionRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionMapper {

    List<OptionRow> getOptionsByMealId(@Param("mealId") Long mealId);
}