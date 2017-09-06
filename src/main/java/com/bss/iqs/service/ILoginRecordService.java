package com.bss.iqs.service;


import com.baomidou.mybatisplus.service.IService;
import com.bss.iqs.entity.LoginRecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgh
 * @since 2017-08-29
 */
public interface ILoginRecordService extends IService<LoginRecord> {
     List<LoginRecord> getUserLoginRecord(String type, String keyword, Integer pageNum, Integer pageSize);
}
