package org.jeecg.modules.customapi.machineproduction.service;

import org.jeecg.modules.customapi.machineproduction.entity.Machineproduction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 机器产能
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
public interface IMachineproductionService extends IService<Machineproduction> {

    List<Machineproduction> getAll();
}
