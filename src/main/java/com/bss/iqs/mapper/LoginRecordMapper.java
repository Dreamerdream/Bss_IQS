package com.bss.iqs.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bss.iqs.entity.LoginRecord;
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
 * @since 2017-08-29
 */
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {
    public List<LoginRecord> getUserLoginRecord(Pagination pagination,@Param("column") String column, @Param("columnValue") String columnValue);


    public List<LoginRecord> getUserLoginRecord2( @Param("column") String column, @Param("columnValue") String columnValue, @Param("pageStart") Integer pageStart, @Param("pageEnd") Integer pageEnd);
}