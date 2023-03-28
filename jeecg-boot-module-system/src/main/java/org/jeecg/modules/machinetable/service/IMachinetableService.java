package org.jeecg.modules.machinetable.service;

import org.jeecg.modules.machinetable.entity.Machinetable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
public interface IMachinetableService extends IService<Machinetable> {

    List<Machinetable> getAll();
    int getCount();
}
