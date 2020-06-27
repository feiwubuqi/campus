package com.campus.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class LostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    @TableField("ACCOUNT_SNO")
    private String accountSno;

    /**
     * 失物描述
     */
    @TableField("LOST_DESC")
    private String lostDesc;

    /**
     * 地点
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 联系方式
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 任务状态 00-招领，01-遗失，02-完成
     */
    @TableField("TASK_TYPE")
    private String taskType;

    /**
     * 图片保存地址
     */
    @TableField("IMG_ADDRESS")
    private String imgAddress;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private String createTime;

    /**
     * id
     */
    @TableId("ID")
    private String id;


}
