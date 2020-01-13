package org.jeecg.modules.iot.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2020-01-10
 * @Version: V1.0
 */
@Data
@TableName("cm_equiment")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cm_equiment对象", description="设备表")
public class Equiment {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**lon*/
	@Excel(name = "lon", width = 15)
    @ApiModelProperty(value = "lon")
	private java.lang.String lon;
	/**lat*/
	@Excel(name = "lat", width = 15)
    @ApiModelProperty(value = "lat")
	private java.lang.String lat;
	/**ip*/
	@Excel(name = "ip", width = 15)
    @ApiModelProperty(value = "ip")
	private java.lang.String ip;
	/**mac*/
	@Excel(name = "mac", width = 15)
    @ApiModelProperty(value = "mac")
	private java.lang.String mac;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
	private java.lang.String name;
	/**type*/
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
	@Dict(dicCode = "equipment_type")
	private java.lang.String type;
	/**人员进出类型：1进，2出，3内部*/
	@Excel(name = "人员进出类型：1进，2出，3内部", width = 15)
    @ApiModelProperty(value = "人员进出类型：1进，2出，3内部")
	@Dict(dicCode = "equipment_ie_type")
	private java.lang.String ieType;
	/**villageId*/
	@Excel(name = "villageId", width = 15)
    @ApiModelProperty(value = "villageId")
	private java.lang.Integer villageId;
	/**installTime*/
    @ApiModelProperty(value = "installTime")
	private java.util.Date installTime;
	/**directionAngle*/
	@Excel(name = "directionAngle", width = 15)
    @ApiModelProperty(value = "directionAngle")
	private java.lang.String directionAngle;
	/**coverage*/
	@Excel(name = "coverage", width = 15)
    @ApiModelProperty(value = "coverage")
	private java.lang.String coverage;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
	private java.lang.Integer status;
	/**checkTime*/
    @ApiModelProperty(value = "checkTime")
	private java.util.Date checkTime;
	/**offlineTime*/
    @ApiModelProperty(value = "offlineTime")
	private java.util.Date offlineTime;
	/**streamUrl*/
	@Excel(name = "streamUrl", width = 15)
    @ApiModelProperty(value = "streamUrl")
	private java.lang.String streamUrl;
	/**buildingId*/
	@Excel(name = "buildingId", width = 15)
    @ApiModelProperty(value = "buildingId")
	private java.lang.Integer buildingId;
	/**rssi*/
	@Excel(name = "rssi", width = 15)
    @ApiModelProperty(value = "rssi")
	private java.lang.String rssi;
	/**position*/
	@Excel(name = "position", width = 15)
    @ApiModelProperty(value = "position")
	private java.lang.String position;
	/**channelId*/
	@Excel(name = "channelId", width = 15)
    @ApiModelProperty(value = "channelId")
	private java.lang.String channelId;
	/**eqOrder*/
	@Excel(name = "eqOrder", width = 15)
    @ApiModelProperty(value = "eqOrder")
	private java.lang.Integer eqOrder;
	/**unionId*/
	@Excel(name = "unionId", width = 15)
    @ApiModelProperty(value = "unionId")
	private java.lang.String unionId;
	/**showFlag*/
	@Excel(name = "showFlag", width = 15)
    @ApiModelProperty(value = "showFlag")
	private java.lang.Integer showFlag;
	/**isCollect*/
	@Excel(name = "isCollect", width = 15)
    @ApiModelProperty(value = "isCollect")
	private java.lang.Integer isCollect;
	/**车辆进出类型：1进，2出，3内部*/
	@Excel(name = "车辆进出类型：1进，2出，3内部", width = 15)
    @ApiModelProperty(value = "车辆进出类型：1进，2出，3内部")
	@Dict(dicCode = "equipment_ie_type")
	private java.lang.String carType;
	/**height*/
	@Excel(name = "height", width = 15)
    @ApiModelProperty(value = "height")
	private java.lang.Float height;
	/**摄像头分布：1 楼栋内 2小区道路*/
	@Excel(name = "摄像头分布：1 楼栋内 2小区道路", width = 15)
    @ApiModelProperty(value = "摄像头分布：1 楼栋内 2小区道路")
	@Dict(dicCode = "equipment_camera_dist")
	private java.lang.String cameraDist;
}
