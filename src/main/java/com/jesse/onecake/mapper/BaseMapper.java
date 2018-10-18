package com.jesse.onecake.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

public interface BaseMapper<T> extends Mapper<T>,SelectByIdsMapper<T> {
}
