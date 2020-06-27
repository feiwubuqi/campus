package com.campus.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengmh@belink.com
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RepairInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题描述
     */
    @TableField("PROBLEM_DESC")
    private String problemDesc;

    /**
     * 门牌号
     */
    @TableField("HOUSE_NO")
    private String houseNo;

    /**
     * 报备时间
     */
    @TableField("REPORT_TIME")
    private String reportTime;

    /**
     * 联系方式
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 维修时间
     */
    @TableField("REPAIR_TIME")
    private String repairTime;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 图片保存地址
     */
    @TableField("IMG_ADDRESS")
    private String imgAddress;

    /**
     * 处理状态 00-未处理，01-处理中，02-已完成
     */
    @TableField("PROCESS_STATUS")
    private String processStatus;

    /**
     * 学号
     */
    @TableField("ACCOUNT_SNO")
    private String accountSno;

    /**
     * 任务id
     */
    @TableId(value = "TASK_ID",type = IdType.AUTO)
    private String taskId;


}
