package org.jeecg.modules.customapi.machineproduction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.customapi.machineproduction.entity.Machineproduction;
import org.jeecg.modules.customapi.machineproduction.mapper.MachineproductionMapper;
import org.jeecg.modules.customapi.machineproduction.service.IMachineproductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 机器产能
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Service
public class MachineproductionServiceImpl extends ServiceImpl<MachineproductionMapper, Machineproduction> implements IMachineproductionService {
    @Autowired
    private MachineproductionMapper machineproductionMapper;
    @Transactional
    public List<Machineproduction> getAll(){
        QueryWrapper<Machineproduction> wrapper = new QueryWrapper<>();
        return machineproductionMapper.selectList(wrapper);
    }
}
