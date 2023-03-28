package org.jeecg.modules.customapi.realtimedata.entity;

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
 * @Description: 实时数据报板
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Data
@TableName("realtimedata")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="realtimedata对象", description="实时数据报板")
public class Realtimedata implements Serializable {
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
	/**待排产订单数量*/
	@Excel(name = "待排产订单数量", width = 15)
    @ApiModelProperty(value = "待排产订单数量")
    private Integer waitScheduleNum;
	/**新订单数量*/
	@Excel(name = "新订单数量", width = 15)
    @ApiModelProperty(value = "新订单数量")
    private Integer newOrderNum;
	/**当月已经完成订单数量*/
	@Excel(name = "当月已经完成订单数量", width = 15)
    @ApiModelProperty(value = "当月已经完成订单数量")
    private Integer finishOrderNum;
	/**车间工作人员数量*/
	@Excel(name = "车间工作人员数量", width = 15)
    @ApiModelProperty(value = "车间工作人员数量")
    private Integer workshopStaffNum;
	/**操作车间使用数量*/
	@Excel(name = "操作车间使用数量", width = 15)
    @ApiModelProperty(value = "操作车间使用数量")
    private Integer useWorkshopStaff;
}
