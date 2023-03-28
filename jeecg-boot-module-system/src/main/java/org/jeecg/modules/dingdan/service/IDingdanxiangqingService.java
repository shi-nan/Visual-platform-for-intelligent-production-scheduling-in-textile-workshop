package org.jeecg.modules.dingdan.service;

import org.jeecg.modules.dingdan.entity.Dingdanxiangqing;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
public interface IDingdanxiangqingService extends IService<Dingdanxiangqing> {

	public List<Dingdanxiangqing> selectByMainId(String mainId);
}
