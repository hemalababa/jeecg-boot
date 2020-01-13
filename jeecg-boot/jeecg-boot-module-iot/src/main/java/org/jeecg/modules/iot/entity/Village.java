package org.jeecg.modules.iot.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 小区表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
@Data
@TableName("cm_village")
public class Village implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    private java.lang.String sysOrgCode;
	/**小区名称*/
	@Excel(name = "小区名称", width = 15)
    private java.lang.String name;
	/**行政区编码*/
	@Excel(name = "行政区编码", width = 15)
    private java.lang.String areaCode;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    private java.lang.String latitude;
	/**经度*/
	@Excel(name = "经度", width = 15)
    private java.lang.String longitude;
	/**楼栋数量*/
	@Excel(name = "楼栋数量", width = 15)
    private java.lang.Integer buildingsNumber;
	/**单元数量*/
	@Excel(name = "单元数量", width = 15)
    private java.lang.Integer cellNumber;
}
