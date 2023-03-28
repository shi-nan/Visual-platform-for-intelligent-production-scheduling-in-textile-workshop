package org.jeecg.modules.dingdanjincheng.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.dingdanjincheng.entity.Dingdanjincheng;
import org.jeecg.modules.dingdanjincheng.service.IDingdanjinchengService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 记录订单进程
 * @Author: jeecg-boot
 * @Date:   2022-04-21
 * @Version: V1.0
 */
@Api(tags="记录订单进程")
@RestController
@RequestMapping("/dingdanjincheng/dingdanjincheng")
@Slf4j
public class DingdanjinchengController extends JeecgController<Dingdanjincheng, IDingdanjinchengService> {
	@Autowired
	private IDingdanjinchengService dingdanjinchengService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dingdanjincheng
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "记录订单进程-分页列表查询")
	@ApiOperation(value="记录订单进程-分页列表查询", notes="记录订单进程-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dingdanjincheng dingdanjincheng,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dingdanjincheng> queryWrapper = QueryGenerator.initQueryWrapper(dingdanjincheng, req.getParameterMap());
		Page<Dingdanjincheng> page = new Page<Dingdanjincheng>(pageNo, pageSize);
		IPage<Dingdanjincheng> pageList = dingdanjinchengService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param dingdanjincheng
	 * @return
	 */
	@AutoLog(value = "记录订单进程-添加")
	@ApiOperation(value="记录订单进程-添加", notes="记录订单进程-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dingdanjincheng dingdanjincheng) {
		dingdanjinchengService.save(dingdanjincheng);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dingdanjincheng
	 * @return
	 */
	@AutoLog(value = "记录订单进程-编辑")
	@ApiOperation(value="记录订单进程-编辑", notes="记录订单进程-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dingdanjincheng dingdanjincheng) {
		dingdanjinchengService.updateById(dingdanjincheng);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "记录订单进程-通过id删除")
	@ApiOperation(value="记录订单进程-通过id删除", notes="记录订单进程-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dingdanjinchengService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "记录订单进程-批量删除")
	@ApiOperation(value="记录订单进程-批量删除", notes="记录订单进程-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dingdanjinchengService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "记录订单进程-通过id查询")
	@ApiOperation(value="记录订单进程-通过id查询", notes="记录订单进程-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dingdanjincheng dingdanjincheng = dingdanjinchengService.getById(id);
		if(dingdanjincheng==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(dingdanjincheng);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dingdanjincheng
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dingdanjincheng dingdanjincheng) {
        return super.exportXls(request, dingdanjincheng, Dingdanjincheng.class, "记录订单进程");
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
        return super.importExcel(request, response, Dingdanjincheng.class);
    }

}
