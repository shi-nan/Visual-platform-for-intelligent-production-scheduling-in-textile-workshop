package org.jeecg.modules.productionbroad;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.dingdan.entity.Dingdan;
import org.jeecg.modules.dingdan.mapper.DingdanMapper;
import org.jeecg.modules.dingdanjincheng.entity.Dingdanjincheng;
import org.jeecg.modules.dingdanjincheng.mapper.DingdanjinchengMapper;
import org.jeecg.modules.machinetable.entity.Machinetable;
import org.jeecg.modules.machinetable.mapper.MachinetableMapper;
import org.jeecg.modules.preschedulemanage.entity.Preschedulemanage;
import org.jeecg.modules.preschedulemanage.service.IPreschedulemanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

//@Api(tags = "前端数据")
@RestController
@RequestMapping("/productionBroad/productionBroad")
@Slf4j
public class productionBroad extends JeecgController<Preschedulemanage, IPreschedulemanageService> {
    @Autowired
    private DingdanMapper dingdanMapper;
    @Autowired
    private DingdanjinchengMapper dingdanjinchengMapper;
    @Autowired
    private MachinetableMapper machinetableMapper;
    /**
     * 数据看板模块
     * @return
     */
//    @ApiOperation(value = "数据看板")
    @GetMapping("/dingdanDateSP")
    public Result<Map<String, Object>> returnOnMachineCount() {

        Map<String,Object> maps = new HashMap<>();

        //条件控制器
        QueryWrapper<Dingdan> queryWrapper = new QueryWrapper<>();
        //订单总数量
        //queryWrapper.between("create_time", "DATE_SUB(NOW(),INTERVAL 12 HOUR)", "DATE_ADD(NOW(),INTERVAL 12 HOUR)");
        Integer dingDanCount = dingdanMapper.selectCount(queryWrapper);

        //生产中订单 -production_status == 1
        queryWrapper.eq("production_status","1");
        Integer productionCount = dingdanMapper.selectCount(queryWrapper);

        //待生产订单
        QueryWrapper<Dingdan> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("production_status","-1");
        Integer dProductionCount = dingdanMapper.selectCount(queryWrapper1);

        //待排产(未排单)订单数量
        QueryWrapper<Dingdan> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("schedule_status", "0");
        Integer waitDingdanCount = dingdanMapper.selectCount(queryWrapper2);

//        //当月已完成订单数量
//        Integer sum = 0;
//        Date date = new Date();
//        String dateFormat = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
//        String[] time1 = simpleDateFormat.format(date).split("-");
//        QueryWrapper<Dingdan> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.eq("order_status",1);
//        List<Dingdan> dingdanList = dingdanMapper.selectList(queryWrapper2);
//        for (Dingdan dingdan:dingdanList
//             ) {
//            Date shipmentdate = dingdan.getAdvanceShipmentdate();
//            String[] time2 = simpleDateFormat.format(shipmentdate).split("-");
//            if(time1[0].equals(time2[0]) && time1[1].equals(time2[0])){
//                sum+=1;
//            }
//        };
        //操作车间当前温度
        Integer wendu = 30;
        //操作车间相对湿度
        String shidu = 64 + "%";

        maps.put("dingdanCount",dingDanCount);
        maps.put("productionCount",productionCount);
        maps.put("dProductionCount",dProductionCount);
        maps.put("waitDingdanCount",waitDingdanCount);
        maps.put("wendu",wendu);
        maps.put("shidu",shidu);

        return Result.OK(maps);

    }

    /**
     * 订单完成情况
     */
    @GetMapping("machineSP")
    public Result<Map<Integer, Object>> machineStartPircture() {
        Map<Integer, Object> map = new HashMap<>();

        //拿到表中的数量
        QueryWrapper<Dingdanjincheng> queryWrapper = new QueryWrapper<>();
        //Integer count = dingdanjinchengMapper.selectCount(queryWrapper);

        //得到所有的Dingdanjinxcheng
        List<Dingdanjincheng> dingdanjinchengs = dingdanjinchengMapper.selectList(queryWrapper);

        int i = 1;
        int over = 0;
        int zong = 8;
        //遍历list集合
        for(Dingdanjincheng dingdanjincheng : dingdanjinchengs){
            over = 0;
            int dingDanName = i;
            if(dingdanjincheng.getCusha() == 1){
                over++;
            }
            if(dingdanjincheng.getXisha() == 1){
                over++;
            }
            if(dingdanjincheng.getHuntiao() == 1){
                over++;
            }
            if(dingdanjincheng.getTouzhen() == 1){
                over++;
            }
            if(dingdanjincheng.getErzhen() == 1){
                over++;
            }
            if(dingdanjincheng.getSanzhen() == 1){
                over++;
            }
            if(dingdanjincheng.getSizhen() == 1){
                over++;
            }
            if(dingdanjincheng.getWuzhen() == 1){
                over++;
            }
            int dingDanNumber = over * 100 / zong;
            map.put(dingDanName,dingDanNumber);
            i++;
        }
        return Result.OK(map);
    }

