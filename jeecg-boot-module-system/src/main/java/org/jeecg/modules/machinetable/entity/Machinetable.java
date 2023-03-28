package org.jeecg.modules.machinetable.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("machinetable")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="machinetable对象", description="设备表")
public class Machinetable implements Serializable {
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
	/**设备ID*/
	@Excel(name = "设备ID", width = 15)
    @ApiModelProperty(value = "设备ID")
    private String deviceId;
	/**设备名*/
	@Excel(name = "设备名", width = 15)
    @ApiModelProperty(value = "设备名")
    private String machineName;
	/**机器型号*/
	@Excel(name = "机器型号", width = 15)
    @ApiModelProperty(value = "机器型号")
    private String machineType;
	/**所属工序*/
	@Excel(name = "所属工序", width = 15)
    @ApiModelProperty(value = "所属工序")
    private String belongProcess;
	/**物料*/
	@Excel(name = "物料", width = 15)
    @ApiModelProperty(value = "物料")
    private String material;
	/**技术员*/
	@Excel(name = "技术员", width = 15)
    @ApiModelProperty(value = "技术员")
    private String technologyStaff;
	/**班次*/
	@Excel(name = "班次", width = 15, dicCode = "banci")
	@Dict(dicCode = "banci")
    @ApiModelProperty(value = "班次")
    private String whatShift;
	/**订单ID*/
	@Excel(name = "订单ID", width = 15)
    @ApiModelProperty(value = "订单ID")
    private String orderId;
	/**机器状态*/
	@Excel(name = "机器状态", width = 15, dicCode = "jiqizhuangtai")
	@Dict(dicCode = "jiqizhuangtai")
    @ApiModelProperty(value = "机器状态")
    private String machineStatus;
}
