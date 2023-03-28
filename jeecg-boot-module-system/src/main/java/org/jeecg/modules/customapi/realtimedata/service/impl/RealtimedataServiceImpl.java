package org.jeecg.modules.customapi.realtimedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.customapi.realtimedata.entity.Realtimedata;
import org.jeecg.modules.customapi.realtimedata.mapper.RealtimedataMapper;
import org.jeecg.modules.customapi.realtimedata.service.IRealtimedataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 实时数据报板
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Service
public class RealtimedataServiceImpl extends ServiceImpl<RealtimedataMapper, Realtimedata> implements IRealtimedataService {
    @Autowired
    private RealtimedataMapper realtimedataMapper;
    @Override
    public List<Realtimedata> getAll() {
        QueryWrapper<Realtimedata> Wrapper = new QueryWrapper<>();
        return realtimedataMapper.selectList(Wrapper);
    }
}
