package org.jeecg.modules.machinetable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.machinetable.entity.Machinetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Mapper
public interface MachinetableMapper extends BaseMapper<Machinetable> {

}
