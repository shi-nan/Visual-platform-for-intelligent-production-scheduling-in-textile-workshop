package org.jeecg.modules.dingdan.mapper;

import java.util.List;
import org.jeecg.modules.dingdan.entity.Staffchange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 工人换班
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
public interface StaffchangeMapper extends BaseMapper<Staffchange> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<Staffchange> selectByMainId(@Param("mainId") String mainId);
}
