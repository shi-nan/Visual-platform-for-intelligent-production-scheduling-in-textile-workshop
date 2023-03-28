package org.jeecg.modules.processes.entity;

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
 * @Description: 工序表
 * @Author: jeecg-boot
 * @Date:   2021-11-04
 * @Version: V1.0
 */
@Data
@TableName("processes")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="processes对象", description="工序表")
public class Processes implements Serializable {
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
	/**订单ID*/
	@Excel(name = "订单ID", width = 15)
    @ApiModelProperty(value = "订单ID")
    private String orderId;
	/**工序名称*/
	@Excel(name = "工序名称", width = 15)
    @ApiModelProperty(value = "工序名称")
    private String processesName;
	/**工序顺序*/
	@Excel(name = "工序顺序", width = 15)
    @ApiModelProperty(value = "工序顺序")
    private String processesSequence;
	/**工序开始时间*/
	@Excel(name = "工序开始时间", width = 15)
    @ApiModelProperty(value = "工序开始时间")
    private String processesBegin;
	/**工序工作时间*/
	@Excel(name = "工序工作时间", width = 15)
    @ApiModelProperty(value = "工序工作时间")
    private String processesEnd;
}
