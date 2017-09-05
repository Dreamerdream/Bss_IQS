package com.bss.iqs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bss.iqs.bean.PageBean;
import com.bss.iqs.entity.LoginRecord;
import com.bss.iqs.entity.PlanTaskRecord;
import com.bss.iqs.mapper.LoginRecordMapper;
import com.bss.iqs.service.ILoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgh
 * @since 2017-08-29
 */
@Service
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord> implements ILoginRecordService {

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    public List<LoginRecord> getUserLoginRecord(String type, String keyword, Integer pageNum, Integer pageSize){
        int pageStart = 0;
        if (pageNum != null && pageSize != null ){
            pageStart = (pageNum - 1) * pageSize;
        }
        List<LoginRecord> loginRecords = new ArrayList<>();
        if ("用户名".equals(type)) {
            loginRecords = loginRecordMapper.getUserLoginRecord(new Pagination(pageStart,pageSize),"username", keyword);
        } else if ("真实姓名".equals(type)){
            loginRecords = loginRecordMapper.getUserLoginRecord(new Pagination(pageStart,pageSize),"realname", keyword);
        }else if ("所属部门".equals(type)) {
            loginRecords = loginRecordMapper.getUserLoginRecord(new Pagination(pageStart,pageSize),"departmentName", keyword);
        }

        //自己分页
//        int pageStart2 = 0;
//        int pageEnd2 = 0;
//        if (pageNum != null && pageSize != null ){
//            pageStart2 = (pageNum - 1) * pageSize;
//            pageEnd2 = pageStart2 + pageSize;
//        }
//
//        Wrapper<LoginRecord> loginRecordWrapper = new EntityWrapper<>();
//        List<LoginRecord> loginRecords1 = new ArrayList<>();
//        if ("用户名".equals(type)) {
//            loginRecordWrapper.like("username",keyword);
//            loginRecords1 = loginRecordMapper.getUserLoginRecord2("username",keyword,pageStart2,pageEnd2);
//        } else if ("真实姓名".equals(type)){
//            loginRecordWrapper.like("realname",keyword);
//            loginRecords1 = loginRecordMapper.getUserLoginRecord2("realname", keyword, pageStart2, pageEnd2);
//
//        }else if ("所属部门".equals(type)) {
//            loginRecordWrapper.like("departmentName",keyword);
//            loginRecords1 = loginRecordMapper.getUserLoginRecord2("departmentName",keyword,pageStart2,pageEnd2);
//
//        }
//        Integer count = loginRecordMapper.selectCount(loginRecordWrapper);
//
//        PageBean pageBean = new PageBean(pageStart2,pageSize,loginRecords1,count,"");

        return loginRecords;
    }
}
