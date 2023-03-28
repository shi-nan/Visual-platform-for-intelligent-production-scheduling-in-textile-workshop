package org.jeecg.modules.dingdan.service;

import org.jeecg.modules.dingdan.entity.Dingdanxiangqing;
import org.jeecg.modules.dingdan.entity.Staffchange;
import org.jeecg.modules.dingdan.entity.Dingdan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单表
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
public interface IDingdanService extends IService<Dingdan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Dingdan dingdan,List<Dingdanxiangqing> dingdanxiangqingList,List<Staffchange> staffchangeList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Dingdan dingdan,List<Dingdanxiangqing> dingdanxiangqingList,List<Staffchange> staffchangeList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

    Integer changeStatus(String id, String name,String type);
	Integer changeCancel(String id);

    List<Integer> NumberInfo();
}
