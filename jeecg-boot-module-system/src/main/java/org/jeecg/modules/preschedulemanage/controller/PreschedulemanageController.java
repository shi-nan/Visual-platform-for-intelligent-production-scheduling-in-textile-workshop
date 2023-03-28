package org.jeecg.modules.preschedulemanage.controller;

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
import org.jeecg.modules.preschedulemanage.entity.Preschedulemanage;
import org.jeecg.modules.preschedulemanage.service.IPreschedulemanageService;

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
 * @Description: 预排程管理
 * @Author: jeecg-boot
 * @Date:   2021-11-24
 * @Version: V1.0
 */
@Api(tags="预排程管理")
@RestController
@RequestMapping("/preschedulemanage/preschedulemanage")
@Slf4j
public class PreschedulemanageController extends JeecgController<Preschedulemanage, IPreschedulemanageService> {
	@Autowired
	private IPreschedulemanageService preschedulemanageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param preschedulemanage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "预排程管理-分页列表查询")
	@ApiOperation(value="预排程管理-分页列表查询", notes="预排程管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Preschedulemanage preschedulemanage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Preschedulemanage> queryWrapper = QueryGenerator.initQueryWrapper(preschedulemanage, req.getParameterMap());
		Page<Preschedulemanage> page = new Page<Preschedulemanage>(pageNo, pageSize);
		IPage<Preschedulemanage> pageList = preschedulemanageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param preschedulemanage
	 * @return
	 */
	@AutoLog(value = "预排程管理-添加")
	@ApiOperation(value="预排程管理-添加", notes="预排程管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Preschedulemanage preschedulemanage) {
		preschedulemanageService.save(preschedulemanage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param preschedulemanage
	 * @return
	 */
	@AutoLog(value = "预排程管理-编辑")
	@ApiOperation(value="预排程管理-编辑", notes="预排程管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Preschedulemanage preschedulemanage) {
		preschedulemanageService.updateById(preschedulemanage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "预排程管理-通过id删除")
	@ApiOperation(value="预排程管理-通过id删除", notes="预排程管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		preschedulemanageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "预排程管理-批量删除")
	@ApiOperation(value="预排程管理-批量删除", notes="预排程管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.preschedulemanageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "预排程管理-通过id查询")
	@ApiOperation(value="预排程管理-通过id查询", notes="预排程管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Preschedulemanage preschedulemanage = preschedulemanageService.getById(id);
		if(preschedulemanage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(preschedulemanage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param preschedulemanage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Preschedulemanage preschedulemanage) {
        return super.exportXls(request, preschedulemanage, Preschedulemanage.class, "预排程管理");
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
        return super.importExcel(request, response, Preschedulemanage.class);
    }

}
