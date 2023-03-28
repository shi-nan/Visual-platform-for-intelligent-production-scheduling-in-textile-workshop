package org.jeecg.modules.dingdan.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.dingdan.entity.Dingdanxiangqing;
import org.jeecg.modules.dingdan.entity.Staffchange;
import org.jeecg.modules.dingdan.entity.Dingdan;
import org.jeecg.modules.dingdan.vo.DingdanPage;
import org.jeecg.modules.dingdan.service.IDingdanService;
import org.jeecg.modules.dingdan.service.IDingdanxiangqingService;
import org.jeecg.modules.dingdan.service.IStaffchangeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 订单表
 * @Author: jeecg-boot
 * @Date: 2021-12-08
 * @Version: V1.0
 */
@Api(tags = "订单表")
@RestController
@RequestMapping("/dingdan/dingdan")
@Slf4j
public class DingdanController {

    @Autowired
    private IDingdanService dingdanService;
    @Autowired
    private IDingdanxiangqingService dingdanxiangqingService;
    @Autowired
    private IStaffchangeService staffchangeService;

    /**
     * 订单总数
     * 订单数量信息
     */
    @GetMapping("/dingdanCount")
    public Result<List<Integer>> dingdanNumberInfo(){
        List<Integer> numberList = dingdanService.NumberInfo();
        return Result.OK(numberList);
    }

    /**
     * 排单按钮
     */
    @GetMapping("/changeStatus")
    public Result<?> paidanButton(String id, String name, String type) {
        Integer status = dingdanService.changeStatus(id, name, type);
        return Result.OK(status);
    }

    /**
     * 撤单按钮
     *
     */
    @GetMapping("/changeCancel")
    public Result<?> chedanButton(String id) {
        Integer status = dingdanService.changeCancel(id);
        return Result.OK(status);
    }

