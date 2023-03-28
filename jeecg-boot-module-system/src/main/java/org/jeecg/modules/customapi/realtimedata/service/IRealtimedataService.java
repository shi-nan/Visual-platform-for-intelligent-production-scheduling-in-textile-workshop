package org.jeecg.modules.customapi.realtimedata.service;

import org.jeecg.modules.customapi.realtimedata.entity.Realtimedata;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 实时数据报板
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
public interface IRealtimedataService extends IService<Realtimedata> {

    List<Realtimedata> getAll();
}
