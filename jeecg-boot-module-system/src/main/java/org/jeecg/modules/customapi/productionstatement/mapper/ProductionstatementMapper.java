package org.jeecg.modules.customapi.productionstatement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.customapi.productionstatement.entity.Productionstatement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 生产报表
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Mapper
public interface ProductionstatementMapper extends BaseMapper<Productionstatement> {

}
