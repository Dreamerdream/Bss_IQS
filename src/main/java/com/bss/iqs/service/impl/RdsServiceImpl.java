package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.Rds;
import com.bss.iqs.mapper.RdsMapper;
import com.bss.iqs.service.IRdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
@Service
public class RdsServiceImpl extends ServiceImpl<RdsMapper, Rds> implements IRdsService {
    @Autowired
    private RdsMapper rdsMapper;

    @Transactional
    @Override
    public ResultBean saveRDS(Rds rds) {
        Date date = new Date();
        rds.setCreateTime(date);
        rds.setUpdateTime(date);
        Integer insert = rdsMapper.insert(rds);
        if (insert != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("添加成功");
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean deleteRDS(Integer id) {
        Integer integer = rdsMapper.deleteById(id);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("删除成功");
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ResultBean updateRDS(Rds rds) {
        rds.setUpdateTime(new Date());
        Integer integer = rdsMapper.updateById(rds);
        if (integer != null){
            ResultBean result = new ResultBean();
            result.setErrorCode(0);
            result.setErrorReason("更新成功");
            return result;
        }
        return null;
    }

    @Override
    public Rds getRDS(Integer id) {
        return rdsMapper.selectById(id);
    }

    @Override
    public List<Rds> queryAllRDS() {
        Wrapper<Rds> rdsWrapper = new EntityWrapper<>();
        List<Rds> rds = rdsMapper.selectList(rdsWrapper);
        if (rds != null && rds.size() != 0){
            return rds;
        }
        return null;
    }
}
