package com.bss.iqs.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserLoginRecordDao {

    public List<Map> getUserLoginRecord(@Param("column") String column, @Param("columnValue") String columnValue, @Param("pageStart") Integer pageStart, @Param("pageEnd") Integer pageEnd);
}
