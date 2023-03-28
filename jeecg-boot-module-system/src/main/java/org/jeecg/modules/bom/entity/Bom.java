package org.jeecg.modules.bom.entity;

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
 * @Description: 物料详情
 * @Author: jeecg-boot
 * @Date:   2022-04-17
 * @Version: V1.0
 */
@Data
@TableName("bom")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bom对象", description="物料详情")
public class Bom implements Serializable {
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
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
    private String orderId;
	/**物料级别*/
	@Excel(name = "物料级别", width = 15)
    @ApiModelProperty(value = "物料级别")
    private String materialClass;
	/**物料号*/
	@Excel(name = "物料号", width = 15)
    @ApiModelProperty(value = "物料号")
    private String materialNumber;
	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
    @ApiModelProperty(value = "物料名称")
    private String materialName;
	/**物料阶段*/
	@Excel(name = "物料阶段", width = 15)
    @ApiModelProperty(value = "物料阶段")
    private String materialStage;
	/**所需机器数量*/
	@Excel(name = "所需机器数量", width = 15)
    @ApiModelProperty(value = "所需机器数量")
    private String materialRequiredNumber;
	/**工时*/
	@Excel(name = "工时", width = 15)
    @ApiModelProperty(value = "工时")
    private String workingHour;
	/**说明*/
	@Excel(name = "说明", width = 15)
    @ApiModelProperty(value = "说明")
    private String declaration;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private String quantity;
	/**所需子物料数*/
	@Excel(name = "所需子物料数", width = 15)
    @ApiModelProperty(value = "所需子物料数")
    private String subMaterialRequiredNumber;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
    private String measuringUnit;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
}
