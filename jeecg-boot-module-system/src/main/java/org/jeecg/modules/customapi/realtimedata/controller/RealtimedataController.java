package org.jeecg.modules.customapi.realtimedata.controller;

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
import org.jeecg.modules.customapi.machineproduction.entity.Machineproduction;
import org.jeecg.modules.customapi.realtimedata.entity.Realtimedata;
import org.jeecg.modules.customapi.realtimedata.service.IRealtimedataService;

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
 * @Description: 实时数据报板
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Api(tags="实时数据报板")
@RestController
@RequestMapping("/realtimedata/realtimedata")
@Slf4j
public class RealtimedataController extends JeecgController<Realtimedata, IRealtimedataService> {
	@Autowired
	private IRealtimedataService realtimedataService;
	
	/**
	 * 分页列表查询
	 *
	 * @param realtimedata
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "实时数据报板-分页列表查询")
	@ApiOperation(value="实时数据报板-分页列表查询", notes="实时数据报板-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Realtimedata realtimedata,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Realtimedata> queryWrapper = QueryGenerator.initQueryWrapper(realtimedata, req.getParameterMap());
		Page<Realtimedata> page = new Page<Realtimedata>(pageNo, pageSize);
		IPage<Realtimedata> pageList = realtimedataService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	 /**
	  * LJCADD
	  */
	 @GetMapping("/test")
	 public Result<List<Realtimedata>> queryList(){
		 List<Realtimedata> realtimedatas = realtimedataService.getAll();
		 Result<List<Realtimedata>> result = new Result<>();
		 result.setResult(realtimedatas);
		 result.setSuccess(true);
		 return result;
	 }
	/**
	 *   添加
	 *
	 * @param realtimedata
	 * @return
	 */
	@AutoLog(value = "实时数据报板-添加")
	@ApiOperation(value="实时数据报板-添加", notes="实时数据报板-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Realtimedata realtimedata) {
		realtimedataService.save(realtimedata);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param realtimedata
	 * @return
	 */
	@AutoLog(value = "实时数据报板-编辑")
	@ApiOperation(value="实时数据报板-编辑", notes="实时数据报板-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Realtimedata realtimedata) {
		realtimedataService.updateById(realtimedata);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "实时数据报板-通过id删除")
	@ApiOperation(value="实时数据报板-通过id删除", notes="实时数据报板-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		realtimedataService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "实时数据报板-批量删除")
	@ApiOperation(value="实时数据报板-批量删除", notes="实时数据报板-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.realtimedataService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "实时数据报板-通过id查询")
	@ApiOperation(value="实时数据报板-通过id查询", notes="实时数据报板-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Realtimedata realtimedata = realtimedataService.getById(id);
		if(realtimedata==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(realtimedata);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param realtimedata
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Realtimedata realtimedata) {
        return super.exportXls(request, realtimedata, Realtimedata.class, "实时数据报板");
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
        return super.importExcel(request, response, Realtimedata.class);
    }

}
