package org.jeecg.modules.dingdan.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@ApiModel(value="dingdan对象", description="订单表")
@Data
@TableName("dingdanxiangqing")
public class Dingdanxiangqing implements Serializable {
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
	/**纱线名称*/
	@Excel(name = "纱线名称", width = 15)
	@ApiModelProperty(value = "纱线名称")
	private java.lang.String shanxianName;
	/**纱线种类*/
	@Excel(name = "纱线种类", width = 15)
	@ApiModelProperty(value = "纱线种类")
	private java.lang.String shaxianElement;
	/**纱线织数*/
	@Excel(name = "纱线织数", width = 15)
	@ApiModelProperty(value = "纱线织数")
	private java.lang.String shaxianZhishu;
	/**纱线颜色*/
	@Excel(name = "纱线颜色", width = 15)
	@ApiModelProperty(value = "纱线颜色")
	private java.lang.String shaxianColor;
	/**需求量*/
	@Excel(name = "需求量", width = 15)
	@ApiModelProperty(value = "需求量")
	private java.lang.String orderWeight;
	/**价格*/
	@Excel(name = "价格", width = 15)
	@ApiModelProperty(value = "价格")
	private java.lang.String money;
	/**订单号*/
	@ApiModelProperty(value = "订单号")
	private java.lang.String orderId;
	/**完成数量*/
	@Excel(name = "完成数量", width = 15)
	@ApiModelProperty(value = "完成数量")
	private java.lang.String completeNumber;
}
