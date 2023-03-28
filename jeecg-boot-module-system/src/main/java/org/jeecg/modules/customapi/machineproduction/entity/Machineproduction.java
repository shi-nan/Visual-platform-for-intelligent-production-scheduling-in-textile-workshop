package org.jeecg.modules.customapi.machineproduction.entity;

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
 * @Description: 机器产能
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Data
@TableName("machineproduction")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="machineproduction对象", description="机器产能")
public class Machineproduction implements Serializable {
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
	/**混条机*/
	@Excel(name = "混条机", width = 15)
    @ApiModelProperty(value = "混条机")
    private String huntiaoji;
	/**头道针梳机*/
	@Excel(name = "头道针梳机", width = 15)
    @ApiModelProperty(value = "头道针梳机")
    private Integer toudaozhenshuji;
	/**二道针梳机*/
	@Excel(name = "二道针梳机", width = 15)
    @ApiModelProperty(value = "二道针梳机")
    private Integer erdaozhenshuji;
	/**三道针梳机*/
	@Excel(name = "三道针梳机", width = 15)
    @ApiModelProperty(value = "三道针梳机")
    private Integer sandaozhenshuji;
	/**四道针梳机*/
	@Excel(name = "四道针梳机", width = 15)
    @ApiModelProperty(value = "四道针梳机")
    private Integer sidaozhenshuji;
	/**五道针梳机*/
	@Excel(name = "五道针梳机", width = 15)
    @ApiModelProperty(value = "五道针梳机")
    private Integer wudaozhenshuji;
	/**粗纱机*/
	@Excel(name = "粗纱机", width = 15)
    @ApiModelProperty(value = "粗纱机")
    private Integer cushaji;
}