    /**
     * 开机图
     */
    @GetMapping("kaiji")
    public Result<HashMap<String, Map<String,Integer>>> kaiji(){

        //new一个条件控制器
        QueryWrapper<Machinetable> queryWrapper = new QueryWrapper<>();

        //查出所有机器的列表
        List<Machinetable> machinetables = machinetableMapper.selectList(queryWrapper);

        int huntiaoji = 0;
        int huntiaojiyunxing = 0;
        int cusha = 0;
        int cushayunxing = 0;
        int xishaji = 0;
        int xishajiyunxing = 0;
        int touzhen = 0;
        int touzhenyunxing = 0;
        int erzhen  = 0;
        int erzhenyunxing = 0;
        int sanzhen = 0;
        int sanzhenyunxing = 0;
        int sizhen = 0;
        int sizhenyunxing = 0;
        int wuzhen = 0;
        int wuzhenyunxing = 0;
        //遍历list集合，找出符合条件的机器的数量
        for (Machinetable machinetable : machinetables) {

            if(machinetable.getMachineName().equals("混条机")){
                huntiaoji++;
                if(machinetable.getMachineStatus().equals("1")){
                    huntiaojiyunxing++;
                }
            }else if(machinetable.getMachineName().equals("粗纱机")){
                cusha++;
                if(machinetable.getMachineStatus().equals("1")){
                    cushayunxing++;
                }
            }else if(machinetable.getMachineName().equals("细纱机")){
                xishaji++;
                if(machinetable.getMachineStatus().equals("1")){
                    xishajiyunxing++;
                }
            }else if(machinetable.getMachineName().equals("头道针梳机")){
                touzhen++;
                if(machinetable.getMachineStatus().equals("1")){
                    touzhenyunxing++;
                }
            }else if(machinetable.getMachineName().equals("二道针梳机")){
                erzhen++;
                if(machinetable.getMachineStatus().equals("1")){
                    erzhenyunxing++;
                }
            }else if(machinetable.getMachineName().equals("三道针梳机")){
                sanzhen++;
                if(machinetable.getMachineStatus().equals("1")){
                    sanzhenyunxing++;
                }
            }else if(machinetable.getMachineName().equals("四道针梳机")){
                sizhen++;
                if(machinetable.getMachineStatus().equals("1")){
                    sizhenyunxing++;
                }
            }else if(machinetable.getMachineName().equals("五道针梳机")){
                wuzhen++;
                if(machinetable.getMachineStatus().equals("1")){
                    wuzhenyunxing++;
                }
            }
        }

        //创建一个map用于返回数据
        HashMap<String, Map<String,Integer>> map = new HashMap<>();

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("count",huntiaoji);
        map1.put("yunXingCount",huntiaojiyunxing);
        map.put("混条机",map1);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("count",cusha);
        map2.put("yunXingCount",cushayunxing);
        map.put("粗纱机",map2);

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("count",xishaji);
        map3.put("yunXingCount",xishajiyunxing);
        map.put("细纱机",map3);

        Map<String, Integer> map4 = new HashMap<>();
        map4.put("count",touzhen);
        map4.put("yunXingCount",touzhenyunxing);
        map.put("头道针梳机",map4);

        Map<String, Integer> map5 = new HashMap<>();
        map5.put("count",erzhen);
        map5.put("yunXingCount",erzhenyunxing);
        map.put("二道针梳机",map5);

        Map<String, Integer> map6 = new HashMap<>();
        map6.put("count",sanzhen);
        map6.put("yunXingCount",sanzhenyunxing);
        map.put("三道针梳机",map6);

        Map<String, Integer> map7 = new HashMap<>();
        map7.put("count",sizhen);
        map7.put("yunXingCount",sizhenyunxing);
        map.put("四道针梳机",map7);

        Map<String, Integer> map8 = new HashMap<>();
        map8.put("count",wuzhen);
        map8.put("yunXingCount",wuzhenyunxing);
        map.put("五道针梳机",map8);

        return Result.OK(map);
    }



