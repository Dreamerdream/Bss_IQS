package com.bss.iqs.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bss.iqs.entity.PlanTaskRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hgh
 * @since 2017-09-04
 */
public interface PlanTaskRecordMapper extends BaseMapper<PlanTaskRecord> {
    public List<PlanTaskRecord> getPlanTaskRecord(Pagination pagination,@Param("dataQueryGroupId") Integer dataQueryGroupId, @Param("planTaskName") String planTaskName);


    public List<PlanTaskRecord> getPlanTaskRecord2(@Param("dataQueryGroupId") Integer dataQueryGroupId, @Param("planTaskName") String planTaskName,@Param("pageStart") Integer pageStart,@Param("pageEnd") Integer pageEnd);
}