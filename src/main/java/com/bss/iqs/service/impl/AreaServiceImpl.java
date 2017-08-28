package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.entity.Area;
import com.bss.iqs.mapper.AreaMapper;
import com.bss.iqs.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-26
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<String> getCitys(Integer parentId) {
        Wrapper<Area> areaWrapper = new EntityWrapper<>();
        areaWrapper.eq("parentId",parentId);
        List<Area> areas = areaMapper.selectList(areaWrapper);
        if (areas != null && areas.size() != 0){
            List<String> citys = new ArrayList<>();
            for (Area area: areas) {
                citys.add(area.getCity());
            }
            return citys;
        }

        return null;
    }
}
