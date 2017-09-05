package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.PageBean;
import com.bss.iqs.entity.PlanTaskRecord;
import com.bss.iqs.mapper.PlanTaskRecordDao;
import com.bss.iqs.mapper.PlanTaskRecordMapper;
import com.bss.iqs.service.IPlanTaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-09-04
 */
@Service
public class PlanTaskRecordServiceImpl extends ServiceImpl<PlanTaskRecordMapper, PlanTaskRecord> implements IPlanTaskRecordService {

    @Autowired
    private PlanTaskRecordMapper planTaskRecordMapper;
    @Override
    public List<PlanTaskRecord> getPlanTaskRecord(Integer dataQueryGroupId, String keyword, Integer pageNum, Integer pageSize) {
        int pageStart = 0;
        if (pageNum != null && pageSize != null ){
            pageStart = (pageNum - 1) * pageSize;
        }
        List<PlanTaskRecord> planTaskRecords = planTaskRecordMapper.getPlanTaskRecord(new Pagination(pageStart,pageSize),dataQueryGroupId, keyword);
        if (planTaskRecords != null && planTaskRecords.size() != 0){
            return planTaskRecords;
        }

        //自己分页
//        int pageStart2 = 0;
//        int pageEnd2 = 0;
//        if (pageNum != null && pageSize != null ){
//            pageStart2 = (pageNum - 1) * pageSize;
//            pageEnd2 = pageStart2 + pageSize;
//        }
//        Wrapper<PlanTaskRecord> planTaskRecordWrapper = new EntityWrapper<>();
//        planTaskRecordWrapper.eq("dataQueryGroupId",dataQueryGroupId).like("dataQueryGroupName",keyword);
//        List<PlanTaskRecord> planTaskRecords1 = planTaskRecordMapper.selectList(planTaskRecordWrapper);
//        List<PlanTaskRecord> planTaskRecord2 = planTaskRecordMapper.getPlanTaskRecord2(dataQueryGroupId, keyword, pageStart2, pageEnd2);
//        PageBean pageBean = new PageBean(pageStart2,pageSize,planTaskRecord2,planTaskRecords1.size(),"");
        return null;
    }
}
