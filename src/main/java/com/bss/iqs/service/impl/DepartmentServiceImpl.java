package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.entity.Department;
import com.bss.iqs.mapper.DepartmentMapper;
import com.bss.iqs.service.IDepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-25
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
	
}
