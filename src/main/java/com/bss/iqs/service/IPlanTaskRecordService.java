package com.bss.iqs.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.entity.PlanTaskRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-09-04
 */
public interface IPlanTaskRecordService extends IService<PlanTaskRecord> {


    List<PlanTaskRecord> getPlanTaskRecord(Integer dataQueryGroupId, String keyword, Integer pageNum, Integer pageSize);
}
