package org.jeecg.modules.dingdanjincheng.entity;

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
 * @Description: 记录订单进程
 * @Author: jeecg-boot
 * @Date:   2022-04-21
 * @Version: V1.0
 */
@Data
@TableName("dingdanjincheng")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="dingdanjincheng对象", description="记录订单进程")
public class Dingdanjincheng implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
    private String dingdanId;
	/**粗纱*/
	@Excel(name = "粗纱", width = 15)
    @ApiModelProperty(value = "粗纱")
    private Integer cusha;
	/**细纱*/
	@Excel(name = "细纱", width = 15)
    @ApiModelProperty(value = "细纱")
    private Integer xisha;
	/**混条*/
	@Excel(name = "混条", width = 15)
    @ApiModelProperty(value = "混条")
    private Integer huntiao;
	/**头针*/
	@Excel(name = "头针", width = 15)
    @ApiModelProperty(value = "头针")
    private Integer touzhen;
	/**二针*/
	@Excel(name = "二针", width = 15)
    @ApiModelProperty(value = "二针")
    private Integer erzhen;
	/**三针*/
	@Excel(name = "三针", width = 15)
    @ApiModelProperty(value = "三针")
    private Integer sanzhen;
	/**四针*/
	@Excel(name = "四针", width = 15)
    @ApiModelProperty(value = "四针")
    private Integer sizhen;
	/**五针*/
	@Excel(name = "五针", width = 15)
    @ApiModelProperty(value = "五针")
    private Integer wuzhen;
}
