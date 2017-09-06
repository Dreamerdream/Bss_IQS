package com.bss.iqs.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlanTaskRecordDao {



    public List<Map> getPlanTaskRecord2(@Param("dataQueryGroupId") Integer dataQueryGroupId, @Param("planTaskName") String planTaskName, @Param("pageStart") Integer pageStart, @Param("pageEnd") Integer pageEnd);
}
