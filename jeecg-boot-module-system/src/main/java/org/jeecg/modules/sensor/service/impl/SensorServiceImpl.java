package org.jeecg.modules.sensor.service.impl;

import org.jeecg.modules.sensor.entity.Sensor;
import org.jeecg.modules.sensor.mapper.SensorMapper;
import org.jeecg.modules.sensor.service.ISensorService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 传感器表
 * @Author: jeecg-boot
 * @Date:   2022-04-23
 * @Version: V1.0
 */
@Service
public class SensorServiceImpl extends ServiceImpl<SensorMapper, Sensor> implements ISensorService {

}
