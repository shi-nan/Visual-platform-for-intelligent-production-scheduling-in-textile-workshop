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
 * @Description: 工人换班
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@ApiModel(value="dingdan对象", description="订单表")
@Data
@TableName("staffchange")
public class Staffchange implements Serializable {
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
	/**员工ID*/
	@Excel(name = "员工ID", width = 15)
	@ApiModelProperty(value = "员工ID")
	private java.lang.String staffId;
	/**早班时间*/
	@Excel(name = "早班时间", width = 15)
	@ApiModelProperty(value = "早班时间")
	private java.lang.String dayShift;
	/**午班时间*/
	@Excel(name = "午班时间", width = 15)
	@ApiModelProperty(value = "午班时间")
	private java.lang.String nightShift;
	/**订单编号*/
	@ApiModelProperty(value = "订单编号")
	private java.lang.String orderId;
	/**加班时间*/
	@Excel(name = "加班时间", width = 15)
	@ApiModelProperty(value = "加班时间")
	private java.lang.String prolongTime;
}
