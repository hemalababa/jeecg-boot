package org.jeecg.modules.iot.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.iot.entity.Village;
import org.jeecg.modules.iot.service.IVillageService;

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

 /**
 * @Description: 小区表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
@RestController
@RequestMapping("/iot/village")
@Slf4j
public class VillageController extends JeecgController<Village, IVillageService> {
	@Autowired
	private IVillageService villageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param village
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Village village,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Village> queryWrapper = QueryGenerator.initQueryWrapper(village, req.getParameterMap());
		Page<Village> page = new Page<Village>(pageNo, pageSize);
		IPage<Village> pageList = villageService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param village
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Village village) {
		villageService.save(village);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param village
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Village village) {
		villageService.updateById(village);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		villageService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.villageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Village village = villageService.getById(id);
		if(village==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(village);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param village
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Village village) {
        return super.exportXls(request, village, Village.class, "小区表");
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
        return super.importExcel(request, response, Village.class);
    }


    /**
     * 根据登录用户获取小区集合
	 *
     * @author : houchunjian
     * @date : 2020-01-13 15:04
     */
	 @GetMapping(value = "/getVillageList")
	 public Result<?> getVillageList() {
		 LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		 QueryWrapper<Village> queryWrapper = new QueryWrapper<>();
		 queryWrapper.lambda().eq(Village::getSysOrgCode,sysUser.getOrgCode());
		 List<Village> pageList = villageService.list(queryWrapper);
		 return Result.ok(pageList);
	 }
}