    /**
     * 燃尽图
     */
    @GetMapping("fireP")
    public Result<Map<String, List<Integer>>> firePicture() {
        List<Integer> mission = new ArrayList<>();
        List<Integer> surplus = new ArrayList<>();
        Map hashMap = new HashMap<>();
        mission.add(850);
        mission.add(650);
        mission.add(600);
        mission.add(410);
        mission.add(381);
        surplus.add(640);
        surplus.add(220);
        surplus.add(200);
        surplus.add(150);
        surplus.add(50);
        hashMap.put("任务量", mission);
        hashMap.put("剩余工作量", surplus);
        return Result.OK(hashMap);
    }


    /**
     * 生产报表
     */
    @GetMapping("productPi")
    public  Result<Map<Integer,Map<String,String>>> productPicture(){

        QueryWrapper<Machinetable> queryWrapper = new QueryWrapper<>();
        List<Machinetable> machinetables = machinetableMapper.selectList(queryWrapper);
        HashMap<Integer, Map<String,String>> map1 = new HashMap<>();
        int i = 1;
        for(Machinetable machinetable : machinetables){
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("deviceId",machinetable.getId());
            map2.put("machineName",machinetable.getMachineName());
            map2.put("machineType",machinetable.getMachineType());
            map2.put("belongProcess",machinetable.getBelongProcess());
            map2.put("technologyStaff",machinetable.getTechnologyStaff());
            map2.put("whatShift",machinetable.getWhatShift());
            map2.put("machineStatus",machinetable.getMachineStatus());
            map1.put(i,map2);
            i++;
        }
        return Result.OK(map1);
    }


    /**
     * 机器产能图
     */
    @GetMapping("/machinePP")
    public Result<ArrayList<Integer>> machineProductivityPicture() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(200);
        arrayList.add(100);
        arrayList.add(250);
        arrayList.add(136);
        arrayList.add(349);
        arrayList.add(110);
        arrayList.add(205);
        return Result.OK(arrayList);
    }


    @GetMapping("/dingdanFp")
    public Result< List<Map<String, Object>>> dingdanFinishPicture() {
        Gson gson = new Gson();
        List<Map<String, Object>> maps = new ArrayList<>();
        //Math.random生成0-1之间的小数
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 1; j <= 3; j++) {
                if (j == 1) {
                    map.put("name", "订单" + i);
                } else if (j == 2) {
                    map.put("use", Math.random() * 100);
                } else {
                    map.put("all", 100);
                }
            }
            maps.add(map);
        }
        return Result.OK(maps);
    }

    @GetMapping("/machineWP")
    public Result<List<Map<String, Object>>> machineWorkingPicture() {
        String[] list = {"细纱机", "粗纱机", "混条机", "一道针梳机", "二道针梳机", "三道针梳机", "四道针梳机", "末道针梳机"};
        List<Map<String, Object>> maps = new ArrayList<>();
        //细纱粗砂混条 针梳机1，2，3，4，末
        for (int i = 0; i < 8; i++) {
            Map<String, Object> map = new HashMap<>();
            QueryWrapper<Machinetable> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("machine_type", list[i]);
            Integer work = null;
            try {
                work = machinetableMapper.selectCount(queryWrapper);
            } catch (Exception e) {
                e.printStackTrace();
            }
            QueryWrapper<Machinetable> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("machine_type", list[i]).eq("machine_status", 1);
            try {
                Integer total = machinetableMapper.selectCount(queryWrapper1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 8; j++) {
                map.put("work",work);
                map.put("name",list[i]);
                map.put("work",work);
            }
            maps.add(map);
        }
        return Result.OK(maps);
    }

    @GetMapping("/machineDevices")
    public Result<List<Machinetable>> returnMachineDevices(){
        QueryWrapper<Machinetable> queryWrapper = new QueryWrapper<>();
        List<Machinetable> machinetables = machinetableMapper.selectList(queryWrapper);
        return Result.OK(machinetables);
    }
}
