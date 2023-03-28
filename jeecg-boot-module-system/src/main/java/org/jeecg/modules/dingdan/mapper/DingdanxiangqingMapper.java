package org.jeecg.modules.dingdan.mapper;

import java.util.List;
import org.jeecg.modules.dingdan.entity.Dingdanxiangqing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
public interface DingdanxiangqingMapper extends BaseMapper<Dingdanxiangqing> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<Dingdanxiangqing> selectByMainId(@Param("mainId") String mainId);
}
