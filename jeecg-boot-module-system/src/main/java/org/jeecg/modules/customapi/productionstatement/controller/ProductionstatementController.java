package org.jeecg.modules.customapi.productionstatement.controller;

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
import org.jeecg.modules.customapi.productionstatement.entity.Productionstatement;
import org.jeecg.modules.customapi.productionstatement.service.IProductionstatementService;

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
 * @Description: 生产报表
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Api(tags="生产报表")
@RestController
@RequestMapping("/productionstatement/productionstatement")
@Slf4j
public class ProductionstatementController extends JeecgController<Productionstatement, IProductionstatementService> {
	@Autowired
	private IProductionstatementService productionstatementService;
	
	/**
	 * 分页列表查询
	 *
	 * @param productionstatement
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "生产报表-分页列表查询")
	@ApiOperation(value="生产报表-分页列表查询", notes="生产报表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Productionstatement productionstatement,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Productionstatement> queryWrapper = QueryGenerator.initQueryWrapper(productionstatement, req.getParameterMap());
		Page<Productionstatement> page = new Page<Productionstatement>(pageNo, pageSize);
		IPage<Productionstatement> pageList = productionstatementService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	 /**
	  * LJCADD
	  */
	 /**
	  * LJCadd
	  */
	 @GetMapping("/test")
	 public Result<List<Productionstatement>> queryList(){
		 List<Productionstatement> productionstatements = productionstatementService.getAll();
		 Result<List<Productionstatement>> result = new Result<>();
		 result.setResult(productionstatements);
		 result.setSuccess(true);
		 return result;
	 }

	/**
	 *   添加
	 *
	 * @param productionstatement
	 * @return
	 */
	@AutoLog(value = "生产报表-添加")
	@ApiOperation(value="生产报表-添加", notes="生产报表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Productionstatement productionstatement) {
		productionstatementService.save(productionstatement);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param productionstatement
	 * @return
	 */
	@AutoLog(value = "生产报表-编辑")
	@ApiOperation(value="生产报表-编辑", notes="生产报表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Productionstatement productionstatement) {
		productionstatementService.updateById(productionstatement);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产报表-通过id删除")
	@ApiOperation(value="生产报表-通过id删除", notes="生产报表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		productionstatementService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "生产报表-批量删除")
	@ApiOperation(value="生产报表-批量删除", notes="生产报表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productionstatementService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产报表-通过id查询")
	@ApiOperation(value="生产报表-通过id查询", notes="生产报表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Productionstatement productionstatement = productionstatementService.getById(id);
		if(productionstatement==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(productionstatement);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param productionstatement
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Productionstatement productionstatement) {
        return super.exportXls(request, productionstatement, Productionstatement.class, "生产报表");
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
        return super.importExcel(request, response, Productionstatement.class);
    }

}
