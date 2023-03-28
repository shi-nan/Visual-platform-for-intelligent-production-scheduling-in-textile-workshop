package org.jeecg.modules.customapi.productionstatement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.customapi.machineproduction.entity.Machineproduction;
import org.jeecg.modules.customapi.productionstatement.entity.Productionstatement;
import org.jeecg.modules.customapi.productionstatement.mapper.ProductionstatementMapper;
import org.jeecg.modules.customapi.productionstatement.service.IProductionstatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 生产报表
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Service
public class ProductionstatementServiceImpl extends ServiceImpl<ProductionstatementMapper, Productionstatement> implements IProductionstatementService {
    @Autowired
    private ProductionstatementMapper productionstatementMapper;
    @Transactional
    public List<Productionstatement> getAll(){
        QueryWrapper<Productionstatement> wrapper = new QueryWrapper<>();
        return productionstatementMapper.selectList(wrapper);
    }
}
