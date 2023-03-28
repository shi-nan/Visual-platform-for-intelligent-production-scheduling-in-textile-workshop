package org.jeecg.modules.sensor.entity;

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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 传感器表
 * @Author: jeecg-boot
 * @Date:   2022-04-23
 * @Version: V1.0
 */
@Data
@TableName("sensor")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sensor对象", description="传感器表")
public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**传感器名称*/
	@Excel(name = "传感器名称", width = 15)
    @ApiModelProperty(value = "传感器名称")
    private String sensorName;
	/**传感器类型*/
	@Excel(name = "传感器类型", width = 15)
    @ApiModelProperty(value = "传感器类型")
    private String sensorType;
	/**传感器强度*/
	@Excel(name = "传感器强度", width = 15)
    @ApiModelProperty(value = "传感器强度")
    private String sensorIntensity;
	/**读写控制*/
	@Excel(name = "读写控制", width = 15)
    @ApiModelProperty(value = "读写控制")
    private String sensorRw;
}
