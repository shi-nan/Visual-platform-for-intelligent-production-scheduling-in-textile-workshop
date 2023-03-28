package org.jeecg.modules.dingdan.service;

import org.jeecg.modules.dingdan.entity.Staffchange;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 工人换班
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
public interface IStaffchangeService extends IService<Staffchange> {

	public List<Staffchange> selectByMainId(String mainId);
}
