package org.jeecg.modules.processes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.processes.entity.Processes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 工序表
 * @Author: jeecg-boot
 * @Date:   2021-11-04
 * @Version: V1.0
 */
@Mapper
public interface ProcessesMapper extends BaseMapper<Processes> {

}
