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
public class LateInfoFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private String createTime;

    /**
     * 晚归原因
     */
    @TableField("LATE_CAUSE")
    private String lateCause;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 门牌号
     */
    @TableField("HOUSE_NO")
    private String houseNo;

    /**
     * 学号
     */
    @TableField("ACCOUNT_SNO")
    private String accountSno;

    /**
     * 图片保存地址
     */
    @TableField("IMG_ADDRESS")
    private String imgAddress;

    /**
     * id
     */
    @TableId("ID")
    private String id;


}
