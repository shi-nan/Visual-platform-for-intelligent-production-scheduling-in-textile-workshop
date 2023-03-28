package org.jeecg.modules.customapi.productionstatement.entity;

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
 * @Description: 生产报表
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Data
@TableName("productionstatement")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="productionstatement对象", description="生产报表")
public class Productionstatement implements Serializable {
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
	/**设备*/
	@Excel(name = "设备", width = 15)
    @ApiModelProperty(value = "设备")
    private String equipment;
	/**设备型号*/
	@Excel(name = "设备型号", width = 15)
    @ApiModelProperty(value = "设备型号")
    private String eqType;
	/**所属工序*/
	@Excel(name = "所属工序", width = 15, dicCode = "suoshugongxu")
	@Dict(dicCode = "suoshugongxu")
    @ApiModelProperty(value = "所属工序")
    private String belongProcess;
	/**原料*/
	@Excel(name = "原料", width = 15)
    @ApiModelProperty(value = "原料")
    private String material;
	/**技术员*/
	@Excel(name = "技术员", width = 15)
    @ApiModelProperty(value = "技术员")
    private String techStaff;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private String orderNum;
	/**班次*/
	@Excel(name = "班次", width = 15)
    @ApiModelProperty(value = "班次")
    private String whenWork;
	/**机器状态*/
	@Excel(name = "机器状态", width = 15)
    @ApiModelProperty(value = "机器状态")
    private String machineStatus;
}
