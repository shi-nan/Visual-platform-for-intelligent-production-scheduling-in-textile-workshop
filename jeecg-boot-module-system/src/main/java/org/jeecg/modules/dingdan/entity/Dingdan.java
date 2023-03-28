package org.jeecg.modules.dingdan.entity;

import java.io.Serializable;
import java.util.Date;
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

/**
 * @Description: 订单表
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@ApiModel(value="dingdan对象", description="订单表")
@Data
@TableName("dingdan")
public class Dingdan implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
    private java.lang.String orderId;
	/**订单日期*/
	@Excel(name = "订单日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "订单日期")
    private java.util.Date orderDate;
	/**订单客户*/
	@Excel(name = "订单客户", width = 15)
    @ApiModelProperty(value = "订单客户")
    private java.lang.String orderCustomer;
	/**预发货日期*/
	@Excel(name = "预发货日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预发货日期")
    private java.util.Date advanceShipmentdate;
	/**BOM状态*/
	@Excel(name = "BOM状态", width = 15)
    @ApiModelProperty(value = "BOM状态")
    private java.lang.String bomStatus;
	/**是否生单*/
	@Excel(name = "是否生单", width = 15, dicCode = "yes_no")
    @Dict(dicCode = "yes_no")
    @ApiModelProperty(value = "是否生单")
    private java.lang.String ynOrder;
	/**是否确认*/
	@Excel(name = "是否确认", width = 15, dicCode = "yes_no")
    @Dict(dicCode = "yes_no")
    @ApiModelProperty(value = "是否确认")
    private java.lang.String ynEnsure;
	/**设置约束*/
	@Excel(name = "设置约束", width = 15, dicCode = "schedulebind")
    @Dict(dicCode = "schedulebind")
    @ApiModelProperty(value = "设置约束")
    private java.lang.String setBind;
	/**优先级*/
	@Excel(name = "优先级", width = 15)
    @ApiModelProperty(value = "优先级")
    private java.lang.String precedence;
	/**订单状态*/
	@Excel(name = "订单状态", width = 15, dicCode = "dingdanzhuangtai")
    @Dict(dicCode = "dingdanzhuangtai")
    @ApiModelProperty(value = "订单状态")
    private java.lang.String orderStatus;
	/**排单状态*/
	@Excel(name = "排单状态", width = 15, dicCode = "paidanzhuangtai")
    @Dict(dicCode = "paidanzhuangtai")
    @ApiModelProperty(value = "排单状态")
    private java.lang.String scheduleStatus;
	/**生产状态*/
	@Excel(name = "生产状态", width = 15, dicCode = "shengchanzhuangtai")
    @Dict(dicCode = "shengchanzhuangtai")
    @ApiModelProperty(value = "生产状态")
    private java.lang.String productionStatus;
	/**排单员*/
	@Excel(name = "排单员", width = 15)
    @ApiModelProperty(value = "排单员")
    private java.lang.String scheduleStaff;
}
