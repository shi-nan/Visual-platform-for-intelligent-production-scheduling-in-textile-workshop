package org.jeecg.modules.processes.service;

import org.jeecg.modules.processes.entity.Processes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 工序表
 * @Author: jeecg-boot
 * @Date:   2021-11-04
 * @Version: V1.0
 */
public interface IProcessesService extends IService<Processes> {
    List<Processes> queryGanTeTu();
}