    /**
     * 未完成、未排单、未生产
     */
    @AutoLog(value = "订单表-分页列表查询")
    @ApiOperation(value = "订单表-分页列表查询", notes = "订单表-分页列表查询")
    @GetMapping(value = "/nopaidan")
    public Result<?> noPaidanQueryPageList(Dingdan dingdan,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                           HttpServletRequest req) {
        //QueryWrapper<Dingdan> queryWrapper = QueryGenerator.initQueryWrapper(dingdan, req.getParameterMap());
        Page<Dingdan> page = new Page<Dingdan>(pageNo, pageSize);
        QueryWrapper<Dingdan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", 0).eq("production_status", 0).eq("schedule_status", 0);
        IPage<Dingdan> pageList = dingdanService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 未完成，已排单，未生产
     */
    @AutoLog(value = "订单表-分页列表查询")
    @ApiOperation(value = "订单表-分页列表查询", notes = "订单表-分页列表查询")
    @GetMapping(value = "/jihuapaidan")
    public Result<?> scheduleQueryPageList(Dingdan dingdan,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                           HttpServletRequest req) {
        //QueryWrapper<Dingdan> queryWrapper = QueryGenerator.initQueryWrapper(dingdan, req.getParameterMap());
        Page<Dingdan> page = new Page<Dingdan>(pageNo, pageSize);
        QueryWrapper<Dingdan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", 0).eq("production_status", 0).eq("schedule_status", 1);
        IPage<Dingdan> pageList = dingdanService.page(page, queryWrapper);
        return Result.OK(pageList);
    }
    /**
     * 未完成，已排单，待生产or在生产
     */
    @AutoLog(value = "订单表-分页列表查询")
    @ApiOperation(value = "订单表-分页列表查询", notes = "订单表-分页列表查询")
    @GetMapping(value = "/productionDingdan")
    public Result<?> productionQueryPageList(Dingdan dingdan,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                           HttpServletRequest req) {
        //QueryWrapper<Dingdan> queryWrapper = QueryGenerator.initQueryWrapper(dingdan, req.getParameterMap());
        Page<Dingdan> page = new Page<Dingdan>(pageNo, pageSize);
        QueryWrapper<Dingdan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", 0).eq("production_status",-1).or().eq("production_status",1).eq("schedule_status", 1);
        IPage<Dingdan> pageList = dingdanService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 分页列表查询
     *
     * @param dingdan
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "订单表-分页列表查询")
    @ApiOperation(value = "订单表-分页列表查询", notes = "订单表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dingdan dingdan,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dingdan> queryWrapper = QueryGenerator.initQueryWrapper(dingdan, req.getParameterMap());
        Page<Dingdan> page = new Page<Dingdan>(pageNo, pageSize);
        IPage<Dingdan> pageList = dingdanService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param dingdanPage
     * @return
     */
    @AutoLog(value = "订单表-添加")
    @ApiOperation(value = "订单表-添加", notes = "订单表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody DingdanPage dingdanPage) {
        Dingdan dingdan = new Dingdan();
        BeanUtils.copyProperties(dingdanPage, dingdan);
        dingdanService.saveMain(dingdan, dingdanPage.getDingdanxiangqingList(), dingdanPage.getStaffchangeList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dingdanPage
     * @return
     */
    @AutoLog(value = "订单表-编辑")
    @ApiOperation(value = "订单表-编辑", notes = "订单表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody DingdanPage dingdanPage) {
        Dingdan dingdan = new Dingdan();
        BeanUtils.copyProperties(dingdanPage, dingdan);
        Dingdan dingdanEntity = dingdanService.getById(dingdan.getId());
        if (dingdanEntity == null) {
            return Result.error("未找到对应数据");
        }
        dingdanService.updateMain(dingdan, dingdanPage.getDingdanxiangqingList(), dingdanPage.getStaffchangeList());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "订单表-通过id删除")
    @ApiOperation(value = "订单表-通过id删除", notes = "订单表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dingdanService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "订单表-批量删除")
    @ApiOperation(value = "订单表-批量删除", notes = "订单表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dingdanService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "订单表-通过id查询")
    @ApiOperation(value = "订单表-通过id查询", notes = "订单表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dingdan dingdan = dingdanService.getById(id);
        if (dingdan == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(dingdan);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "订单详情通过主表ID查询")
    @ApiOperation(value = "订单详情主表ID查询", notes = "订单详情-通主表ID查询")
    @GetMapping(value = "/queryDingdanxiangqingByMainId")
    public Result<?> queryDingdanxiangqingListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<Dingdanxiangqing> dingdanxiangqingList = dingdanxiangqingService.selectByMainId(id);
        return Result.OK(dingdanxiangqingList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工人换班通过主表ID查询")
    @ApiOperation(value = "工人换班主表ID查询", notes = "工人换班-通主表ID查询")
    @GetMapping(value = "/queryStaffchangeByMainId")
    public Result<?> queryStaffchangeListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<Staffchange> staffchangeList = staffchangeService.selectByMainId(id);
        return Result.OK(staffchangeList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dingdan
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dingdan dingdan) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<Dingdan> queryWrapper = QueryGenerator.initQueryWrapper(dingdan, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //Step.2 获取导出数据
        List<Dingdan> queryList = dingdanService.list(queryWrapper);
        // 过滤选中数据
        String selections = request.getParameter("selections");
        List<Dingdan> dingdanList = new ArrayList<Dingdan>();
        if (oConvertUtils.isEmpty(selections)) {
            dingdanList = queryList;
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            dingdanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
        }

        // Step.3 组装pageList
        List<DingdanPage> pageList = new ArrayList<DingdanPage>();
        for (Dingdan main : dingdanList) {
            DingdanPage vo = new DingdanPage();
            BeanUtils.copyProperties(main, vo);
            List<Dingdanxiangqing> dingdanxiangqingList = dingdanxiangqingService.selectByMainId(main.getId());
            vo.setDingdanxiangqingList(dingdanxiangqingList);
            List<Staffchange> staffchangeList = staffchangeService.selectByMainId(main.getId());
            vo.setStaffchangeList(staffchangeList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "订单表列表");
        mv.addObject(NormalExcelConstants.CLASS, DingdanPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单表数据", "导出人:" + sysUser.getRealname(), "订单表"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<DingdanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), DingdanPage.class, params);
                for (DingdanPage page : list) {
                    Dingdan po = new Dingdan();
                    BeanUtils.copyProperties(page, po);
                    dingdanService.saveMain(po, page.getDingdanxiangqingList(), page.getStaffchangeList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.OK("文件导入失败！");
    }

}
