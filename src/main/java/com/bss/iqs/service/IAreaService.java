package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.entity.Area;

import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
public interface IAreaService extends IService<Area> {
     List<String> getCitys(Integer parentId);
}
