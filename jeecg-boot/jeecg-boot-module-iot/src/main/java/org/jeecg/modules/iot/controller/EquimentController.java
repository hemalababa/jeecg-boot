package org.jeecg.modules.iot.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.iot.entity.Equiment;
import org.jeecg.modules.iot.service.IEquimentService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2020-01-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="设备表")
@RestController
@RequestMapping("/iot/equiment")
public class EquimentController extends JeecgController<Equiment, IEquimentService> {
	@Autowired
	private IEquimentService equimentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param equiment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备表-分页列表查询")
	@ApiOperation(value="设备表-分页列表查询", notes="设备表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Equiment equiment,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Equiment> queryWrapper = QueryGenerator.initQueryWrapper(equiment, req.getParameterMap());
		Page<Equiment> page = new Page<Equiment>(pageNo, pageSize);
		IPage<Equiment> pageList = equimentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param equiment
	 * @return
	 */
	@AutoLog(value = "设备表-添加")
	@ApiOperation(value="设备表-添加", notes="设备表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Equiment equiment) {
		equimentService.save(equiment);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param equiment
	 * @return
	 */
	@AutoLog(value = "设备表-编辑")
	@ApiOperation(value="设备表-编辑", notes="设备表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Equiment equiment) {
		equimentService.updateById(equiment);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备表-通过id删除")
	@ApiOperation(value="设备表-通过id删除", notes="设备表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		equimentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备表-批量删除")
	@ApiOperation(value="设备表-批量删除", notes="设备表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.equimentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备表-通过id查询")
	@ApiOperation(value="设备表-通过id查询", notes="设备表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Equiment equiment = equimentService.getById(id);
		return Result.ok(equiment);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param equiment
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Equiment equiment) {
      return super.exportXls(request, equiment, Equiment.class, "设备表");
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
      return super.importExcel(request, response, Equiment.class);
  }

}
