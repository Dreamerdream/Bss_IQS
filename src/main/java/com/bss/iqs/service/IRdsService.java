package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.bean.ResultBean;
import com.bss.iqs.entity.Rds;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
public interface IRdsService extends IService<Rds> {
    public ResultBean saveRDS(Rds rds);

    public ResultBean deleteRDS(Integer id);

    public ResultBean updateRDS(Rds rds);

    public Rds getRDS(Integer id);

    public List<Rds> queryRDS(Integer groupId, String keyword, Integer pageNum, Integer pageSize);
}
