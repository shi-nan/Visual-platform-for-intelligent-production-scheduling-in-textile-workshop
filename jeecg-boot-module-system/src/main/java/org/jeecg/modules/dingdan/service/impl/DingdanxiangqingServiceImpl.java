package org.jeecg.modules.dingdan.service.impl;

import org.jeecg.modules.dingdan.entity.Dingdanxiangqing;
import org.jeecg.modules.dingdan.mapper.DingdanxiangqingMapper;
import org.jeecg.modules.dingdan.service.IDingdanxiangqingService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Service
public class DingdanxiangqingServiceImpl extends ServiceImpl<DingdanxiangqingMapper, Dingdanxiangqing> implements IDingdanxiangqingService {
	
	@Autowired
	private DingdanxiangqingMapper dingdanxiangqingMapper;
	
	@Override
	public List<Dingdanxiangqing> selectByMainId(String mainId) {
		return dingdanxiangqingMapper.selectByMainId(mainId);
	}
}
