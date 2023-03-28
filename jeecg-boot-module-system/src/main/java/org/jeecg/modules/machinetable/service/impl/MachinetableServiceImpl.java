package org.jeecg.modules.machinetable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.machinetable.entity.Machinetable;
import org.jeecg.modules.machinetable.mapper.MachinetableMapper;
import org.jeecg.modules.machinetable.service.IMachinetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Service
public class MachinetableServiceImpl extends ServiceImpl<MachinetableMapper, Machinetable> implements IMachinetableService {
    @Autowired
    private MachinetableMapper machinetableMapper;
    @Override
    public List<Machinetable> getAll() {
        QueryWrapper<Machinetable> queryWrapper = new QueryWrapper<>();
        List<Machinetable> machinetableList = machinetableMapper.selectList(queryWrapper);

        return  machinetableList;
    }

    @Override
    public int getCount() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("machine_status",1);
        Integer count = machinetableMapper.selectCount(queryWrapper);
        return count;
    }
}
