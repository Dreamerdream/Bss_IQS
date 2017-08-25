package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.entity.Rds;
import com.bss.iqs.mapper.RdsMapper;
import com.bss.iqs.service.IRdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void saveRDS(Rds rds) {
        rdsMapper.insert(rds);
    }

    @Override
    public void deleteRDS(Integer id) {
        rdsMapper.deleteById(id);
    }

    @Override
    public void updateRDS(Rds rds) {
        rdsMapper.updateById(rds);
    }

    @Override
    public Rds getRDS(Integer id) {
        return rdsMapper.selectById(id);
    }

    @Override
    public List<Rds> queryRDS(Integer groupId, String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }
}
