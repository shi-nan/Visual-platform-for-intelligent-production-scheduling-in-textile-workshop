package org.jeecg.modules.customapi.machineproduction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.customapi.machineproduction.entity.Machineproduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 机器产能
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Mapper
public interface MachineproductionMapper extends BaseMapper<Machineproduction> {

}
