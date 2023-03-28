package org.jeecg.modules.customapi.productionstatement.service;

import org.jeecg.modules.customapi.productionstatement.entity.Productionstatement;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 生产报表
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
public interface IProductionstatementService extends IService<Productionstatement> {

    List<Productionstatement> getAll();
}
