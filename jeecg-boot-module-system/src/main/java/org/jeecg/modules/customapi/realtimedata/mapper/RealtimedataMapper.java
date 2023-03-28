package org.jeecg.modules.customapi.realtimedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.customapi.realtimedata.entity.Realtimedata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @Description: 实时数据报板
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Mapper
public interface RealtimedataMapper extends BaseMapper<Realtimedata> {

}
