package org.jeecg.modules.preschedulemanage.entity;

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
 * @Description: 预排程管理
 * @Author: jeecg-boot
 * @Date:   2021-11-24
 * @Version: V1.0
 */
@Data
@TableName("preschedulemanage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="preschedulemanage对象", description="预排程管理")
public class Preschedulemanage implements Serializable {
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
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private String orderId;
	/**订单客户*/
	@Excel(name = "订单客户", width = 15)
    @ApiModelProperty(value = "订单客户")
    private String orderCustomer;
	/**生产状态*/
	@Excel(name = "生产状态", width = 15, dicCode = "shengchanzhuangtai")
	@Dict(dicCode = "shengchanzhuangtai")
    @ApiModelProperty(value = "生产状态")
    private String productionStatus;
	/**排单顺序*/
	@Excel(name = "排单顺序", width = 15)
    @ApiModelProperty(value = "排单顺序")
    private String scheduleOrder;
	/**生产开始时间*/
	@Excel(name = "生产开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生产开始时间")
    private Date startTime;
	/**预计完成时间*/
	@Excel(name = "预计完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预计完成时间")
    private Date finishTime;
	/**设置约束*/
	@Excel(name = "设置约束", width = 15, dicCode = "schedulebind")
	@Dict(dicCode = "schedulebind")
    @ApiModelProperty(value = "设置约束")
    private String setBind;
}
