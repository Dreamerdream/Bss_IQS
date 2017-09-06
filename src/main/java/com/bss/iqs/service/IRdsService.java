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
     ResultBean saveRDS(Rds rds);

     ResultBean deleteRDS(Integer id);

     ResultBean updateRDS(Rds rds);

     Rds getRDS(Integer id);

     List<Rds> queryAllRDS();
}
