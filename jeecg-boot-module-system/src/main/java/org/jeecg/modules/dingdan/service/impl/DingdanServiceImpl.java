package org.jeecg.modules.dingdan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.jeecg.modules.dingdan.entity.Dingdan;
import org.jeecg.modules.dingdan.entity.Dingdanxiangqing;
import org.jeecg.modules.dingdan.entity.Staffchange;
import org.jeecg.modules.dingdan.mapper.DingdanxiangqingMapper;
import org.jeecg.modules.dingdan.mapper.StaffchangeMapper;
import org.jeecg.modules.dingdan.mapper.DingdanMapper;
import org.jeecg.modules.dingdan.service.IDingdanService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单表
 * @Author: jeecg-boot
 * @Date: 2021-12-08
 * @Version: V1.0
 */
@Service
public class DingdanServiceImpl extends ServiceImpl<DingdanMapper, Dingdan> implements IDingdanService {

    @Autowired
    private DingdanMapper dingdanMapper;
    @Autowired
    private DingdanxiangqingMapper dingdanxiangqingMapper;
    @Autowired
    private StaffchangeMapper staffchangeMapper;

    @Override
    @Transactional
    public void saveMain(Dingdan dingdan, List<Dingdanxiangqing> dingdanxiangqingList, List<Staffchange> staffchangeList) {
        dingdanMapper.insert(dingdan);
        if (dingdanxiangqingList != null && dingdanxiangqingList.size() > 0) {
            for (Dingdanxiangqing entity : dingdanxiangqingList) {
                //外键设置
                entity.setOrderId(dingdan.getId());
                dingdanxiangqingMapper.insert(entity);
            }
        }
        if (staffchangeList != null && staffchangeList.size() > 0) {
            for (Staffchange entity : staffchangeList) {
                //外键设置
                entity.setOrderId(dingdan.getId());
                staffchangeMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void updateMain(Dingdan dingdan, List<Dingdanxiangqing> dingdanxiangqingList, List<Staffchange> staffchangeList) {
        dingdanMapper.updateById(dingdan);

        //1.先删除子表数据
        dingdanxiangqingMapper.deleteByMainId(dingdan.getId());
        staffchangeMapper.deleteByMainId(dingdan.getId());

        //2.子表数据重新插入
        if (dingdanxiangqingList != null && dingdanxiangqingList.size() > 0) {
            for (Dingdanxiangqing entity : dingdanxiangqingList) {
                //外键设置
                entity.setOrderId(dingdan.getId());
                dingdanxiangqingMapper.insert(entity);
            }
        }
        if (staffchangeList != null && staffchangeList.size() > 0) {
            for (Staffchange entity : staffchangeList) {
                //外键设置
                entity.setOrderId(dingdan.getId());
                staffchangeMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void delMain(String id) {
        dingdanxiangqingMapper.deleteByMainId(id);
        staffchangeMapper.deleteByMainId(id);
        dingdanMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            dingdanxiangqingMapper.deleteByMainId(id.toString());
            staffchangeMapper.deleteByMainId(id.toString());
            dingdanMapper.deleteById(id);
        }
    }
    //排单按钮
    @Override
    public Integer changeStatus(String id, String name, String type) {
        //Dingdan dingdan = dingdanMapper.selectById(id);
        if (type != null) {
            if (type.equals("pd")) {
                UpdateWrapper<Dingdan> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", id).set("schedule_status", 1).set("schedule_staff", name);
                int update = dingdanMapper.update(null, updateWrapper);
                return update;
            }
            if (type.equals("pc")) {
                UpdateWrapper<Dingdan> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", id).set("production_status", -1);
                int update = dingdanMapper.update(null, updateWrapper);
                return update;
            }
        }
        return -1;
    }
    //撤单按钮
    @Override
    public Integer changeCancel(String id) {
        UpdateWrapper<Dingdan> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id).set("production_status",0);
        int update = dingdanMapper.update(null, wrapper);
        return update;
    }

    @Override
    public List<Integer> NumberInfo() {
        ArrayList<Integer> integers = new ArrayList<>();
        //待排产订单
        QueryWrapper<Dingdan> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("schedule_status", "0");
        Integer integer1 = dingdanMapper.selectCount(queryWrapper1);
        //当月已完成订单
        QueryWrapper<Dingdan> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("order_status", "1");
        Integer integer2 = dingdanMapper.selectCount(queryWrapper2);
        //新订单
        QueryWrapper<Dingdan> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.between("create_time","DATE_SUB(NOW(),INTERVAL 12 HOUR)","DATE_ADD(NOW(),INTERVAL 12 HOUR)");
        Integer integer = dingdanMapper.selectCount(queryWrapper3);
        integers.add(integer1);
        integers.add(integer2);
        return integers;
    }



}
