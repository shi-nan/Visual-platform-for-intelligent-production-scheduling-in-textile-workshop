package org.jeecg.modules.dingdan.service.impl;

import org.jeecg.modules.dingdan.entity.Staffchange;
import org.jeecg.modules.dingdan.mapper.StaffchangeMapper;
import org.jeecg.modules.dingdan.service.IStaffchangeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 工人换班
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Service
public class StaffchangeServiceImpl extends ServiceImpl<StaffchangeMapper, Staffchange> implements IStaffchangeService {
	
	@Autowired
	private StaffchangeMapper staffchangeMapper;
	
	@Override
	public List<Staffchange> selectByMainId(String mainId) {
		return staffchangeMapper.selectByMainId(mainId);
	}
}
