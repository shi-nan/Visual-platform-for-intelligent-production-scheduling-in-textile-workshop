package org.jeecg.modules.processes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.processes.entity.Processes;
import org.jeecg.modules.processes.mapper.ProcessesMapper;
import org.jeecg.modules.processes.service.IProcessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 工序表
 * @Author: jeecg-boot
 * @Date:   2021-11-04
 * @Version: V1.0
 */
@Service
public class ProcessesServiceImpl extends ServiceImpl<ProcessesMapper, Processes> implements IProcessesService {
    @Autowired
    private ProcessesMapper processesMapper;
    @Override
    public List<Processes> queryGanTeTu() {
        QueryWrapper<Processes> wrapper = new QueryWrapper<>();
        return processesMapper.selectList(wrapper);
    }
}
